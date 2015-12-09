package Registers;

import java.util.LinkedList;



public class RegisterStat {
	private int size;
	private LinkedList<RegisterStatEntry> registers;
	
	public RegisterStat(int size){
		for (int i = 0; i < size; i++) {
			this.registers.add(new RegisterStatEntry("R"+i));
		}
	}
	 public boolean checkBusy(String reg){
		 int index = reg.charAt(1);
		 return ((RegisterStatEntry)this.registers.get(index)).getBusy();
	 }
	 public int getReorder(String reg){
		 int index = reg.charAt(1);
		 return ((RegisterStatEntry)this.registers.get(index)).getReorder();
	 }
	 public void resetBusy(String reg){
		 int index = reg.charAt(1);
		 this.registers.get(index).resetBusy();
	 }
	 public RegisterStatEntry getReg(int i){
		 return this.registers.get(i);
	 }
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	

}
