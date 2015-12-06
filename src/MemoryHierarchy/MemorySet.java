package MemoryHierarchy;
import java.util.LinkedList;


public class MemorySet {
	public static int cycles;
	
	private MainMemory mainMemory;
	private LinkedList<Cache> caches;
	
	private Cache instructionCache, dataCache;
	
	public MemorySet(int numberOfLevels) {
		cycles = 0;
		mainMemory = new MainMemory();
		caches = new LinkedList<Cache>();
		int x, y, z, cyc;
		x =  y = z = cyc = 0;
		instructionCache = new Cache(x, y, z, cyc);
		dataCache = new Cache(x, y, z, cyc);
		for(int i = 0; i < numberOfLevels - 1; i++) {
			caches.add(new Cache(x, y, z, cyc));
			if (i > 0) {
				caches.get(i - 1).setHigherLevel(caches.getLast());
			} else {
				instructionCache.setHigherLevel(caches.getLast());
				dataCache.setHigherLevel(caches.getLast());
			}
		}
		caches.getLast().setMainMemory(mainMemory);
	}
	
	public int[] getBlock(int address) {
		cycles = 0;
		int threshold = (1 << 8);
		if (address >= threshold) {
			return instructionCache.getBlock(address);
		} else {
			return dataCache.getBlock(address);
		}
	}
	
	public int getNumberOfCyclesSpent() {
		return cycles;
	}
	
	
	
}
