package Registers;

public class RegisterStatEntry {
	private String name;
	private boolean busy;
	private int reorder;
	
	public RegisterStatEntry(String name){
		this.name = name;
	}
	
	public boolean getBusy(){
		return this.busy;
	}
	public void setBusy(){
		this.busy=true;
	}
	public void setReorder(int i){
		this.reorder = 1;
	}
	public void resetBusy(){
		this.busy = false;
	}

	public int getReorder(){
		return this.reorder;
	}



}
