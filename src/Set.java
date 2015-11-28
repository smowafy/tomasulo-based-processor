import java.util.LinkedList;


public class Set {
	private LinkedList<Block> blocks;
	private int cacheAssociativity;
	private int cacheSize;
	private int blockSize;
	
	public Set(int assoc, int cachesz, int blocksz) {
		cacheAssociativity = assoc;
		blockSize = blocksz;
		cacheSize = cachesz;
		blocks = new LinkedList<Block>();
		cacheAssociativity = assoc;
		for(int i = 0; i < assoc; i++) {
			blocks.add(new Block(assoc, cachesz, blocksz));
		}
	}
	
	public int checkForData(int address) {
		for(Block tmp : blocks) {
			int probableData = tmp.checkForData(address);
			if (probableData != -1) return probableData;
		}
		return -1;
	}
	
}
