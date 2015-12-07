package Instructions;
import java.util.Arrays;

import Registers.*;
public class Instruction {
	protected String type;
	protected int[] instruction;
	protected int cyclesEx;
	
	public boolean equals(Instruction x){
		return this.type.equals(x.getType()) && Arrays.equals(this.getIns(),x.getIns());
	}
	public String getType(){
		return this.type;
	}
	public int getCycles(){
		return this.cyclesEx;
	}
	public int[] getIns(){
		return this.instruction;
	}
}
