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
	
	public boolean checkValid(int address) {
		int index = (address/blockSize)%setCount;
		return sets.get(index).checkValid(address);
	}
	
	public int[][] getBlock(int address) {
		MemorySet.cycles += cycleCount;
		int index = (address/blockSize)%setCount;
		int[][] probableData = sets.get(index).checkForData(address);
		if (probableData != null) return probableData;
		else {
			if (sets.get(index).checkValid(address) && sets.get(index).checkDirtyBlock(address)) {
				this.forWriteAllocate(address, this.getBlock(address));
			}
			if (higherLevel == null) {
				int[][] toBeSaved = mainMemory.fetchData(address, blockSize);
				sets.get(index).writeData(address, toBeSaved);
				return toBeSaved;
			} else {
				int[][] upperCacheBlock = higherLevel.getBlock(address);
				int startAddress = address/upperCacheBlock.length*upperCacheBlock.length;
				int myStartAddress = address/blockSize*blockSize;
				int offset = myStartAddress - startAddress;
				int[][] dataToBeSaved = new int[blockSize][];
				for(int i = 0; i < blockSize; i++) {
					dataToBeSaved[i] = upperCacheBlock[i + offset];
				}
				sets.get(index).writeData(address, dataToBeSaved);
				return dataToBeSaved;
			}
		}
	}
	
	public void forWriteAllocate(int address, int[][] data) {
		int index = (address/blockSize)%setCount;
		/*sets.get(index).writeWord(address, data);
		if (higherLevel == null) {
			mainMemory.addEntry(address, data);
		} else {
			higherLevel.forWriteAllocate(address, data);
		}*/
		
		int[][] thisCacheBlock = this.getBlock(address);
		int startAddress = address/blockSize*blockSize;
		int myStartAddress = address/data.length*data.length;
		int offset = myStartAddress - startAddress;
		//int[][] dataToBeSaved = new int[blockSize][];
		for(int i = 0; i < blockSize; i++) {
			//dataToBeSaved[i] = upperCacheBlock[i + offset];
			thisCacheBlock[i + offset] = data[i];
		}
		if (higherLevel == null) {
			/*for(int i = 0; i < data.length; i++) {
				mainMemory.addEntry(address + i, data[i]);
			}*/
			mainMemory.storeBlock(address, data);
		} else {
			higherLevel.forWriteAllocate(address, data);
		}
	}
	

	
	
	
	public void writeData(int address, int[][] data) {
		int index = (address/blockSize)%setCount;
		boolean writeThrough = MemorySet.writeThrough;
		if (sets.get(index).checkValid(address)) {
			// Write hit
			// choose write through or write back according to policy
			if (writeThrough) {
				sets.get(index).writeData(address, data);
				if (higherLevel == null) {
					//Highest level of cache, store in memory
					mainMemory.storeBlock(address, data);
				} else {
					higherLevel.writeData(address, data);
				}
			} else {
				//write-back
				
				sets.get(index).writeData(address, data);
				sets.get(index).setDirty(address);
			}
		} else {
			//Write miss
			if (writeThrough) {
				if (higherLevel == null) {
					mainMemory.storeBlock(address, data);
				} else {
					higherLevel.writeData(address, data);
				}
			} else {
				if (higherLevel == null) {
					mainMemory.storeBlock(address, data);
					getBlock(address);
				} else {
					higherLevel.writeData(address, data);
					getBlock(address);
				}
				/*sets.get(index).writeData(address, data);
				sets.get(index).setDirty(address);*/
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
	
	
	public int getBlockSize() {
		return blockSize;
	}
	
	
	public void printCache() {
		System.out.println("Cache begin:");
		for(Set tmp : sets) {
			tmp.printSet();
		}
		System.out.println("Cache end.");
	}
	

	
	
}
