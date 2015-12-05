import java.util.LinkedList;

import Instructions.*;

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
	
	public void add(Instruction ins){
		this.buffer.getLast().resetTail();
		if (ins.getClass() == AddIns.class || ins.getClass() == MulIns.class || ins.getClass() == SubIns.class
				|| ins.getClass() == NandIns.class) {
			this.buffer.addLast(new RobEntry(this.buffer.size()+1, ins, ((AddIns) ins).getrdname()));
			this.buffer.getLast().setTail();
		}
		if (ins.getClass() == LoadIns.class || ins.getClass() == StoreIns.class || ins.getClass() == AddiIns.class) {
			this.buffer.addLast(new RobEntry(this.buffer.size()+1, ins, ((AddiIns) ins).getrtname()));
			this.buffer.getLast().setTail();
		}
	}
	
	public void delete(){
		this.buffer.removeFirst();
		this.buffer.getFirst().setHead();
	}

}
