package Parser;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Registers.Registers;

public class parser {
	static ArrayList<int []> opcode = new ArrayList<int []>();
	static String Rs, Rd, Rt, imm;
	
	int startAddress;
	
	
	public int[] getInstOpCode(int address) {
		return opcode.get(address - startAddress);
	}
	
	
	
	public parser() {
		
	}
	//constructor
	public parser(ArrayList<String> program, int startAddress)
	{
		/*passes on the lines of instructions of the program written by the user
		 * stored in the arraylist called program and brings back the 
		 * opcode of the instruction ليتغذى عليه جسم الانسان, stored in the opcode arraylist
		 * in the same order.
		 * CONVENTIONS: -instructions are written in Upper case
		 * 				-space separated tokens*/
		this.startAddress = startAddress;
		for(int i = 0; i<program.size(); i++)
		{
			opcode.add(new int[16]);
			StringTokenizer st = new StringTokenizer(program.get(i));
			String instruction= st.nextToken();
			switch (instruction)
			{
			case "ADD":
			case "SUB":
			case "NAND":
			case "MUL": for(int j = 0; j<3; j++)
						{
							opcode.get(i)[j] = 0;
						}
			 Rd = st.nextToken();
			if(Rd.equals("R0"))
			{
				System.out.println("You can not write into R0");
				break;
			}
			Rs = st.nextToken();
			Rt = st.nextToken();
			opCoder(Rd, Rs, Rt, i);
			func(instruction, i);
			break;
			case "ADDI": for(int j = 0; j<2; j++)
						{
							opcode.get(i)[j] = 0;
						}
							opcode.get(i)[2]= 1;
			Rd = st.nextToken();
			Rs = st.nextToken();
			imm = st.nextToken();
			funcI(Rd, Rs, imm, i);
							break;
			case "LW": opcode.get(i)[0]= 0;
					   opcode.get(i)[1]= 1;
					   opcode.get(i)[2]= 2;
			Rd = st.nextToken();
			Rs = st.nextToken();
			imm = st.nextToken();
			funcI(Rd, Rs, imm, i);
					   break;
			case "SW": opcode.get(i)[0] = 0;
					   opcode.get(i)[1]= 1;
					   opcode.get(i)[2]= 1;
			Rd = st.nextToken();
			Rs = st.nextToken();
			imm = st.nextToken();
			funcI(Rd, Rs, imm, i);
					   break;
			case "JMP": opcode.get(i)[0]= 1;
						opcode.get(i)[1] = 0;
						opcode.get(i)[2]= 0;
						break;
			case "BEQ": opcode.get(i)[0]= 1;
						opcode.get(i)[1]= 0;
						opcode.get(i)[2]= 1;
						break;
			case "JALR": opcode.get(i)[0]= 1;
						 opcode.get(i)[1]= 1; 
						 opcode.get(i)[2]= 0;
						 break;
			case "RET": opcode.get(i)[0]= 1;
						opcode.get(i)[1]= 1;
						opcode.get(i)[2]= 1;
						break;
			//TODO: opcode of the jump and branching instructions are to be added.
			default: System.out.println("invalid command");break;
			}
		}
		
	}
	private void funcI(String Rd, String Rs, String immediate, int ArrIdx) {
		String source = checkRegister(Rs);
		String destination = checkRegister(Rd);
		int index = 3;
		
		for(int i = 0; i<3; i++)
		{
			if(!source.equals("Wrong register number"))
			{
				opcode.get(ArrIdx)[index]= (int)(source.charAt(i)-'0');
				index++;
			}
			else
			{
				System.out.println("Wrong register number");
				break;
			}
		}
		
		for(int i = 0; i<3; i++)
		{
			if(!destination.equals("Wrong register number"))
			{
				opcode.get(ArrIdx)[index]= (int)(destination.charAt(i)-'0');
				index++;
			}
			else
			{
				System.out.println("Wrong register number");
				break;
			}
		}
		calcImm(immediate, ArrIdx);
	}
	public void calcImm(String immediate, int ArrIdx)
	{
		int result = Integer.parseInt(immediate);
		int [] binary = new int[16]; 
		int [] answer = new int [7];
		boolean comp = false;
		if((result<64) &&(result>=0)){
			
			binary = Registers.IntToIntArray(result);
			for(int i = binary.length-1; i>8; i--)
			{
				answer[(i%10)+1]= binary[i];
			}
			
		}
		else if((result<0) && (result>-65))
		{
			binary = Registers.IntToIntArray(Math.abs(result));
			for(int i = binary.length-1; i>8; i--)
			{
				answer[(i%10)+1]= binary[i];
			}
			for(int i = answer.length-1; i>=0; i--)
			{
				if( (answer[i]==0) && !comp)
				{
					answer[i] = 0;
				}
				if((answer [i]==1) && !comp)
				{
					comp = true;
					answer [i]= 1;
				}
				if((answer[i]==1) && comp)
				{
					answer[i] = 0;
				}
				if((answer[i]==0) && comp)
				{
					answer[i] = 1;
				}
				
			}
		}
		/*adding the value of the immediate calculated to the last 7 bits 
		of the opcode of the instruction*/
		int index = 9;
		for(int i = 0; i<8; i++)
		{
			
			opcode.get(ArrIdx)[index] = answer[i];
			index++;
		}
		
	}
	/*opCoder is used to enter the registers of the instructions in the 
	opcode of the instruction*/
	public void opCoder(String Rd, String Rs, String Rt, int ArrIdx)
	{
		String destination = checkRegister(Rd);
		String source = checkRegister(Rs);
		String target = checkRegister(Rt);
		int index = 3;
		for(int i = 0; i<3; i++)
		{
			if(!source.equals("Wrong register number"))
			{
				opcode.get(ArrIdx)[index]= (int)(source.charAt(i)-'0');
				index++;
			}
			else
			{
				System.out.println("Wrong register number");
				break;
			}
		}
		for(int i = 0 ; i<3 ; i++)
		{
			if(!target.equals("Wrong register number"))
			{
				opcode.get(ArrIdx)[index]= (int)(target.charAt(i)-'0');
				index++;
			}
			else
			{
				System.out.println("Wrong register number");
				break;
			}
		}
		for(int i = 0 ; i<3 ; i++)
		{
			if(!destination.equals("Wrong register number"))
			{
				opcode.get(ArrIdx)[index]= (int)(destination.charAt(i)-'0');
				index++;
			}
			else
			{
				System.out.println("Wrong register number");
				break;
			}
		}
	}
	//func is used to enter the function in the opcode of the arithmetic functions
	public void func(String instruction, int ArrIdx)
	{
		int index = 12;
	
		switch (instruction)
		{
		case "ADD":
			for(int i = 0; i<4; i++)
			{
				opcode.get(ArrIdx)[index] = 0;
			}break;
		case "SUB":
			opcode.get(ArrIdx)[12]= 0;
			opcode.get(ArrIdx)[13]= 0;
			opcode.get(ArrIdx)[14] = 0;
			opcode.get(ArrIdx)[15] = 1;
			break;
		case "NAND":
			opcode.get(ArrIdx)[12]= 0;
			opcode.get(ArrIdx)[13]= 0;
			opcode.get(ArrIdx)[14] = 1;
			opcode.get(ArrIdx)[15] = 0;
			break;
		case "MUL":
			opcode.get(ArrIdx)[12]= 0;
			opcode.get(ArrIdx)[13]= 0;
			opcode.get(ArrIdx)[14] = 1;
			opcode.get(ArrIdx)[15] = 1;
			break;
		default: break;
		}
	}
	public String checkRegister(String reg)
	{
		switch(reg)
		{
			case "R0": return "000";
			case "R1": return "001";
			case "R2": return "010";
			case "R3": return "011";
			case "R4": return "100";
			case "R5": return "101";
			case "R6": return "110";
			case "R7": return "111";
			default: return "Wrong register number";
		}
	}
}