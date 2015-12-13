package MemoryHierarchy;

import java.util.Arrays;

public class TestingMemory {
	public static void main(String[] args) {
		int[] assocs = new int [1];
		int[] cacheSizes = new int[1];
		int[] blockSizes = new int[1];
		int[] latencies = new int[1];
		assocs[0] = 1;
		cacheSizes[0] = 16;
		blockSizes[0] = 2;
		latencies[0] = 100;
		MemorySet memorySet = new MemorySet(1, assocs, cacheSizes, blockSizes, latencies, 1);
		int[] word1 = new int[16];
		int[] word2 = new int[16];
		for(int i = 0; i < 16; i++) {
			word2[i] = (i % 2);
		}
		Arrays.fill(word1, 1);
		memorySet.storeWord(4, word1);
		memorySet.getMainMemory().printMainMemory();
		memorySet.storeWord(4,  word2);
		memorySet.getMainMemory().printMainMemory();
		memorySet.storeWord(20, word1);
		memorySet.getMainMemory().printMainMemory();
	}
}
