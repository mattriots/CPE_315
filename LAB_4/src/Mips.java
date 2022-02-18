/*
  # Name: Matt DePauw
  # Section:  7
  # Description: This is where all the Mips simulating happens.
  #
 */

import java.util.List;
import java.util.Scanner;

public class Mips {

    public List<String> script;
    public Instructions bin;


    public Mips(List<String> script) {
        this.script = script;
        MipsData.reset();
    }

    public void mipsOutput() {

        if (!script.isEmpty()) {
            for (String scr : script) {
                System.out.println("mips> " + scr);
                System.out.println();

                if (scr.equals("h")) {
                    printH();

                } else if (scr.equals("d")) {
                    printD();

                } else if (scr.contains("p")) {
                    printP();

                } else if (scr.contains("s")) {
                    step(scr);

                } else if (scr.equals("r")) {
                    runEnd();

                } else if (scr.contains("m")) {
                    displayMem(scr);

                } else if (scr.equals("c")) {
                    MipsData.reset();
                    System.out.println("\t\tSimulator reset");
                } else if (scr.equals("q")) {
                    System.exit(0);
                }
                System.out.println();
            }
        } else {

            Scanner keyboard = new Scanner(System.in);
            String input;
            do {
                System.out.println("mips>");
                input = keyboard.nextLine();
                System.out.println();

                if (input.equals("h")) {
                    printH();

                } else if (input.equals("d")) {
                    printD();

                } else if (input.contains("p")) {
                    printP();

                } else if (input.contains("s")) {
                    step(input);

                } else if (input.equals("r")) {
                    runEnd();

                } else if (input.contains("m")) {
                    displayMem(input);

                } else if (input.equals("c")) {
                    MipsData.reset();
                    System.out.println("\t\tSimulator reset");
                }
                System.out.println();
            } while (!input.equals("q"));

        }
    }

    private void printH() {

//        System.out.println("h = show help\n" +
//                "d = dump register state\n" +
//                "s = single step through the program (i.e. execute 1 instruction and stop)\n" +
//                "s num = step through num instructions of the program\n" +
//                "r = run until the program ends\n" +
//                "m num1 num2 = display data memory from location num1 to num2\n" +
//                "c = clear all registers, memory, and the program counter to 0\n" +
//                "q = exit the program");

        System.out.println("\n" +
                "    h = show help\n" +
                "    d = dump register state\n" +
                "    p = show pipeline registers\n" +
                "    s = step through a single clock cycle step (i.e. simulate 1 cycle and stop)\n" +
                "    s num = step through num clock cycles\n" +
                "    r = run until the program ends and display timing summary\n" +
                "    m num1 num2 = display data memory from location num1 to num2\n" +
                "    c = clear all registers, memory, and the program counter to 0\n" +
                "    q = exit the program\n");
    }

    private void printD() {
        System.out.println("pc = " + MipsData.pc);
        System.out.print("$0 = " + MipsData.registers.get("$0"));
        System.out.print("          ");
        System.out.print("$v0 = " + MipsData.registers.get("$v0"));
        System.out.print("         ");
        System.out.print("$v1 = " + MipsData.registers.get("$v1"));
        System.out.print("         ");
        System.out.println("$a0 = " + MipsData.registers.get("$a0"));

        System.out.print("$a1 = " + MipsData.registers.get("$a1"));
        System.out.print("         ");
        System.out.print("$a2 = " + MipsData.registers.get("$a2"));
        System.out.print("         ");
        System.out.print("$a3 = " + MipsData.registers.get("$a3"));
        System.out.print("         ");
        System.out.println("$t0 = " + MipsData.registers.get("$t0"));

        System.out.print("$t1 = " + MipsData.registers.get("$t1"));
        System.out.print("         ");
        System.out.print("$t2 = " + MipsData.registers.get("$t2"));
        System.out.print("         ");
        System.out.print("$t3 = " + MipsData.registers.get("$t3"));
        System.out.print("         ");
        System.out.println("$t4 = " + MipsData.registers.get("$t4"));

        System.out.print("$t5 = " + MipsData.registers.get("$t5"));
        System.out.print("         ");
        System.out.print("$t6 = " + MipsData.registers.get("$t6"));
        System.out.print("         ");
        System.out.print("$t7 = " + MipsData.registers.get("$t7"));
        System.out.print("         ");
        System.out.println("$s0 = " + MipsData.registers.get("$s0"));

        System.out.print("$s1 = " + MipsData.registers.get("$s1"));
        System.out.print("         ");
        System.out.print("$s2 = " + MipsData.registers.get("$s2"));
        System.out.print("         ");
        System.out.print("$s3 = " + MipsData.registers.get("$s3"));
        System.out.print("         ");
        System.out.println("$s4 = " + MipsData.registers.get("$s4"));

        System.out.print("$s5 = " + MipsData.registers.get("$s5"));
        System.out.print("         ");
        System.out.print("$s6 = " + MipsData.registers.get("$s6"));
        System.out.print("         ");
        System.out.print("$s7 = " + MipsData.registers.get("$s7"));
        System.out.print("         ");
        System.out.println("$t8 = " + MipsData.registers.get("$t8"));

        System.out.print("$t9 = " + MipsData.registers.get("$t9"));
        System.out.print("         ");
        System.out.print("$sp = " + MipsData.registers.get("$sp"));
        System.out.print("         ");
        System.out.println("$ra = " + MipsData.registers.get("$ra"));
    }

