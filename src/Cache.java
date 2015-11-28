import java.util.ArrayList;

public class Cache {

	private ArrayList<Set> sets;
	private int cacheAssociativity;
	private int cacheSize;
	private int blockSize;
	private int setCount;
	private int blockCount;
	private Cache higherLevel;
	private Cache lowerLevel;

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
	
	public int getData(int address) {
		int index = (address/blockSize)%setCount;
		int probableData = sets.get(index).checkForData(address);
		if (probableData != -1) return probableData;
		///TODO Handle misses by communicating with higher level cache 
		return -1;
	}
}
