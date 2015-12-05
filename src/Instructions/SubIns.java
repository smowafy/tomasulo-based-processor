package Instructions;


public class SubIns extends Instruction{
	
	public SubIns(int []ins){
		super();
		this.type = "Sub";
		this.instruction = ins;
	}
	
	public int getOp(){
		int []op = {this.instruction[0],this.instruction[1],this.instruction[2],this.instruction[3]};
		return intArrayToInt(op);
	}
	public int getrs(){
		int []rs = {this.instruction[4],this.instruction[5],this.instruction[6]};
		return intArrayToInt(rs);
	}
	public int getrt(){
		int []rt = {this.instruction[7],this.instruction[8],this.instruction[9]};
		return intArrayToInt(rt);
	}
	public int getrd(){
		int []rd = {this.instruction[10],this.instruction[11],this.instruction[12]};
		return intArrayToInt(rd);
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
	public int intArrayToInt(int[] register)
	{
		int value = 0;
		int power= 0;
		for(int i = register.length-1; i>=0; i--)
		{
			value += (int) (register[i]*Math.pow(2, power));
			power+=1;
		}
		return value;
	}

}
