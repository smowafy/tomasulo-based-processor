package Tomasulo;

import Buffers.*;
import Instructions.*;
import MemoryHierarchy.*;
import Registers.*;
import ReservationStation.*;

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
				int[] currIns = this.insMemory.getBlock(pc); // getblock to be changed
				if (currIns == null) {
					break;
				}
				else{
					this.insBuffer.insertInstruction(insConverter(currIns));
					this.pc += 1; //check 1 or 2
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
		
		//writeback
		
		//commit
			
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
}
