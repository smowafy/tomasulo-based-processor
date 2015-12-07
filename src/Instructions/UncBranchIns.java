package Instructions;

public class UncBranchIns extends Instruction{

	public UncBranchIns(int []ins){
		super();
		this.type = "Jmp";
		this.instruction = ins;
	}
}
