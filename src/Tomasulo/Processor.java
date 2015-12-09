package Tomasulo;

import Buffers.*;
import Instructions.*;
import MemoryHierarchy.*;
import Registers.*;
import ReservationStation.*;
import Buffers.*;


public class Processor {
	private static Processor processor;
	private int cycles;
	private int fetchCycles;
	private int pc;
	private MemorySet dataMemory;
	private MemorySet insMemory;
	private InstructionBuffer insBuffer;
	private ReservationStations reservationStation;
	private ReorderBuffer reorderBuffer;
	private Registers registersFile;
	private RegisterStat registerStat;
	
	public void initProcessor(int pc, MemorySet dataMemory,MemorySet insMemory,InstructionBuffer insBuffer,
			ReservationStations reservationStation,ReorderBuffer reorderBuffer,Registers registersFile,
			RegisterStat registerStat){
		this.setCycles(0);
		this.setPc(pc);
		this.setDataMemory(dataMemory);
		this.setInsMemory(insMemory);
		this.setRegistersFile(registersFile);
		this.insBuffer = insBuffer;
		this.setReservationStation(reservationStation);
		this.setReorderBuffer(reorderBuffer);
	}
	//creating instruction object
	public Instruction insConverter(int []x){
		Instruction result;
		int[]op = {x[0],x[1],x[2]};
		int opi = Registers.intArrayToInt(op);
		int[]f = {x[14],x[15]};
		int fi = Registers.intArrayToInt(f);
		switch(opi){
		case 0:
			switch(fi){
			case 0: result = new AddIns(x);break;
			case 1:	result = new SubIns(x);break;
			case 2:	result = new NandIns(x);break;
			default:	result = new MulIns(x);break;
			};break;
		case 1: result = new AddiIns(x);break;
		case 2: result = new LoadIns(x);break;
		case 3: result = new StoreIns(x);break;
		case 4: result = new UncBranchIns(x);break;
		case 5: result = new CBranchIns(x);break;
		case 6: result = new CallIns(x);break;
		default: result = new ReturnIns(x);break;
		}
		return result;
	}
	// Run processor
	public void run(){
		//fetch == > how fetch one cycle?? // how write one cycle if store??
		if(this.insBuffer.isEmpty()){
			int s = this.insBuffer.getSize();
			for (int i = s; i > 0 ; i--) {
				int[] currIns = this.insMemory.getWord(pc); // getblock to be changed
				if (currIns == null) {
					break;
				}
				else{
					this.insBuffer.insertInstruction(insConverter(currIns));
					this.pc += 1; 
					this.fetchCycles += this.insMemory.getNumberOfCyclesSpent();
				}
			}
		}
		if (this.fetchCycles==0) {
		//issue
			if (!this.reservationStation.ifFull() && !this.reorderBuffer.isFull()) {
				this.reorderBuffer.issue(this.insBuffer.getFirst());
				this.reservationStation.issue(this.insBuffer.getFirst());
				this.insBuffer.removeInstruction();
			}
		//execute
			
		
		for(Station s : reservationStation.getStationList()) {
			
			//if started execution already
			
			if (s.getIns().getCycles() > 0) {
				s.getIns().setCyclesEx(s.getIns().getCycles() - 1);
			} else if (!s.getIns().isStartedEx()){
			
				//Arithmetic
				if (isArithmetic(s)&& s.getqJ() == 0 && s.getqK() == 0 && !s.getIns().isStartedEx()) {
					reservationStation.execute(s);
				}
				if (s instanceof LoadStation && s.getqJ() == 0) {
					s.setAddress(Registers.intArrayToInt(s.getvJ()) + s.getAddress());
					reservationStation.execute(s);
					
					
				}
				if (s instanceof StoreStation && s.getqJ() == 0) {
					/*
					 * TODO
					 * 	1- Rediscuss the design of the ROB as the destination attribute in
					 * reservation station does not imply anything, to find the ROB entry
					 * of an instruction it must be all looked up
					 */
					RobEntry insROB;
					for(RobEntry tmp : reorderBuffer.getBuffer()) {
						/* Need verification regarding the comparison with double equal,
						* as the instruction reference is supposed to be the same
						*/
						//.euals implemented already for comparisons usage
						if (tmp.getInstruction().equals(s.getIns())) {
							insROB = tmp;
							insROB.setDest("" + (Registers.intArrayToInt(s.getvJ()) + s.getAddress()));
							break;
						}
					}
					/*
					 * Count cycles in write not in execute
					 */
				}
			}
			
		}
		
		//writeback
		
		for(RobEntry entry : reorderBuffer.getBuffer()) {
			Instruction inst = entry.getInstruction();
			Station station = getStationForInstruction(inst);
			if (inst.getCycles() == 0 && inst.isStartedEx()) {
				int[] result = station.getFunit().getResult();
				int resultInt = Registers.intArrayToInt(result);
				if (inst instanceof StoreIns) {
					entry.setValue(Registers.intArrayToInt(station.getvK()));
				} else {
					int b = station.getDest();
					station.setNotBusy();
					
					CDB(b, result);
					
					entry.setValue(resultInt);
					entry.setReady(true);
					
				}
			}
		}
		
		
		
		//Commit ==> commits only if the head is ready
		if (Processor.getProcessor().getReorderBuffer().getFirstEntry().isReady()) {
			Processor.getProcessor().getReorderBuffer().commit();
		}
		}
		//increment cycles
		setCycles(getCycles() + 1);
		if (this.fetchCycles>0)this.fetchCycles--;
	}
	
	//Showayet Getters w setters
	public int getCycles() {
		return cycles;
	}

	public void setCycles(int cycles) {
		this.cycles = cycles;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public MemorySet getDataMemory() {
		return dataMemory;
	}

	public void setDataMemory(MemorySet dataMemory) {
		this.dataMemory = dataMemory;
	}

	public MemorySet getInsMemory() {
		return insMemory;
	}

	public void setInsMemory(MemorySet insMemory) {
		this.insMemory = insMemory;
	}

	public ReservationStations getReservationStation() {
		return reservationStation;
	}

	public void setReservationStation(ReservationStations reservationStation) {
		this.reservationStation = reservationStation;
	}

	public ReorderBuffer getReorderBuffer() {
		return reorderBuffer;
	}

	public void setReorderBuffer(ReorderBuffer reorderBuffer) {
		this.reorderBuffer = reorderBuffer;
	}

	public Registers getRegistersFile() {
		return registersFile;
	}

	public void setRegistersFile(Registers registersFile) {
		this.registersFile = registersFile;
	}

	public RegisterStat getRegisterStat() {
		return registerStat;
	}

	public void setRegisterStat(RegisterStat registerStat) {
		this.registerStat = registerStat;
	}
	public static Processor getProcessor() {
		if (processor == null) {
			processor = new Processor();
		}
		return processor;
	}
	public static void setProcessor(Processor proc) {
		Processor.processor = proc;
	}
	
	public boolean isArithmetic(Station s) {
		return (s instanceof AddStation
				|| s instanceof SubStation
				|| s instanceof MulStation
				|| s instanceof NandStation);
	}
	
	public Station getStationForInstruction(Instruction ins) {
		for(Station s : reservationStation.getStationList()) {
			if (s.getIns() == ins) {
				return s;
			}
		}
		return null;
	}
	
	public void CDB(int b, int[] result){
		for(Station tmp : reservationStation.getStationList()) {
			if (tmp.getqJ() == b) {
				tmp.setvJ(result);
				tmp.setqJ(0);
			}
			if (tmp.getqK() == b) {
				tmp.setvK(result);
				tmp.setqK(0);
			}
		}
	}
}
