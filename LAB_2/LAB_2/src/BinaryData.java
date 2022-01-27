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

public class BinaryData {

    private Map<String, String> instructionToOpCode = new HashMap<>();
    private Map<String, String> instructiontoFunct = new HashMap<>();
    private Map<String, String> registertoBinary = new HashMap<>();


    public BinaryData() {
        setInstructionToOpCode();
        setInstructionToFunct();
        setRegisterToBinary();
    }

    private void setInstructionToOpCode() {
        instructionToOpCode.put("and", "000000");
        instructionToOpCode.put("or", "000000");
        instructionToOpCode.put("add", "000000");
        instructionToOpCode.put("addi", "001000");
        instructionToOpCode.put("sll", "000000");
        instructionToOpCode.put("sub", "000000");
        instructionToOpCode.put("slt", "000000");
        instructionToOpCode.put("beq", "000100");
        instructionToOpCode.put("bne", "000101");
        instructionToOpCode.put("lw", "100011");
        instructionToOpCode.put("sw", "101011");
        instructionToOpCode.put("j", "000010");
        instructionToOpCode.put("jr", "000000");
        instructionToOpCode.put("jal", "000011");
    }

    private void setInstructionToFunct() {

        instructiontoFunct.put("and", "100100");
        instructiontoFunct.put("or", "100101");
        instructiontoFunct.put("add", "100000");
        instructiontoFunct.put("sll", "000000");
        instructiontoFunct.put("sub", "100010");
        instructiontoFunct.put("slt", "101010");
        instructiontoFunct.put("jr", "001000");

    }

    private void setRegisterToBinary() {

        //0
        registertoBinary.put("$zero", "00000");
        registertoBinary.put("$0", "00000");
        //2-3
        registertoBinary.put("$v0", "00010");
        registertoBinary.put("$v1", "00011");
        //4-7
        registertoBinary.put("$a0", "00100");
        registertoBinary.put("$a1", "00101");
        registertoBinary.put("$a2", "00110");
        registertoBinary.put("$a3", "00111");
        //8-15
        registertoBinary.put("$t0", "01000");
        registertoBinary.put("$t1", "01001");
        registertoBinary.put("$t2", "01010");
        registertoBinary.put("$t3", "01011");
        registertoBinary.put("$t4", "01100");
        registertoBinary.put("$t5", "01101");
        registertoBinary.put("$t6", "01110");
        registertoBinary.put("$t7", "01111");
        //16-23
        registertoBinary.put("$s0", "10000");
        registertoBinary.put("$s1", "10001");
        registertoBinary.put("$s2", "10010");
        registertoBinary.put("$s3", "10011");
        registertoBinary.put("$s4", "10100");
        registertoBinary.put("$s5", "10101");
        registertoBinary.put("$s6", "10110");
        registertoBinary.put("$s7", "10111");
        //24-25
        registertoBinary.put("$t8", "11000");
        registertoBinary.put("$t9", "11001");
        //29 & 31
        registertoBinary.put("$sp", "11101");
        registertoBinary.put("$ra", "11111");


    }

    public String getOpCode(String instruction) {

        return instructionToOpCode.get(instruction);
    }

    public String getFunct(String instruction) {

        return instructiontoFunct.get(instruction);
    }

    public String getRegBinary(String register) {
        if (!registertoBinary.containsKey(register)) {
            return "00000";
        }
        return registertoBinary.get(register);

    }


}
