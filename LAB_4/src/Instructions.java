/*
  # Name: Matt DePauw
  # Section:  7
  # Description:  This class handles all the operations for the particular instruction.
  # Binary: This class acts as a data structure for ALL MIPS instructions.
 */

public class Instructions {
    public static String rawLine;
    public static String instruction;
    public static String rs;
    public static String rt;
    public static String rd;
    private int immediate;
    private int shamt;
    public static int jumpAddress;


    public Instructions(String rawLine) {
        this.rawLine = rawLine;
        this.rd = "";
        this.rs = "";
        this.rt = "";
        this.immediate = 0;
        this.shamt = 0;

    }

    public void instType() {
        if (rawLine.contains(":")) {
//            label(rawLine.substring(0, rawLine.indexOf(":")));
            this.rawLine = rawLine.substring(rawLine.indexOf(":") + 1).trim();
        }
        if (rawLine.startsWith("and")) {
            instruction = "and";
            rawLine = rawLine.substring(instruction.length());
            andInst();
        } else if (rawLine.startsWith("or")) {
            instruction = "or";
            rawLine = rawLine.substring(instruction.length());
            orInst();
        } else if (rawLine.startsWith("addi")) {
            instruction = "addi";
            rawLine = rawLine.substring(instruction.length());
            addiInst();
        } else if (rawLine.startsWith("add")) {
            instruction = "add";
            rawLine = rawLine.substring(instruction.length());
            addInst();
        } else if (rawLine.startsWith("sll")) {
            instruction = "sll";
            rawLine = rawLine.substring(instruction.length());
            sllInst();
        } else if (rawLine.startsWith("sub")) {
            instruction = "sub";
            rawLine = rawLine.substring(instruction.length());
            subInst();
        } else if (rawLine.startsWith("slt")) {
            instruction = "slt";
            rawLine = rawLine.substring(instruction.length());
            sltInst();
        } else if (rawLine.startsWith("beq")) {
            instruction = "beq";
            rawLine = rawLine.substring(instruction.length());
            beqInst();
        } else if (rawLine.startsWith("bne")) {
            instruction = "bne";
            rawLine = rawLine.substring(instruction.length());
            bneInst();
        } else if (rawLine.startsWith("lw")) {
            instruction = "lw";
            rawLine = rawLine.substring(instruction.length());
            lwInst();
        } else if (rawLine.startsWith("sw")) {
            instruction = "sw";
            rawLine = rawLine.substring(instruction.length());
            swInst();
        } else if (rawLine.startsWith("jr")) {
            instruction = "jr";
            rawLine = rawLine.substring(instruction.length());
            jrInst();
        } else if (rawLine.startsWith("jal")) {
            instruction = "jal";
            rawLine = rawLine.substring(instruction.length());
            jalInst();
        } else if (rawLine.startsWith("j")) {
            instruction = "j";
            rawLine = rawLine.substring(instruction.length());
            jInst();
        } else {
            if (rawLine.contains("$")) {
                System.out.println("invalid instruction: " + rawLine.substring(0, rawLine.indexOf("$")));
                System.exit(0);
            }
        }
    }

    ///R FORMAT////
    // OPCODE : RS : RT : RD : SHAMT : FUNCT //
    public void andInst() {
        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        rt = regArr[2];

        int result;

        result = MipsData.registers.get(rs) & MipsData.registers.get(rt);

        MipsData.registers.put(rd, result);

    }

    public void orInst() {

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        rt = regArr[2];

        int result;

        result = MipsData.registers.get(rs) | MipsData.registers.get(rt);

        MipsData.registers.put(rd, result);

    }

    public void addInst() {

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        rt = regArr[2];

        int result;

        result = MipsData.registers.get(rs) + MipsData.registers.get(rt);

        MipsData.registers.put(rd, result);


    }

    public void sllInst() {

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        rt = regArr[2];
        shamt = Integer.parseInt(rt);

        int result;

        result = MipsData.registers.get(rs) << shamt;

        MipsData.registers.put(rd, result);

    }

