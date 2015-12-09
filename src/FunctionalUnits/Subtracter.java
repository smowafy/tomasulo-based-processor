package FunctionalUnits;


public class Subtracter implements FunctionalUnit{

	@Override
	public int[] doOperation(int []x, int []y) {
		// TODO Auto-generated method stub
		int a = intArrayToInt(x);
		int b = intArrayToInt(y);
		
		return IntToIntArray(a-b);
	}
	public int [] IntToIntArray(int x)
	{
		int [] result = null;
		String binary = Integer.toBinaryString(x);
		for(int i = 0 ; i<binary.length()-1; i++)
		{
			result[i]= binary.charAt(i);
		}
		return result;
	}
	
	public int intArrayToInt(int[] register)
	{
		int value = 0;
		int power= 0;
		for(int i = register.length-1; i>=0; i--)
		{
			value += (int) (register[i]*Math.pow(2, power));
			power+=1;
		}
		return value;
	}

}
