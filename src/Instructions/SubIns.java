package Instructions;

import Registers.Registers;


public class SubIns extends Instruction{
	
	public SubIns(int []ins){
		super();
		this.type = "Sub";
		this.instruction = ins;
	}
	
	public int getOp(){
		int []op = {this.instruction[0],this.instruction[1],this.instruction[2]};
		return Registers.intArrayToInt(op);
	}
	public int getrs(){
		int []rs = {this.instruction[3],this.instruction[4],this.instruction[5]};
		return Registers.intArrayToInt(rs);
	}
	public int getrt(){
		int []rt = {this.instruction[6],this.instruction[7],this.instruction[8]};
		return Registers.intArrayToInt(rt);
	}
	public int getrd(){
		int []rd = {this.instruction[9],this.instruction[10],this.instruction[11]};
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
