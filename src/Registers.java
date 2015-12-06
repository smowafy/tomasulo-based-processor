import java.util.HashMap;

//most significant bit is the 0 index of an array and bits are converted in 2's complement
public class Registers {
    
    HashMap<String, int[]> Registers;
    
    //constructor
    public Registers()
    {
        Registers = new HashMap<String, int[]>();
        //initializing the registers
        for(int i = 0; i< 9; i++)
        {
            String Reg_name;
            Reg_name = "R"+i ;
            Registers.put(Reg_name, new int [16]);
        }
        for(int x=0; x<Registers.get("R0").length-1; x++)
        {
            Registers.get("R0")[x]= 0;
        }
    }
    //----------------------------------------------------------------------------------
    //clear the register given in the function
    public void clearReg(String regName)
    {
        for(int x=0; x< this.Registers.get(regName).length-1; x++)
        {
            this.Registers.get(regName)[x]= 0;
        }
    }
    //----------------------------------------------------------------------------------
    //write to the register given the data needed to be written and the register name
    public void writeToReg(int [] data, String regName)
    {
        if(regName.equals("R0"))
        {
            System.out.println("You can not write to R0");
        }
        if(data.length> 16)
        {
            System.out.println("the data you are trying to input in the register is more"
                               + "than 16 bits");
        }
        else
        {
            for(int i = data.length-1; i>=0; i-- )
            {
                
                this.Registers.get(regName)[i]= data[i];
            }
            
            //
            
        }
    }
    //----------------------------------------------------------------------------------
    //converting binary to integer in 2's complement
    public static int intArrayToInt(int[] register)
    {
        int value = 0;
        int power= 0;
        for(int i = register.length-1; i>=0; i--)
        {
            if(i ==0)
            {
                value += (int) (register[i]*Math.pow(2, power))*-1;
            }
            else{
                value += (int) (register[i]*Math.pow(2, power));
                power+=1;
            }
        }
        return value;
    }
    //--------------------------------------------------------------------------------
    //converting integer to binary in 2's complement
    public static int [] IntToIntArray(int x)
    {
        int [] result = new int [16];
        String binary = Integer.toBinaryString(x);
        int character = binary.length()-1;
        for(int j = 15; j>=0; j--){
            result[j]= Character.getNumericValue(binary.charAt(character));
            character--;
            
        }
        return result;
    }
    
    //--------------------------------------------------------------------------------
    //main method
    public static void main(String[] args)
    {
        //new Registers();		
    }
    
    
}
