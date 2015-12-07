package FunctionalUnits;

import Registers.Registers;


public class Adder implements FunctionalUnit{
	private int latency;
	
	public int getLatency() {
		return latency;
	}
	public void setLatency(int latency) {
		this.latency = latency;
	}

	@Override
	public int[] doOperation(int []x, int []y) {
		// TODO Auto-generated method stub
		int a = Registers.intArrayToInt(x);
		int b = Registers.intArrayToInt(y);
		
		return Registers.IntToIntArray(a+b);
	}


}
