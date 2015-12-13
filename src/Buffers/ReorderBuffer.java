package Buffers;

import java.util.LinkedList;
import Registers.*;

import Instructions.*;
import Tomasulo.Processor;

public class ReorderBuffer extends Buffer{
	//private int size;
	//private int head; // no need to head as we remove first committed instruction when done,
						//so automatically head is the next first
	//private int tail;
	private LinkedList<RobEntry> buffer;
	
	public ReorderBuffer(int size){
		super(size);
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
	public void issue(Instruction x){
		add(x);
	}
	public RobEntry getEntry(int i){
		return this.buffer.get(i);
	}
	public RobEntry getFirstEntry(){
		return this.buffer.peek();
	}
	public void commit(){
		RobEntry temp = this.buffer.pollFirst();
		String dest = temp.getDest();
		if (temp.getInstruction().getType() == "BEQ") {
			//if mispredicted -> Subtraction result not equal 0
			if (temp.getValue() != null) {
				//clear ROB[h], RegisterStat; fetch branch dest;
			}
			
		}
		else{
			if (temp.getInstruction().getType() == "Store") {
				//Mem[ROB[h].Destination] = ROB[h].Value;
				int address = Integer.parseInt(dest);
				Processor.getProcessor().getDataMemory().storeWord(address,temp.getValue());
			}
			else{
				Processor.getProcessor().getRegistersFile().writeToReg((temp.getValue()), dest);
			}
		}
		//ROB[h].Busy = no; we already remove the entry
		if (Processor.getProcessor().getRegisterStat().getReorder(dest) == temp.getEntryno()) {
			Processor.getProcessor().getRegisterStat().resetBusy(dest);
		}
	}
	public int getEntryno(Instruction x){
		int result = 0;
		for (int i = 0; i < this.buffer.size(); i++) {
			if (this.buffer.get(i).getInstruction().equals(x)) {
				result = this.buffer.get(i).getEntryno();
			}
		}
		return result;
	}

	public LinkedList<RobEntry> getBuffer() {
		return buffer;
	}

	public void setBuffer(LinkedList<RobEntry> buffer) {
		this.buffer = buffer;
	}
	
}
