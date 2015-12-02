import FunctionalUnits.Adder;
import FunctionalUnits.FunctionalUnit;
import FunctionalUnits.Nand;
import FunctionalUnits.Subtracter;


public class Station {
	private String name;
	private boolean busy;
	private int opcode;
	private int vJ; // if int change the funits inputs to int
	private int vK;
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
				this.funit = new Nand();
			}
		}
	}
}
