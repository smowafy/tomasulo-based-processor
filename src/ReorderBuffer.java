import java.util.LinkedList;

public class ReorderBuffer {
	private int size;
	//private int head; // no need to head as we remove first committed instruction when done,
						//so automatically head is the next first
	//private int tail;
	private LinkedList<RobEntry> buffer;
	
	public ReorderBuffer(int size){
		this.size = size;
		this.buffer = new LinkedList<RobEntry>();
	}
	
	public boolean isFull(){
		return this.buffer.size() == this.size;
	}
	
	public void add(){
		
	}
	
	public void delete(){
		
	}

}
