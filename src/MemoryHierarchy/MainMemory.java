package MemoryHierarchy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


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
	
	public void storeBlock(int address, int[][] data) {
		int startingAddress = address/data.length*data.length;
		for(int i = 0; i < data.length; i++) {
			if (data[i] == null) continue;
			memory.put(startingAddress + i, data[i]);
		}
	}
	
	public void printMainMemory() {
		Set<Integer> st = memory.keySet();
		for(Integer x : st) {
			System.out.println("address " + x + ": " + Arrays.toString(memory.get(x)));
		}
	}
 
}
