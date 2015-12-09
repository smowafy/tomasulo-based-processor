package FunctionalUnits;

import Registers.Registers;
import Tomasulo.Processor;

public class Loader extends FunctionalUnit{
	
	public Loader(int latency) {
		super(latency);
	}

	@Override
	public int[] doOperation(int[] x, int[] y) {
		int a = Registers.intArrayToInt(x);
		int b = Registers.intArrayToInt(y);
		int address = a+b;
		int [] result = Processor.getProcessor().getDataMemory().getWord(address); 
		// adding number of cycles spent to read the data in immediate address
		this.setLatency(this.getLatency()+Processor.getProcessor().getDataMemory().getNumberOfCyclesSpent());
		return result;//getBlock to be changed
	}

	public int getLatency() {
		return this.latency;
	}
	public void setLatency(int x) {
		this.latency = x;
	}
	
	// nzbat latency // memory // pc 
}
