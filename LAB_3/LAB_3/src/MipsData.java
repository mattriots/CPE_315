/*
  # Name: Matt DePauw
  # Section:  7
  # Description:  This program converts MIPS assembly code to binary.
  # BinaryData: This class holds all data for MIPS -> Binary Conversion
                This data is held in hashmaps
                Instrucition -> Opcode Binary
                Instruction  -> Function Binary
                Register     -> Binary
 */
import java.util.HashMap;
import java.util.Map;

public class MipsData {

    public static int pc;
    public static int[] dataMemory;
    public static Map<String, Integer> registers;

    public static void reset() {
        pc = 0;
        dataMemory = new int[8192];
        registers = new HashMap<>();
       registers.put("$0", 0);
       registers.put("$v0",0);
       registers.put("$v1",0);
       registers.put("$a0",0);
       registers.put("$a1",0);
       registers.put("$a2",0);
       registers.put("$a3",0);
       registers.put("$t0",0);
       registers.put("$t1",0);
       registers.put("$t2",0);
       registers.put("$t3",0);
       registers.put("$t4",0);
       registers.put("$t5",0);
       registers.put("$t6",0);
       registers.put("$t7",0);
       registers.put("$s0",0);
       registers.put("$s1",0);
       registers.put("$s2",0);
       registers.put("$s3",0);
       registers.put("$s4",0);
       registers.put("$s5",0);
       registers.put("$s6",0);
       registers.put("$s7",0);
       registers.put("$t8",0);
       registers.put("$t9",0);
       registers.put("$sp",0);
       registers.put("$ra",0);
    }

//    private void setInstructionToOpCode() {
//        instructionToOpCode.put("and", "000000");
//        instructionToOpCode.put("or", "000000");
//        instructionToOpCode.put("add", "000000");
//        instructionToOpCode.put("addi", "001000");
//        instructionToOpCode.put("sll", "000000");
//        instructionToOpCode.put("sub", "000000");
//        instructionToOpCode.put("slt", "000000");
//        instructionToOpCode.put("beq", "000100");
//        instructionToOpCode.put("bne", "000101");
//        instructionToOpCode.put("lw", "100011");
//        instructionToOpCode.put("sw", "101011");
//        instructionToOpCode.put("j", "000010");
//        instructionToOpCode.put("jr", "000000");
//        instructionToOpCode.put("jal", "000011");
//    }





}
