package ReservationStation;

import FunctionalUnits.Adder;
import Instructions.Instruction;

public class AddiStation extends Station{

	public AddiStation(String name) {
		super(name);
		this.setFunit(new Adder());
	}

	@Override
	public void issue(Instruction x) {
		// TODO Auto-generated method stub
		
	}

}
