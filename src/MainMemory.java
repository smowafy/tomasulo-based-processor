import java.util.HashMap;


public class MainMemory {
	
	private HashMap<Integer, Integer> memory;
	
	public MainMemory() {
		memory = new HashMap<Integer, Integer>();
	}
	
	public void addEntry(int address, int value) {
		memory.put(address, value);
	}

}
