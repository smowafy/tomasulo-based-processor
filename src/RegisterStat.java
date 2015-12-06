import java.util.LinkedList;


public class RegisterStat {
	private int size;
	private LinkedList registers;
	
	public RegisterStat(int size){
		for (int i = 0; i < size; i++) {
			this.registers.add(new RegisterStatEntry("R"+i));
		}
	}
	 public boolean checkBusy(String reg){
		 int index = reg.charAt(1);
		 return ((RegisterStatEntry)this.registers.get(index)).getBusy();
	 }

}
