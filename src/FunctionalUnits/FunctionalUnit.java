package FunctionalUnits;

public abstract class FunctionalUnit {
	protected int latency;
	public FunctionalUnit(int latency){
		this.latency = latency;
	}
	public abstract int[] doOperation(int[] x, int []y);
	public abstract int getLatency();
	public abstract void  setLatency(int x);
}
