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
	public int getReorder(){
		return this.reorder;
	}

}
