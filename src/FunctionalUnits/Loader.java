package FunctionalUnits;

import Registers.Registers;
import Tomasulo.Processor;

public class Loader implements FunctionalUnit{
	private int latency;
	
	public int getLatency() {
		return latency;
	}
	public void setLatency(int latency) {
		this.latency = latency;
	}
	@Override
	public int[] doOperation(int[] x, int[] y) {
		int address = Registers.intArrayToInt(x);
		return Processor.getProcessor().getDataMemory().getBlock(address); //getBlock to be changed
	}
	

}
