import java.util.LinkedList;


import Instructions.Instruction;

public class InstructionBuffer {
	private int size;
	private LinkedList<Instruction> buffer;
	
	public boolean isFull(){
		return size == this.buffer.size();
	}
	
	public void insertInstruction(Instruction x){
		this.buffer.add(x);
	}
	
	public void removeInstruction(Instruction x){
		this.buffer.remove(this.buffer.indexOf(x));
	}
}
