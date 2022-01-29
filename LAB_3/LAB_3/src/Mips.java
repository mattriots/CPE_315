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

    public Mips() {

    }

    public Mips(List<String> script) {
        this.script = script;
        MipsData.reset();
    }

    public void mipsOutput() {

        if (!script.isEmpty()) {
            for (String scr : script) {
                System.out.println("Mips> " + scr);

                if (scr.equals("h")) {
                    printH();

                } else if (scr.equals("d")) {
                    printD();

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
            }
        } else {

            Scanner keyboard = new Scanner(System.in);
            String input;
            do {
                System.out.println("Mips>");
                input = keyboard.nextLine();

                if (input.equals("h")) {
                    printH();

                } else if (input.equals("d")) {
                    printD();

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

            } while (!input.equals("q"));

        }
    }

    private void printH() {

        System.out.println("h = show help\n" +
                "d = dump register state\n" +
                "s = single step through the program (i.e. execute 1 instruction and stop)\n" +
                "s num = step through num instructions of the program\n" +
                "r = run until the program ends\n" +
                "m num1 num2 = display data memory from location num1 to num2\n" +
                "c = clear all registers, memory, and the program counter to 0\n" +
                "q = exit the program");
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
    Steps through the code
     */
    public void step(String input) {
        int stepNum = 0;

        String[] arr = input.split(" ");
        if (arr.length <= 1) {
            bin = new Instructions(MipsData.instructionLines.get(MipsData.pc));
            bin.instType();
            MipsData.pc++;
            stepNum++;
        } else {

            stepNum = Integer.parseInt(arr[1]);

            for (int i = 0; i < stepNum; i++) {

                bin = new Instructions(MipsData.instructionLines.get(MipsData.pc));
                bin.instType();
                MipsData.pc++;
            }
        }
        System.out.println("\t\t" + stepNum + " instruction(s) executed");
    }


    public void runEnd() {
        while (MipsData.pc < MipsData.instructionLines.size()) {
            bin = new Instructions(MipsData.instructionLines.get(MipsData.pc));
            bin.instType();
            MipsData.pc++;
        }
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
