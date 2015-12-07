package Buffers;
import Instructions.Instruction;


public class RobEntry {
	private int entryno;
	private Instruction instruction;//instruction obj
	private String dest;
	private int value;
	private boolean ready;
	private boolean head;
	private boolean tail;
	
	public RobEntry(int entryno, Instruction ins, String dest){
		this.setEntryno(entryno);
		this.setInstruction(ins);
		this.setDest(dest);
		this.setValue(-1);
		this.setReady(false);			
	}
	public void setHead(){
		this.head = true;
	}
	public void setTail(){
		this.tail = true;
	}
	public void resetTail(){
		this.setTail(false);
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	public int getEntryno() {
		return entryno;
	}
	public void setEntryno(int entryno) {
		this.entryno = entryno;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isHead() {
		return head;
	}
	public void setHead(boolean head) {
		this.head = head;
	}
	public boolean isTail() {
		return tail;
	}
	public void setTail(boolean tail) {
		this.tail = tail;
	}
	
	
	

}
