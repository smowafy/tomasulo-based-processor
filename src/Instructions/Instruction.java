package Instructions;
import Registers.*;
public class Instruction {
	protected String type;
	protected int[] instruction;
	protected int cyclesEx;
	

	public String getType(){
		return this.type;
	}
	public int getCycles(){
		return this.cyclesEx;
	}
}
