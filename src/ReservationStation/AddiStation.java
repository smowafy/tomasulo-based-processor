package ReservationStation;

import FunctionalUnits.Adder;
import Instructions.Instruction;
import Instructions.AddiIns;
import Registers.Registers;
import Tomasulo.Processor;

public class AddiStation extends Station{

	public AddiStation(String name, int addl) {
		super(name);
		this.setFunit(new Adder(addl));
	}

	@Override
	public void issue(Instruction x){
		int rob = Processor.getProcessor().getReorderBuffer().getEntryno(x);
		this.setBusy();
		this.setDest(rob);
		this.setIns(x);
		
		String rs = ((AddiIns)x).getrsname();
		String rt = ((AddiIns)x).getrtname();
		
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
		
		this.setDest((int)rt.charAt(1));
		this.setAddress(((AddiIns)x).getimmm());
		
		//upDate RegisterStat
		Processor.getProcessor().getRegisterStat().getReg((int)rt.charAt(1)).setBusy();
		Processor.getProcessor().getRegisterStat().getReg((int)rt.charAt(1)).setReorder(rob);

	}

}
