package MemoryHierarchy;
import java.util.HashMap;


public class MainMemory {
	
	private HashMap<Integer, Integer> memory;
	
	public MainMemory() {
		memory = new HashMap<Integer, Integer>();
	}
	
	public void addEntry(int address, int value) {
		memory.put(address, value);
	}
	
	public int[] fetchData(int address, int blockSize) {
		int[] result = new int [blockSize];
		int startAddress = address/blockSize*blockSize;
		for(int i = 0; i < blockSize; i++) {
			if (memory.containsKey(startAddress + i)) {
				result[i] = memory.get(startAddress + i);
			} else {
				result[i] = 0;
			}
		}
		return result;
	}
 
}
