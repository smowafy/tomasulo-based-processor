package Instructions;

public class CallIns extends Instruction{

	public CallIns(int []ins){
		super();
		this.type = "Jal";
		this.instruction = ins;
	}
}
