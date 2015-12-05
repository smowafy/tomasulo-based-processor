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
		this.entryno = entryno;
		this.instruction = ins;
		this.dest = dest;
		this.value = -1;
		this.ready = false;			
	}
	public void setHead(){
		this.head = true;
	}
	public void setTail(){
		this.tail = true;
	}
	public void resetTail(){
		this.tail = false;
	}
	
	
	

}
