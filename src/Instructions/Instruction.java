package Instructions;
import Registers.*;
public class Instruction {
	protected String type;
	protected int[] instruction;
	protected int cycles;
	
	public String getType(){
		return this.type;
	}
}