    public void subInst() {

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        rt = regArr[2];

        int result;

        result = MipsData.registers.get(rs) - MipsData.registers.get(rt);

        MipsData.registers.put(rd, result);


    }


    public void sltInst() {
        int rsVal;
        int rtVal;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        rt = regArr[2];

        rsVal = MipsData.registers.get(rs);
        rtVal = MipsData.registers.get(rt);


        if (rsVal < rtVal) {
            MipsData.registers.put(rd, 1);
        } else
            MipsData.registers.put(rd, 0);


    }

    public void jrInst() {
        rs = rawLine;

        MipsData.pc = MipsData.registers.get(rs);

    }
    ///END R FORMAT///


    ///I FORMAT ////
    //OPCODE : RS : RT : IMMEDIATE //
    public void addiInst() {

        String[] regArr = rawLine.split(",");
        rt = regArr[0];
        rs = regArr[1];
        immediate = Integer.parseInt(regArr[2]);

        int result;

        result = MipsData.registers.get(rs) + immediate;

        MipsData.registers.put(rt, result);


    }

    public void beqInst() {


        int labelNum;
        int rtVal;
        int rsVal;

        String[] regArr = rawLine.split(",");
        rt = regArr[0];
        rs = regArr[1];
        labelNum = MipsData.labelToLineNumber.get(regArr[2]);
        jumpAddress = MipsData.labelToLineNumber.get(regArr[2]);

        rtVal = MipsData.registers.get(rt);
        rsVal = MipsData.registers.get(rs);

        if (rtVal == rsVal) {
            MipsData.branchTaken = true;
            MipsData.branchCountdown = 3;
//                MipsData.pc = labelNum;


        }


    }


    public void bneInst() {

        int labelNum;
        int rtVal;
        int rsVal;

        String[] regArr = rawLine.split(",");
        rt = regArr[0];
        rs = regArr[1];
        labelNum = MipsData.labelToLineNumber.get(regArr[2]);
        jumpAddress = MipsData.labelToLineNumber.get(regArr[2]);

        rtVal = MipsData.registers.get(rt);
        rsVal = MipsData.registers.get(rs);

        if (rtVal != rsVal) {
            MipsData.branchTaken = true;
            MipsData.branchCountdown = 3;
        }

    }

    public void lwInst() {


        int offset;
        int index;
        int memWord;

        String[] regArr = rawLine.split(",");
        rt = regArr[0];
        MipsData.lwRt = rt;
        rs = regArr[1];
        offset = Integer.parseInt(rs.substring(0, rs.indexOf("(")));
        rs = rs.substring(rs.indexOf("(") + 1, rs.indexOf(")"));
        //Index = rs + offset
        //Then we pull that index from data memory and store it in rd
        index = MipsData.registers.get(rs) + offset;
        memWord = MipsData.dataMemory[index];
        MipsData.registers.put(rt, memWord);

    }

    public void swInst() {

        int offset;
        int index;

        String[] regArr = rawLine.split(",");
        rt = regArr[0];
        rs = regArr[1];
        offset = Integer.parseInt(rs.substring(0, rs.indexOf("(")));
        rs = rs.substring(rs.indexOf("(") + 1, rs.indexOf(")"));

        index = MipsData.registers.get(rs) + offset;
        MipsData.dataMemory[index] = MipsData.registers.get(rt);

    }

    ////END I FORMAT///


    ///J FORMAT ////
    //OPCODE : ADDRESS //
    public void jInst() {

        String label = rawLine;
//
//        MipsData.pc = MipsData.labelToLineNumber.get(label) - 1;

        jumpAddress = MipsData.labelToLineNumber.get(label);

    }


    public void jalInst() {

        String label = rawLine;

        MipsData.registers.put("$ra", MipsData.pc);

//        MipsData.pc = MipsData.labelToLineNumber.get(label) - 1;
        jumpAddress = MipsData.labelToLineNumber.get(label);

    }

    ///END J FORMAT ///


}
