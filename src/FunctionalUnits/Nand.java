package FunctionalUnits;


public class Nand implements FunctionalUnit{

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
