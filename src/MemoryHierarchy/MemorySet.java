package MemoryHierarchy;
import java.util.LinkedList;


public class MemorySet {
	public static int cycles;
	
	private MainMemory mainMemory;
	private LinkedList<Cache> caches;
	
	
	public MemorySet(int numberOfLevels) {
		cycles = 0;
		mainMemory = new MainMemory();
		caches = new LinkedList<Cache>();
		int x, y, z, cyc;
		x =  y = z = cyc = 0;
		
		for(int i = 0; i < numberOfLevels - 1; i++) {
			caches.add(new Cache(x, y, z, cyc));
			if (i > 0) {
				caches.get(i - 1).setHigherLevel(caches.getLast());
			}
		}
		caches.getLast().setMainMemory(mainMemory);
	}
	
	public int[] getWord(int address) {
		cycles = 0;
		int[][] tmpBlock = caches.getFirst().getBlock(address);
		return tmpBlock[address % caches.getFirst().getBlockSize()];
	}
	
	
	
	public int getNumberOfCyclesSpent() {
		return cycles;
	}
	
	
	
}
