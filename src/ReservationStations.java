import java.util.LinkedList;

public class ReservationStations {
	private int size;
	private LinkedList<Station> stationList;
	
	public ReservationStations(int load, int store, int add, int sub, int mul, int addi, int nand){
		for (int i = 0; i < load; i++) {
			this.stationList.add(new Station("load"+i));
		}
		for (int i = 0; i < store; i++) {
			this.stationList.add(new Station("store"+i));
		}
		for (int i = 0; i < add; i++) {
			this.stationList.add(new Station("add"+i));
		}
		for (int i = 0; i < sub; i++) {
			this.stationList.add(new Station("sub"+i));
		}
		for (int i = 0; i < mul; i++) {
			this.stationList.add(new Station("mul"+i));
		}
		for (int i = 0; i < addi; i++) {
			this.stationList.add(new Station("addi"+i));
		}
		for (int i = 0; i < nand; i++) {
			this.stationList.add(new Station("nand"+i));
		}
	}
	
	public boolean ifFull(){
		return this.size == stationList.size();
	}
	
	
}
