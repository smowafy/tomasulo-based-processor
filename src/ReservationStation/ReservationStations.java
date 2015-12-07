package ReservationStation;

import java.util.LinkedList;

import Instructions.Instruction;

public class ReservationStations {
	private int size;
	private LinkedList<Station> stationList;
	
	public ReservationStations(int load, int store, int add, int sub, int mul, int addi, int nand){
		for (int i = 0; i < load; i++) {
			this.stationList.add(new LoadStation("load"+i));
		}
		for (int i = 0; i < store; i++) {
			this.stationList.add(new StoreStation("Store"+i));
		}
		for (int i = 0; i < add; i++) {
			this.stationList.add(new AddStation("Add"+i));
		}
		for (int i = 0; i < sub; i++) {
			this.stationList.add(new SubStation("Sub"+i));
		}
		for (int i = 0; i < mul; i++) {
			this.stationList.add(new MulStation("Mul"+i));
		}
		for (int i = 0; i < addi; i++) {
			this.stationList.add(new AddiStation("Adi"+i));
		}
		for (int i = 0; i < nand; i++) {
			this.stationList.add(new NandStation("Nand"+i));
		}
	}
	
	public boolean ifFull(){
		return this.size == stationList.size();
	}
	public void issue(Instruction x){
		for (int i = 0; i < size; i++) {
			if (!this.stationList.get(i).getBusy() && 
					(x.getType() == this.stationList.get(i).getName().substring(0, 3))) {
				this.stationList.get(i).issue(x);
			}
		}
		
	}
	
}
