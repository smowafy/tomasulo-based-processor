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
	
	public int[] getBlock(int address) {
		cycles = 0;
		int threshold = (1 << 8);
		return caches.getFirst().getBlock(address);
	}
	
	public int getNumberOfCyclesSpent() {
		return cycles;
	}
	
	
	
}
