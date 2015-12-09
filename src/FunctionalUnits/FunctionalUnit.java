package FunctionalUnits;

public abstract class FunctionalUnit {
	
	private int[] result;
	
	public abstract int[] doOperation(int[] x, int []y);
	public abstract int getLatency();
	public int[] getResult() {
		return result;
	}
	public void setResult(int[] result) {
		this.result = result;
	}
	public void doOpAndSave(int[] x, int[] y) {
		this.result = doOperation(x, y);
	}
	
}
