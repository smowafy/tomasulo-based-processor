
public class RobEntry {
	private int entryno;
	private int instruction;
	private String dest;
	private int value;
	private boolean ready;
	
	public RobEntry(int entryno, int ins, String dest){
		this.entryno = entryno;
		this.instruction = ins;
		this.dest = dest;
		this.value = -1;
		this.ready = false;			
	}
	
	
	

}
