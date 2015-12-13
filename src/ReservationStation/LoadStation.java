package ReservationStation;

import FunctionalUnits.Loader;
import Instructions.LoadIns;
import Instructions.Instruction;
import Instructions.LoadIns;
import Registers.Registers;
import Tomasulo.Processor;

public class LoadStation extends Station{

	public LoadStation(String name, int addl) {
		super(name);
		this.setFunit(new Loader(addl));
	}

	@Override
	public void issue(Instruction x){
		int rob = Processor.getProcessor().getReorderBuffer().getEntryno(x);
		this.setBusy();
		this.setDest(rob);
		this.setIns(x);
		
		String rs = ((LoadIns)x).getrsname();
		String rt = ((LoadIns)x).getrtname();
		
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
		
		this.setDest((int)rt.charAt(1));
		this.setAddress(((LoadIns)x).getimmm());
		
		//upDate RegisterStat
		Processor.getProcessor().getRegisterStat().getReg((int)rt.charAt(1)).setBusy();
		Processor.getProcessor().getRegisterStat().getReg((int)rt.charAt(1)).setReorder(rob);

	}

}
