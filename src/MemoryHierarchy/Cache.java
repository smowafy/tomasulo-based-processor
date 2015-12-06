package MemoryHierarchy;

import java.util.ArrayList;

public class Cache {

	private ArrayList<Set> sets;
	private int cacheAssociativity;
	private int cacheSize;
	private int blockSize;
	private int setCount;
	private int blockCount;
	private int cycleCount;
	private Cache higherLevel;
	private MainMemory mainMemory;

	public Cache(int assoc, int cachesz, int blocksz, int cycles) {
		cacheAssociativity = assoc;
		cacheSize = cachesz;
		blockSize = blocksz;
		cycleCount = cycles;
		blockCount = cacheSize / blockSize;
		setCount = blockCount / cacheAssociativity;
		sets = new ArrayList<Set>();
		for (int i = 0; i < setCount; i++) {
			sets.add(new Set(assoc, cachesz, blocksz));
		}
	}
	
	public int[] getBlock(int address) {
		MemorySet.cycles += cycleCount;
		int index = (address/blockSize)%setCount;
		int[] probableData = sets.get(index).checkForData(address);
		if (probableData != null) return probableData;
		else {
			if (higherLevel == null) {
				int[] toBeSaved = mainMemory.fetchData(address, blockSize);
				sets.get(index).writeData(address, toBeSaved);
				return toBeSaved;
			} else {
				int[] upperCacheBlock = higherLevel.getBlock(address);
				int startAddress = address/upperCacheBlock.length*upperCacheBlock.length;
				int myStartAddress = address/blockSize*blockSize;
				int offset = myStartAddress - startAddress;
				int[] dataToBeSaved = new int[blockSize];
				for(int i = 0; i < blockSize; i++) {
					dataToBeSaved[i] = upperCacheBlock[i + offset];
				}
				sets.get(index).writeData(address, dataToBeSaved);
				return dataToBeSaved;
			}
		}
	}

	public Cache getHigherLevel() {
		return higherLevel;
	}

	public void setHigherLevel(Cache higherLevel) {
		this.higherLevel = higherLevel;
	}

	public MainMemory getMainMemory() {
		return mainMemory;
	}

	public void setMainMemory(MainMemory mainMemory) {
		this.mainMemory = mainMemory;
	}
	
	
	

	
	
}
