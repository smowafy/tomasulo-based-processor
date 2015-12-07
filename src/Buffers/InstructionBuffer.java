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
	public boolean isEmpty(){
		return this.buffer.isEmpty();
	}
	public void insertInstruction(Instruction x){
		this.buffer.add(x);
	}
	
	public void removeInstruction(){
		this.buffer.remove();
	}
	public Instruction getFirst(){
		return this.buffer.peek();
	}
}
