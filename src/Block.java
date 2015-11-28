
public class Block {
	private boolean valid;
	private int tag;
	private int[] data;
	private int cacheSize;
	private int cacheAssociativity;
	private int blockSize;
	
	public Block(int assoc, int cachesz, int blockSizeInBytes) {
		valid = false;
		tag = 0;
		blockSize = blockSizeInBytes;
		cacheSize = cachesz;
		data = new int[blockSizeInBytes];
		cacheAssociativity = assoc;
	}
	
	public int checkForData(int address) {
		int addressTag = address/cacheAssociativity/data.length;
		if (valid && addressTag == tag) {
			return data[address%data.length];
		} else {
			return -1;
		}
	}
	
	public void writeData(int address, int data) {
		valid = true;
		tag = address/cacheAssociativity/this.data.length;
		this.data[address%this.data.length] = data;
	}
	
	
}
