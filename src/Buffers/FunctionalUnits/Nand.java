package FunctionalUnits;


public class Nand extends FunctionalUnit{
	
	public Nand(int latency) {
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
		int [] result = new int[x.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.abs(x[i]-y[i]);
		}
		return result;
	}

}
