package FunctionalUnits;

import Registers.Registers;


public class Adder extends FunctionalUnit{
	public Adder(int latency) {
		super(latency);
	}
	public int getLatency() {
		return this.latency;
	}
	public void setLatency(int x) {
		this.latency = x;
	}

	@Override
	public int[] doOperation(int []x, int []y) {
		// TODO Auto-generated method stub
		int a = Registers.intArrayToInt(x);
		int b = Registers.intArrayToInt(y);
		
		return Registers.IntToIntArray(a+b);
	}


}
