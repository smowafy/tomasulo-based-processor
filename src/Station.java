import FunctionalUnits.Adder;
import FunctionalUnits.FunctionalUnit;
import FunctionalUnits.Multiplier;
import FunctionalUnits.Nand;
import FunctionalUnits.Subtracter;
import Instructions.Instruction;


public class Station {
	private String name;
	private boolean busy;
	private Instruction ins; // ins object
	private int[] vJ; // if int change the funits inputs to int
	private int[] vK;
	private int qJ;
	private int qK;
	private int dest;
	private int address;
	private FunctionalUnit funit;
	
	public Station(String name){
		this.name = name;
		if(this.name.startsWith("load") || this.name.startsWith("add") || this.name.startsWith("store") ||
				this.name.startsWith("addi")){
			this.funit = new Adder();
		}
		else{
			if (this.name.startsWith("Sub")) {
				this.funit = new Subtracter();
			}
			else{
				if (this.name.startsWith("Mul")) {
					this.funit = new Multiplier();
				}
				this.funit = new Nand();
			}
		}
	}
}
