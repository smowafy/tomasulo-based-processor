package Instructions;

import Registers.Registers;


public class AddIns extends Instruction{
	
	public AddIns(int []ins){
		super();
		this.type = "Add";
		this.instruction = ins;
	}
	
	public int getOp(){
		int []op = {this.instruction[0],this.instruction[1],this.instruction[2],this.instruction[3]};
		return Registers.intArrayToInt(op);
	}
	public int getrs(){
		int []rs = {this.instruction[4],this.instruction[5],this.instruction[6]};
		return Registers.intArrayToInt(rs);
	}
	public int getrt(){
		int []rt = {this.instruction[7],this.instruction[8],this.instruction[9]};
		return Registers.intArrayToInt(rt);
	}
	public int getrd(){
		int []rd = {this.instruction[10],this.instruction[11],this.instruction[12]};
		return Registers.intArrayToInt(rd);
	}
	public String getrdname(){
		return "R"+this.getrd();
	}
	public String getrsname(){
		return "R"+this.getrs();
	}
	public String getrtname(){
		return "R"+this.getrt();
	}


}
