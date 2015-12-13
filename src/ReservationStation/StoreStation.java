package ReservationStation;

import FunctionalUnits.Adder;
import Instructions.Instruction;
import Instructions.StoreIns;
import Registers.Registers;
import Tomasulo.Processor;

public class StoreStation extends Station{

	public StoreStation(String name, int addl) {
		super(name);
		this.setFunit(new Adder(addl));
	}

	@Override
	public void issue(Instruction x){
		int rob = Processor.getProcessor().getReorderBuffer().getEntryno(x);
		this.setBusy();
		this.setDest(rob);
		this.setIns(x);
		
		String rs = ((StoreIns)x).getrsname();
		String rt = ((StoreIns)x).getrtname();
		
		//RS
		if (Processor.getProcessor().getRegisterStat().checkBusy(rs)) {
			int reorder = Processor.getProcessor().getRegisterStat().getReorder(rs);
			if (Processor.getProcessor().getReorderBuffer().getEntry(reorder).isReady()) {
				int[] vJ = (Processor.getProcessor().getReorderBuffer().getEntry(reorder).getValue());
				this.setvJ(vJ);
				this.setqJ(0);
			}
			else{
				this.setqJ(reorder);
			}
		}
		else{
			this.setvJ(Processor.getProcessor().getRegistersFile().getRegData(rs));
			this.setqJ(0);
		}
		//RT
		if (Processor.getProcessor().getRegisterStat().checkBusy(rt)) {
			int reorder = Processor.getProcessor().getRegisterStat().getReorder(rt);
			if (Processor.getProcessor().getReorderBuffer().getEntry(reorder).isReady()) {
				int[] vK = (Processor.getProcessor().getReorderBuffer().getEntry(reorder).getValue());
				this.setvK(vK);
				this.setqK(0);
			}
			else{
				this.setqK(reorder);
			}
		}
		else{
			this.setvJ(Processor.getProcessor().getRegistersFile().getRegData(rt));
			this.setqK(0);
		}
		
		//Immediate
		this.setAddress(((StoreIns)x).getimmm());
		

	}

}
