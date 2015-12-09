package Buffers;
import java.util.LinkedList;


import Instructions.Instruction;

public class InstructionBuffer extends Buffer{
	private LinkedList<Instruction> buffer;
	
	public InstructionBuffer(int size){
		super(size);
		this.buffer = new LinkedList<Instruction>();
	}
	public boolean isFull(){
		return size == this.buffer.size();
	}
	
	public void insertInstruction(Instruction x){
		this.buffer.add(x);
	}
	
	public void removeInstruction(Instruction x){
		this.buffer.remove();
	}
}
