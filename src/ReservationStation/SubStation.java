package ReservationStation;

import FunctionalUnits.*;

import Instructions.SubIns;
import Instructions.Instruction;
import Registers.Registers;
import Tomasulo.Processor;

public class SubStation extends Station{

	public SubStation(String name, int subl) {
		super(name);
		this.setFunit(new Subtracter(subl));
	}
	public void issue(Instruction x){
		int rob = Processor.getProcessor().getReorderBuffer().getEntryno(x);
		this.setBusy();
		this.setDest(rob);
		this.setIns(x);
		
		String rs = ((SubIns)x).getrsname();
		String rt = ((SubIns)x).getrtname();
		String rd = ((SubIns)x).getrdname();
		
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
			this.setvJ(Processor.getProcessor().getRegistersFile().getRegData(rs));
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
			this.setvJ(Processor.getProcessor().getRegistersFile().getRegData(rt));
			this.setqK(0);
		}
		
		//RD
		this.setDest((int)rd.charAt(1));
		
		//upDate RegisterStat
		Processor.getProcessor().getRegisterStat().getReg((int)rd.charAt(1)).setBusy(true);
		Processor.getProcessor().getRegisterStat().getReg((int)rd.charAt(1)).setReorder(rob);

	}
}
