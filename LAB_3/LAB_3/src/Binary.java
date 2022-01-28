/*
  # Name: Matt DePauw
  # Section:  7
  # Description:  This program converts MIPS assembly code to binary.
  # Binary: This class acts as a data structure for ALL MIPS instructions.
 */


import java.util.Map;

public class Binary {
    private String rawLine;
    private String instruction;
    private String rs;
    private String rt;
    private String rd;
    private int immediate;
    private String shamt;
    private int lineNum;
    public Mips mips = new Mips();
    private Map<String, Integer> labelToLineNum;


    public Binary(Integer lineNum, String rawLine, Map<String, Integer> labelToLineNum) {
        this.rawLine = rawLine;
        this.lineNum = lineNum;
        this.rd = "";
        this.rs = "";
        this.rt = "";
        this.immediate = 0;
        this.shamt = "00000";
        this.labelToLineNum = labelToLineNum;

    }

    public void binType() {
        if (rawLine.contains(":")) {
            label(rawLine.substring(0, rawLine.indexOf(":")));
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


    public void label(String label) {
//        System.out.println("================= \n" + lineNum + " : " + label + "\n" + "This is a label");
//        System.out.println(label + " " + lineNum);
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

    ///HAD TO SWAP RS and RT for some reason...... ???
    public void sllInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an sllInst");
//        System.out.println("sll OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("sll FUNCT: " + binaryData.getFunct(instruction));


        //Deals with Shift AMT
        shamt = Integer.toBinaryString(Integer.parseInt(rt));

        while (shamt.length() < 5) {
            shamt = "0" + shamt;
        }

    }

    public void subInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an subInst");
//        System.out.println("sub OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("sub FUNCT: " + binaryData.getFunct(instruction));


    }


    public void sltInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an sltInst");
//        System.out.println("slt OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("slt FUNCT: " + binaryData.getFunct(instruction));


    }

    public void jrInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an jrInst");
//        System.out.println("jr OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("jr FUNCT: " + binaryData.getFunct(instruction));

        rs = rawLine;

    }
    ///END R FORMAT///


    ///I FORMAT ////
    //OPCODE : RS : RT : IMMEDIATE //
    public void addiInst() {

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        immediate = Integer.parseInt(regArr[2]);

        int result;

        result = MipsData.registers.get(rs) + immediate;

        MipsData.registers.put(rd, result);


    }

    public void beqInst() {

        int labelNum;
        int rdVal;
        int rsVal;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        labelNum = labelToLineNum.get(regArr[2]);

        rdVal = MipsData.registers.get(rd);
        rsVal = MipsData.registers.get(rs);

        if (rdVal == rsVal) {
            MipsData.pc = labelNum;
        }


    }


    public void bneInst() {

        int labelNum;
        int rdVal;
        int rsVal;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        labelNum = labelToLineNum.get(regArr[2]);

        rdVal = MipsData.registers.get(rd);
        rsVal = MipsData.registers.get(rs);

        if (rdVal != rsVal) {
            MipsData.pc = labelNum - 1;
        }

    }

    public void lwInst() {


        int offset;
        int index;
        int memWord;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        offset = Integer.parseInt(rs.substring(0, rs.indexOf("(")));
        rs = rs.substring(rs.indexOf("(") + 1, rs.indexOf(")"));
        //Index = rs + offset
        //Then we pull that index from data memory and store it in rd
        index = MipsData.registers.get(rs) + offset;
        memWord = MipsData.dataMemory[index];
        MipsData.registers.put(rd, memWord);

    }

    public void swInst() {

        int offset;
        int index;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        offset = Integer.parseInt(rs.substring(0, rs.indexOf("(")));
        rs = rs.substring(rs.indexOf("(") + 1, rs.indexOf(")"));

        index = MipsData.registers.get(rs) + offset;
        MipsData.dataMemory[index] = MipsData.registers.get(rd);

    }

    ////END I FORMAT///


    ///J FORMAT ////
    //OPCODE : ADDRESS //
    public void jInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an jInst");
//        System.out.println("add OPCODE: " + binaryData.getOpCode(instruction));
        String address;

        address = Integer.toBinaryString(labelToLineNum.get(rawLine) - 1);

    }


    public void jalInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an jalInst");
//        System.out.println("add OPCODE: " + binaryData.getOpCode(instruction));

        String address;

        address = Integer.toBinaryString(labelToLineNum.get(rawLine) - 1);

//        address = jLength(address);
    }

    ///END J FORMAT ///




}
