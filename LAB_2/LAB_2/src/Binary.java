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
    private String immediate;
    private String shamt;
    private int lineNum;
    private BinaryData binaryData;
    private Map<String, Integer> labeltoLineNum;


    public Binary(Map<String, Integer> labelToLineNum, Integer lineNum, String rawLine) {
        this.rawLine = rawLine;
        this.lineNum = lineNum;
        this.rd = "";
        this.rs = "";
        this.rt = "";
        this.immediate = "";
        this.shamt = "00000";
        binaryData = new BinaryData();
        this.labeltoLineNum = labelToLineNum;

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
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an andInst");
//        System.out.println("and OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("and FUNCT: " + binaryData.getFunct(instruction));

        regParseR();
        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rt) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + shamt + " "
                        + binaryData.getFunct(instruction));
    }

    public void orInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an orInst");
//        System.out.println("or OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("or FUNCT: " + binaryData.getFunct(instruction));

        regParseR();
        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rt) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + shamt + " "
                        + binaryData.getFunct(instruction));
    }

    public void addInst() {
//        System.out.println("================= \n" + lineNum + " : " + this.rawLine + "\n" + "This is an addInst");
//        System.out.println("add OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("add FUNCT: " + binaryData.getFunct(instruction));


        regParseR();

        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rt) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + shamt + " "
                        + binaryData.getFunct(instruction));
    }

    ///HAD TO SWAP RS and RT for some reason...... ???
    public void sllInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an sllInst");
//        System.out.println("sll OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("sll FUNCT: " + binaryData.getFunct(instruction));

        regParseR();
        //Deals with Shift AMT
        shamt = Integer.toBinaryString(Integer.parseInt(rt));

        while (shamt.length() < 5) {
            shamt = "0" + shamt;
        }
        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rt) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + shamt + " "
                        + binaryData.getFunct(instruction));
    }

    public void subInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an subInst");
//        System.out.println("sub OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("sub FUNCT: " + binaryData.getFunct(instruction));

        regParseR();
        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rt) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + shamt + " "
                        + binaryData.getFunct(instruction));
    }


    public void sltInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an sltInst");
//        System.out.println("slt OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("slt FUNCT: " + binaryData.getFunct(instruction));

        regParseR();
        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rt) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + shamt + " "
                        + binaryData.getFunct(instruction));
    }

    public void jrInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an jrInst");
//        System.out.println("jr OPCODE: " + binaryData.getOpCode(instruction));
//        System.out.println("jr FUNCT: " + binaryData.getFunct(instruction));

        rs = rawLine;
//        System.out.println("RS: " + rs);
        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rt) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + shamt + " "
                        + binaryData.getFunct(instruction));
    }
    ///END R FORMAT///


    ///I FORMAT ////
    //OPCODE : RS : RT : IMMEDIATE //
    public void addiInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an addiInst");
//        System.out.println("addi OPCODE: " + binaryData.getOpCode(instruction));

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        immediate = regArr[2];

        immediate = Integer.toBinaryString(Integer.parseInt(immediate));

        immmediateLength();
//        System.out.println("RD " + rd);
//        System.out.println("RS " + rs);

        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + immediate);

    }

    public void beqInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an beqInst");
//        System.out.println("beq OPCODE: " + binaryData.getOpCode(instruction));

        int labelOffset;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        labelOffset = labeltoLineNum.get(regArr[2]);
        labelOffset -= lineNum + 1;

        immediate = Integer.toBinaryString(labelOffset);

        immmediateLength();

//        System.out.println("RD " + rd);
//        System.out.println("RS " + rs);
//
        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + immediate + " ");
    }


    public void bneInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an bneInst");
//        System.out.println("bne OPCODE: " + binaryData.getOpCode(instruction));
        int labelOffset;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        labelOffset = labeltoLineNum.get(regArr[2]);
        labelOffset -= lineNum + 1;

        immediate = Integer.toBinaryString(labelOffset);

        immmediateLength();

//        System.out.println("RD " + rd);
//        System.out.println("RS " + rs);

        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + immediate + " ");
    }

    public void lwInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an lwInst");
//        System.out.println("lw OPCODE: " + binaryData.getOpCode(instruction));

        int offset;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        offset = Integer.parseInt(rs.substring(0, rs.indexOf("(")));
        rs = rs.substring(rs.indexOf("(") + 1, rs.indexOf(")"));
        immediate = Integer.toBinaryString(offset);

        immmediateLength();

//        System.out.println("RD " + rd);
//        System.out.println("RS " + rs);

        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + immediate + " ");
    }

    public void swInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an swInst");
//        System.out.println("sw OPCODE: " + binaryData.getOpCode(instruction));

        int offset;

        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        offset = Integer.parseInt(rs.substring(0, rs.indexOf("(")));
        rs = rs.substring(rs.indexOf("(") + 1, rs.indexOf(")"));
        immediate = Integer.toBinaryString(offset);

        immmediateLength();

//        System.out.println("RD " + rd);
//        System.out.println("RS " + rs);

        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + binaryData.getRegBinary(rs) + " "
                        + binaryData.getRegBinary(rd) + " "
                        + immediate + " ");
    }

    ////END I FORMAT///


    ///J FORMAT ////
    //OPCODE : ADDRESS //
    public void jInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an jInst");
//        System.out.println("add OPCODE: " + binaryData.getOpCode(instruction));
        String address;

        address = Integer.toBinaryString(labeltoLineNum.get(rawLine) - 1);

        address = jLength(address);

        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + address);

    }


    public void jalInst() {
//        System.out.println("================= \n" + lineNum + " : " + rawLine + "\n" + "This is an jalInst");
//        System.out.println("add OPCODE: " + binaryData.getOpCode(instruction));

        String address;

        address = Integer.toBinaryString(labeltoLineNum.get(rawLine) - 1);

        address = jLength(address);

        System.out.println(
                binaryData.getOpCode(instruction) + " "
                        + address);
    }

    ///END J FORMAT ///


        /*
    Helper functions
    - regParsR - parses RFormat instructions and stores each (3) int an array
    - immediateLength - makes sure the immediate lenghts are exactly 16 bits long
    - jLength - makes sure the jump addresses are exactly 26 bits long
     */

    public void regParseR() {
        String[] regArr = rawLine.split(",");
        rd = regArr[0];
        rs = regArr[1];
        rt = regArr[2];
    }

    public void immmediateLength() {
        if (immediate.length() > 16) {
            immediate = immediate.substring(16);
        }

        while (immediate.length() < 16) {
            immediate = "0" + immediate;
        }
    }

    public String jLength(String address) {

        if (address.length() > 26) {
            address = address.substring(16);
        }

        while (address.length() < 26) {
            address = "0" + address;
        }
        return address;
    }


}
