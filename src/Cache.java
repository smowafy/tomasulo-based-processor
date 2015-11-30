import java.util.ArrayList;

public class Cache {

	private ArrayList<Set> sets;
	private int cacheAssociativity;
	private int cacheSize;
	private int blockSize;
	private int setCount;
	private int blockCount;
	private Cache higherLevel;
	private MainMemory mainMemory;

	public Cache(int assoc, int cachesz, int blocksz) {
		cacheAssociativity = assoc;
		cacheSize = cachesz;
		blockSize = blocksz;
		blockCount = cacheSize / blockSize;
		setCount = blockCount / cacheAssociativity;
		sets = new ArrayList<Set>();
		for (int i = 0; i < setCount; i++) {
			sets.add(new Set(assoc, cachesz, blocksz));
		}
	}
	
	public int[] getBlock(int address) {
		int index = (address/blockSize)%setCount;
		int[] probableData = sets.get(index).checkForData(address);
		if (probableData != null) return probableData;
		else {
			if (higherLevel == null) {
				mainMemory.fetchData(address, blockSize);
				return null;
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
	

	
	
}
