package MemoryHierarchy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;


public class MemorySet {
	public static int cycles;
	
	private MainMemory mainMemory;
	private LinkedList<Cache> caches;
	
	public static boolean writeThrough;
	
	public static Cache firstCache;
	
	
	//policy 0 --> write through
	
	
	public MemorySet(int numberOfLevels, int[] assocs, int[] cacheszs, int[] blockszs, int[] latencies, int policy, int mainMemoryLatency, HashMap<Integer, int[]> initialMemory) {
		if (policy == 0) {
			writeThrough = true;
		} else {
			writeThrough = false;
		}
		cycles = 0;
		mainMemory = new MainMemory(initialMemory, mainMemoryLatency);
		caches = new LinkedList<Cache>();
		//int x, y, z, cyc;
		
		for(int i = 0; i < numberOfLevels; i++) {
			caches.add(new Cache(assocs[i], cacheszs[i], blockszs[i], latencies[i]));
			if (i > 0) {
				caches.get(i - 1).setHigherLevel(caches.getLast());
			}
		}
		caches.getLast().setMainMemory(mainMemory);
		firstCache = caches.getFirst();
	}
	
	public int[] getWord(int address) {
		cycles = 0;
		int[][] tmpBlock = caches.getFirst().getBlock(address);
		printAllCaches();
		return tmpBlock[address % caches.getFirst().getBlockSize()];
	}
	
	
	public void storeWord(int address, int[] data) {
		cycles = 0;
		boolean chk = caches.getFirst().checkValid(address);
		//System.out.println("[MemorySet/storeWord] address = " + address + ", chk = " + chk);
		int offset = address % caches.getFirst().getBlockSize();
		if (chk) {
			int[][] tmpBlock = caches.getFirst().getBlock(address);
			tmpBlock[offset] = data;
			caches.getFirst().writeData(address, tmpBlock);
		} else {
			int[][] tmpBlock = new int[caches.getFirst().getBlockSize()][];
			for(int i = 0; i < tmpBlock.length; i++) {
				if (i == offset) continue;
			}
			tmpBlock[offset] = data;
			caches.getFirst().writeData(address, tmpBlock);
		}
		
		printAllCaches();
		
	}
	
	
	
	public int getNumberOfCyclesSpent() {
		return cycles;
	}
	
	public void printAllCaches() {
		System.out.println("All Caches begin");
		for(Cache tmp : caches) {
			tmp.printCache();
		}
		System.out.println("All Caches end");
	}
	
	public MainMemory getMainMemory() {
		return this.mainMemory;
	}
	
}
