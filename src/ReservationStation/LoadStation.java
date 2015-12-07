package ReservationStation;

import FunctionalUnits.Loader;
import Instructions.Instruction;

public class LoadStation extends Station{

	public LoadStation(String name) {
		super(name);
		this.setFunit(new Loader());
	}

	@Override
	public void issue(Instruction x) {
		// TODO Auto-generated method stub
		
	}

}
