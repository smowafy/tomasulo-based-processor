package ReservationStation;

import FunctionalUnits.*;
import Instructions.Instruction;

public class ArithStation extends Station{

	public ArithStation(String name) {
		super(name);
		if (this.getName().startsWith("Sub")) {
			this.setFunit(new Subtracter());
		}
		else{
			if (this.getName().startsWith("Mul")) {
				this.setFunit(new Multiplier());
			}
			else{
				if (this.getName().startsWith("Add")) {
					this.setFunit(new Adder());
				}
				else{
					this.setFunit(new Nand());
				}
			}
		}
	}
	public void issue(Instruction x){
		// each station issue in its own way;)
	}
}
