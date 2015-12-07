package MemoryHierarchy;
import java.util.HashMap;


public class MainMemory {
	
	private HashMap<Integer, int[]> memory;
	
	public MainMemory() {
		memory = new HashMap<Integer, int[]>();
	}
	
	public void addEntry(int address, int[] value) {
		memory.put(address, value);
	}
	
	public int[][] fetchData(int address, int blockSize) {
		int[][] result = new int [blockSize][];
		int startAddress = address/blockSize*blockSize;
		for(int i = 0; i < blockSize; i++) {
			if (memory.containsKey(startAddress + i)) {
				result[i] = memory.get(startAddress + i);
			} else {
				result[i] = null;
			}
		}
		return result;
	}
 
}
