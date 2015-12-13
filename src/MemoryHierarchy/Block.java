package MemoryHierarchy;

import java.util.Arrays;

public class Block {
	private boolean valid;
	private int tag;
	private int[][] data;
	private int cacheSize;
	private int cacheAssociativity;
	private int blockSize;
	private boolean dirty;
	private int orgAddress;
	
	public Block(int assoc, int cachesz, int blockSizeInBytes) {
		valid = false;
		dirty = false;
		tag = 0;
		blockSize = blockSizeInBytes;
		cacheSize = cachesz;
		data = new int[blockSizeInBytes][];
		cacheAssociativity = assoc;
	}
	
	public int[][] checkForData(int address) {
		int addressTag = address/cacheAssociativity/data.length;
		if (valid && addressTag == tag) {
			return data;
		} else {
			return null;
		}
	}
	
	public void writeData(int address, int[][] data) {
		valid = true;
		tag = address/cacheAssociativity/this.data.length;
		this.data = data;
		orgAddress = address;
	}
	
	public void writeWord(int address, int[] data) {
		valid = true;
		int offset = address % blockSize;
		this.data[offset] = data;
	}
	
	public boolean checkValid(int address) {
		int addressTag = address/cacheAssociativity/data.length;
		if (valid && addressTag == tag) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public void printBlock() {
		for(int i = 0; i < data.length; i++) {
			if (data[i] == null) {
				System.out.println("---");
			} else {
				System.out.println(Arrays.toString(data[i]));
			}
		}
	}
	
	
	
	public int[][] getData() {
		return data;
	}
	
	public int getOrgAddress() {
		return orgAddress;
	}
	

	
	
}
