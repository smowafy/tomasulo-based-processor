package ReservationStation;

import FunctionalUnits.*;

import Instructions.*;
import Registers.Registers;
import Tomasulo.Processor;

public class AddStation extends Station{

	public AddStation(String name) {
		super(name);
		this.setFunit(new Adder());
	}
	public void issue(Instruction x){
		int rob = Processor.getProcessor().getReorderBuffer().getEntryno(x);
		this.setBusy();
		this.setDest(rob);
		this.setIns(x);
		
		String rs = ((AddIns)x).getrsname();
		String rt = ((AddIns)x).getrtname();
		String rd = ((AddIns)x).getrdname();
		
		//RS
		if (Processor.getProcessor().getRegisterStat().checkBusy(rs)) {
			int reorder = Processor.getProcessor().getRegisterStat().getReorder(rs);
			if (Processor.getProcessor().getReorderBuffer().getEntry(reorder).isReady()) {
				int[] vJ = Registers.IntToIntArray(Processor.getProcessor().getReorderBuffer().getEntry(reorder).getValue());
				this.setvJ(vJ);
				this.setqJ(0);
			}
			else{
				this.setqJ(reorder);
			}
		}
		else{
			//this.setvJ(Processor.getProcessor().getRegistersFile().getData());
			this.setqJ(0);
		}
		//RT
		if (Processor.getProcessor().getRegisterStat().checkBusy(rt)) {
			int reorder = Processor.getProcessor().getRegisterStat().getReorder(rt);
			if (Processor.getProcessor().getReorderBuffer().getEntry(reorder).isReady()) {
				int[] vK = Registers.IntToIntArray(Processor.getProcessor().getReorderBuffer().getEntry(reorder).getValue());
				this.setvK(vK);
				this.setqK(0);
			}
			else{
				this.setqK(reorder);
			}
		}
		else{
			//this.setvJ(Processor.getProcessor().getRegistersFile().getData());
			this.setqK(0);
		}
		
		//RD
		this.setDest((int)rd.charAt(1));
		
		//upDate RegisterStat
		Processor.getProcessor().getRegisterStat().getReg((int)rd.charAt(1)).setBusy(true);
		Processor.getProcessor().getRegisterStat().getReg((int)rd.charAt(1)).setReorder(rob);

	}
}
