package Instructions;

public class CBranchIns extends Instruction {

	public CBranchIns(int []ins){
		super();
		this.type = "Beq";
		this.instruction = ins;
	}
}
