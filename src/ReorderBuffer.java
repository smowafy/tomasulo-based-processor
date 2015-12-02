import java.util.LinkedList;

public class ReorderBuffer {
	private int size;
	private int head;
	private int tail;
	private LinkedList<RobEntry> buffer;
	
	public ReorderBuffer(int size){
		this.size = size;
		this.buffer = new LinkedList<RobEntry>();
	}
	
	public boolean isFull(){
		return this.buffer.size() == this.size;
	}

}
