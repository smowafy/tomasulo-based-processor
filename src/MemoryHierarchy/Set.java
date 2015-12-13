package MemoryHierarchy;
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
	
	public int[][] checkForData(int address) {
		for(Block tmp : blocks) {
			int[][] probableData = tmp.checkForData(address);
			if (probableData != null) return probableData;
		}
		return null;
	}
	
	public boolean checkValid(int address) {
		for(Block tmp : blocks) {
			boolean chk = tmp.checkValid(address);
			if (chk) return true;
		}
		return false;
	}
	public void setDirty(int address) {
		for(Block tmp : blocks) {
			boolean chk = tmp.checkValid(address);
			if (chk) tmp.setDirty(true);
			return;
		}
	}
	public void clearDirty(int address) {
		for(Block tmp : blocks) {
			boolean chk = tmp.checkValid(address);
			if (chk) tmp.setDirty(false);
			return;
		}
	}
	
	public boolean checkDirtyBlock(int address) {
		for(Block tmp : blocks) {
			boolean chk = tmp.checkValid(address);
			if (chk) {
				return tmp.isDirty();
			}
		}
		return false;
	}
	
	public void writeData(int address, int[][] data) {
		if (blocks.get(0).isDirty()) {
			MemorySet.firstCache.forWriteAllocate(blocks.get(0).getOrgAddress(), blocks.get(0).getData());
		}
		blocks.get(0).setDirty(false);
		blocks.get(0).writeData(address, data);
	}
	
	public void writeWord(int address, int[] data) {
		//TODO block choosing policy
		blocks.get(0).writeWord(address, data);
	}
	
	public void printSet() {
		System.out.println("\tSet begin:");
		for(Block tmp : blocks) {
			tmp.printBlock();
		}
		System.out.println("\tSet End.");
	}
	
}
