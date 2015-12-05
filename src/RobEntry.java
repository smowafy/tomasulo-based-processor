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
	
	
	

}
