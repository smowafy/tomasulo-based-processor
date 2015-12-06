package Instructions;

import Registers.Registers;


public class StoreIns extends Instruction{
	
	public StoreIns(int []ins){
		super();
		this.type = "StoreIns";
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
	public int getimmm(){
		int []rt = {this.instruction[10],this.instruction[11],this.instruction[12],this.instruction[13],
		this.instruction[14],this.instruction[15],this.instruction[16]}; //get 2's comp
		return Registers.intArrayToInt(rt);
	}

	public String getrsname(){
		return "R"+this.getrs();
	}
	public String getrtname(){
		return "R"+this.getrt();
	}

}