    /*
    Prints the pipeline
     */
    public void printP() {

        System.out.println("pc      if/id   id/exe  exe/mem mem/wb");
        System.out.println(MipsData.pc + "\t\t" + MipsData.pipeline.get(0)
                + "\t" + MipsData.pipeline.get(1)
                + "\t" + MipsData.pipeline.get(2)
                + "\t" + MipsData.pipeline.get(3));

    }

    // If lw $rd == add $rs || $rt
    public void pipelineLogic() {

        if (MipsData.pipeline.size() > 4) {
            MipsData.pipeline.remove(4);
        }

        if (MipsData.pipeline.get(1).equals("lw") && !MipsData.branchTaken
                && Instructions.rs != null && Instructions.rt != null
                && (Instructions.rs.equals(MipsData.lwRt)
                || Instructions.rt.equals(MipsData.lwRt)))
        {
            MipsData.pipeline.add(1, "stall");

        }
        else if (MipsData.pipeline.get(0).equals("j")
                || MipsData.pipeline.get(0).equals("jal")
                || MipsData.pipeline.get(0).equals("jr")) {
            MipsData.pipeline.add(0, "squash");
            MipsData.pc = Instructions.jumpAddress;


        }
        else if ((MipsData.pipeline.get(2).equals("beq")
                || MipsData.pipeline.get(2).equals("bne"))
                && MipsData.branchTaken
                && MipsData.branchCountdown == 0)
        {
            String bneOrBeq = MipsData.pipeline.get(2);

            MipsData.pipeline.add(0, "squash");
            MipsData.pipeline.add(1, "squash");
            MipsData.pipeline.add(2, "squash");
            MipsData.pipeline.add(3, bneOrBeq);
            MipsData.branchTaken = false;
            MipsData.beqOrBne = true;
            MipsData.pc = Instructions.jumpAddress;
            MipsData.instructionCount ++;


        }
        else
        {
            bin = new Instructions(MipsData.instructionLines.get(MipsData.pc));
            bin.instType();
            MipsData.pc++;
            MipsData.pipeline.add(0, Instructions.instruction);
            MipsData.instructionCount++;


            if (MipsData.branchCountdown > 0) {
                MipsData.branchCountdown--;
                MipsData.instructionCount--;

            }
        }
        MipsData.cycles++;

        if(MipsData.beqOrBne && MipsData.instructionLines.size() == MipsData.pc){
            MipsData.cycles+= 4;
        }
        System.out.println("Cycles: " + MipsData.cycles);
        System.out.println("Inst #: " + MipsData.instructionCount);
        System.out.println("PC: " + MipsData.pc);
        System.out.println("INST: " + Instructions.instruction + Instructions.rawLine);
        System.out.println("BranchTaken: " + MipsData.branchTaken);
        System.out.println(MipsData.pc + " " + Instructions.rawLine);
    }

    /*
    Steps through the code
     */
    public void step(String input) {
        int stepNum;

        String[] arr = input.split(" ");
        if (arr.length <= 1) {
            pipelineLogic();


        } else {

            stepNum = Integer.parseInt(arr[1]);

            for (int i = 0; i < stepNum; i++) {
                pipelineLogic();
            }
        }
//        printP();
    }


    public void runEnd() {
        while (MipsData.pc < MipsData.instructionLines.size()) {
            pipelineLogic();

            printP();

        }

        float cpi = (float) MipsData.cycles / MipsData.instructionCount;

        System.out.println("Program Complete");
        System.out.printf("CPI = %.3f", cpi);
        System.out.println("\tCycles = " + MipsData.cycles +
                "\tInstructions = " + MipsData.instructionCount);
    }

    /*
    display data memory from location num1 to num2
     */
    public void displayMem(String input) {
        String[] arr = input.split(" ");
        int start = Integer.parseInt(arr[1]);
        int end = Integer.parseInt(arr[2]);

        for (int i = start; i <= end; i++) {
            System.out.println("[" + i + "] = " + MipsData.dataMemory[i]);
        }

    }


}
