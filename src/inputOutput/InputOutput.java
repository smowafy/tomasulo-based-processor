package inputOutput;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import Buffers.InstructionBuffer;
import Buffers.ReorderBuffer;
import MemoryHierarchy.MemorySet;
import Registers.RegisterStat;
import Registers.Registers;
import ReservationStation.ReservationStations;
import Tomasulo.Processor;


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
		int[] sizes = new int[n];
		int[] widths = new int[n];
		int[] associativities = new int[n];
		int[] hitTimes = new int[n];
		int[] missPenalties = new int[n];
		for(int i = 0; i < n; i++) {
			System.out.println("Cache level "+ (i+1)+":");
			System.out.println("	Cache total size: ");
			int s = Integer.parseInt(br.readLine());
			sizes[i] = s;
			System.out.println("	Cache line width: ");
			int l = Integer.parseInt(br.readLine());
			widths[i] = l;
			System.out.println("	Cache associativity(maximum of "+ (s/l)+"): ");
			int m = Integer.parseInt(br.readLine());
			associativities[i] = m;
			System.out.println("	Cache hit time: ");
			int hitTime = Integer.parseInt(br.readLine());
			hitTimes[i] = hitTime;
			System.out.println("	Cache miss penalty: ");
			int missPenalty = Integer.parseInt(br.readLine());
			missPenalties[i] = missPenalty;
			System.out.println("	"+s+" "+l+" "+m+"/"+(s/l)+" "+hitTime+" "+missPenalty);
			
			// Send new cache to processor
			System.out.println();
		}
		
		System.out.println("Specify Writing policy 0 for Write through, 1 for write back");
//		try {
		int policy = Integer.parseInt(br.readLine());
		
		
		System.out.println("Main memory access time: ");
		int mainMemoryAccessTime = Integer.parseInt(br.readLine());
		// Send memory access time to processor
		
		
		System.out.println();
		
		System.out.println("Specify number of entries inside Instruction Buffer: ");
		int instBufferSize = Integer.parseInt(br.readLine());
		// Send Instruction Buffer size to processor
		InstructionBuffer instBuffer = new InstructionBuffer(instBufferSize);
		System.out.println("Specify number of entries inside Reorder Buffer: ");
		int ROBSize = Integer.parseInt(br.readLine());
		// Send ROB size to processor
		ReorderBuffer rob = new ReorderBuffer(ROBSize);
		System.out.println();
		
		System.out.println("Reservation stations:");
		System.out.println("	Load FUs");
		int loadFUs = Integer.parseInt(br.readLine());
		System.out.println("	Load FU execution time");
		int loadFUExTime = Integer.parseInt(br.readLine());

		System.out.println("	Store FUs");
		int storeFUs = Integer.parseInt(br.readLine());
		System.out.println("	Store FU exection time");
		int storeFUExTime = Integer.parseInt(br.readLine());
		
		System.out.println("	Addition FUs");
		int addFUs = Integer.parseInt(br.readLine());
		System.out.println("	Add FU execution time");
		int addFUExTime = Integer.parseInt(br.readLine());
		
		System.out.println("	Subtraction FUs");
		int subFUs = Integer.parseInt(br.readLine());
		System.out.println("	Sub FU execution time");
		int subFUExTime = Integer.parseInt(br.readLine());
		
		System.out.println("	Multiplication FUs");
		int multFUs = Integer.parseInt(br.readLine());
		System.out.println("	Multiplication FU execution time");
		int multFUExTime = Integer.parseInt(br.readLine());
		
		System.out.println("	Add Immediate FUs");
		int addiFUs = Integer.parseInt(br.readLine());
//		System.out.println("	Addi FU execution time");
//		int addiFUExTime = Integer.parseInt(br.readLine());
		
		System.out.println("	NAND Immediate FUs");
		int nandFUs = Integer.parseInt(br.readLine());
		System.out.println("	NAND FU execution time");
		int nandFUExTime = Integer.parseInt(br.readLine());
		
		// Send Reservation stations to processors
		ReservationStations rs = new ReservationStations(loadFUs, storeFUs, addFUs, subFUs, multFUs, addiFUs, nandFUs, 
				addFUExTime, subFUExTime, multFUExTime, nandFUExTime);
		
		
		System.out.println();
		System.out.println("Program:");
		System.out.println("	Specify starting address:");
		int startingAddress = Integer.parseInt(br.readLine());
		
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
//		ArrayList<String> data = new ArrayList<String>();
		StringTokenizer st;
		s = null;
		HashMap<Integer, int[]> programData = new HashMap<Integer, int[]>();
		while((s = br.readLine()) != null && s.length() > 0 && (st = new StringTokenizer(s)).countTokens() == 2) {
			int[] data = Registers.IntToIntArray(Integer.parseInt(st.nextToken()));
			int address = Integer.parseInt(st.nextToken());
			programData.put(address, data);

		}
		MemorySet memSet = new MemorySet(n, associativities, sizes, widths, hitTimes, policy, mainMemoryAccessTime, programData);
		// Send data to processor
		Processor.getProcessor().initProcessor(startingAddress, memSet, instBuffer, rs, rob, new Registers(), new RegisterStat(8));
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
