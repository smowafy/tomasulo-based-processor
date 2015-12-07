package inputOutput;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class InputOutput {

	BufferedReader br;

	public InputOutput() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Specify the number of cache levels...");
//		try {
		int n = Integer.parseInt(br.readLine());
//		} 
//		catch(IOException e) {
//			
//		}
		
		for(int i = 0; i < n; i++) {
			System.out.println("Cache level "+ (i+1)+":");
			System.out.println("	Cache total size: ");
			int s = Integer.parseInt(br.readLine());
			System.out.println("	Cache line width: ");
			int l = Integer.parseInt(br.readLine());
			System.out.println("	Cache associativity(maximum of "+ (s/l)+"): ");
			int m = Integer.parseInt(br.readLine());
			
			System.out.println("	Cache hit time: ");
			int hitTime = Integer.parseInt(br.readLine());
			
			System.out.println("	Cache miss penalty: ");
			int missPenalty = Integer.parseInt(br.readLine());
			
			System.out.println("	"+s+" "+l+" "+m+"/"+(s/l)+" "+hitTime+" "+missPenalty);
			
			// Send new cache to processor
			System.out.println();
		}
		
		System.out.println("Main memory access time: ");
		int mainMemoryAccessTime = Integer.parseInt(br.readLine());
		// Send memory access time to processor
		
		System.out.println();
		
		System.out.println("Specify number of entries inside Instruction Buffer: ");
		int instBufferSize = Integer.parseInt(br.readLine());
		// Send Instruction Buffer size to processor
		
		System.out.println("Specify number of entries inside Reorder Buffer: ");
		int ROBSize = Integer.parseInt(br.readLine());
		// Send ROB size to processor
		
		System.out.println();
		
		System.out.println("Reservation stations:");
		System.out.println("	Load FUs");
		int loadFUs = Integer.parseInt(br.readLine());
		System.out.println("	Load FU execution time");
		int loadFUExTime = Integer.parseInt(br.readLine());

		System.out.println("	Store FUs");
		int StoreFUs = Integer.parseInt(br.readLine());
		System.out.println("	Store FU exection time");
		int storeFUExTime = Integer.parseInt(br.readLine());
		
		System.out.println("	Addition FUs");
		int addFUs = Integer.parseInt(br.readLine());
		System.out.println("	Add FU execution time");
		int addFUExTime = Integer.parseInt(br.readLine());
		
		System.out.println("	Multiplication FUs");
		int multFUs = Integer.parseInt(br.readLine());
		System.out.println("	Multiplication FU execution time");
		int multFUExTime = Integer.parseInt(br.readLine());
		
		// Send Reservation stations to processors
		
		System.out.println();
		System.out.println("Program:");
		System.out.println("	Specify starting address:");
		String startingAddress = br.readLine();
		
		System.out.println("	 Enter program (in the form 'op dest src trg' without quotations, empty line at the end): ");
		ArrayList<String> program = new ArrayList<String>();
		String s;
		while((s = br.readLine()) != null && s.length() > 0) {
			program.add(s);
		}
		// Send program to processor
		
		System.out.println();
		System.out.println("Data:");
		System.out.println("	Specify data and address in the form 'data address' without quotations, address in haxadecimal, and empty line at the end: ");
		ArrayList<String> data = new ArrayList<String>();

		s = null;
		while((s = br.readLine()) != null && s.length() > 0) {
			data.add(s);

		}
		// Send data to processor
		
	}
	
	
	public static void main(String[] args) {
		
		try {
			new InputOutput();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
		}
	}
}
