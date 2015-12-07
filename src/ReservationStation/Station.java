package ReservationStation;
import FunctionalUnits.Adder;
import FunctionalUnits.FunctionalUnit;
import FunctionalUnits.Loader;
import FunctionalUnits.Multiplier;
import FunctionalUnits.Nand;
import FunctionalUnits.Subtracter;
import Instructions.Instruction;


public abstract class Station {
	private String name;
	private boolean busy;
	private Instruction ins; // ins object
	private int[] vJ; // if int change the funits inputs to int
	private int[] vK;
	private int qJ;
	private int qK;
	private int dest;
	private int address;
	private FunctionalUnit funit;

	public Station(String name){
		this.name = name;
	}
	public abstract void issue(Instruction x);
	
	public String getName(){
		return this.name;
	}
	public boolean getBusy(){
		return this.busy;
	}
	public FunctionalUnit getFunit() {
		return funit;
	}
	public void setFunit(FunctionalUnit funit) {
		this.funit = funit;
	}
	public Instruction getIns() {
		return ins;
	}
	public void setIns(Instruction ins) {
		this.ins = ins;
	}
	public int[] getvJ() {
		return vJ;
	}
	public void setvJ(int[] vJ) {
		this.vJ = vJ;
	}
	public int[] getvK() {
		return vK;
	}
	public void setvK(int[] vK) {
		this.vK = vK;
	}
	public int getqJ() {
		return qJ;
	}
	public void setqJ(int qJ) {
		this.qJ = qJ;
	}
	public int getqK() {
		return qK;
	}
	public void setqK(int qK) {
		this.qK = qK;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
}
