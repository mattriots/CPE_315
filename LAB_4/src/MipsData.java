/*
  # Name: Matt DePauw
  # Section:  7
  # Description:  This is where all the data is stored. Then we are able to call it and update it in the Mips Class.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MipsData {

    public static int pc;
    public static int pipePC;
    public static int instructionCount;
    public static boolean branchTaken;
    public static int[] dataMemory;
    public static Map<String, Integer> registers;
    public static List<String> pipeline;
    public static List<String> instructionLines = new ArrayList<>();
    public static Map<String, Integer> labelToLineNumber = new HashMap<>();

    public static String lwRt;

    public static int cycles;
    public static void reset() {
        pc = 0;
        pipePC = 0;
        instructionCount = 0;
        branchTaken = false;
        cycles = 0;
        dataMemory = new int[8192];
        registers = new HashMap<>();
        pipeline = new ArrayList<>();
        pipeline.add("empty");
        pipeline.add("empty");
        pipeline.add("empty");
        pipeline.add("empty");
        registers.put("$0", 0);
        registers.put("$v0", 0);
        registers.put("$v1", 0);
        registers.put("$a0", 0);
        registers.put("$a1", 0);
        registers.put("$a2", 0);
        registers.put("$a3", 0);
        registers.put("$t0", 0);
        registers.put("$t1", 0);
        registers.put("$t2", 0);
        registers.put("$t3", 0);
        registers.put("$t4", 0);
        registers.put("$t5", 0);
        registers.put("$t6", 0);
        registers.put("$t7", 0);
        registers.put("$s0", 0);
        registers.put("$s1", 0);
        registers.put("$s2", 0);
        registers.put("$s3", 0);
        registers.put("$s4", 0);
        registers.put("$s5", 0);
        registers.put("$s6", 0);
        registers.put("$s7", 0);
        registers.put("$t8", 0);
        registers.put("$t9", 0);
        registers.put("$sp", 0);
        registers.put("$ra", 0);
    }


    /*
    Parses and cleans up the .asm file.
    Removes all Comments on their own line, comments after commands
    Removes white space in front of instructions
    Also combines any lone Labels with the line below it

    Then looks through arraylist for any lines with labels and (:)
    Stores this in a hashmap to be used for jumps/bne/beq etc
    */
    public static void cleanup(List<String> rawLines) {

        instructionLines = rawLines.stream()
                .map(s -> {
                    if (s.contains("#")) {
                        s = s.substring(0, s.indexOf('#')); //clears all instances of #comments
                    }
                    return s;
                })
                .map(s -> s.replaceAll("\\s", "")) //Removes all spaces
                .filter(s -> s.length() != 0) //removes blank lines
                .collect(Collectors.toList());

        //If a line has just a label, this combines it with the line below. To match up proper line #'s
        for (int i = 0; i < instructionLines.size(); i++) {
            if (instructionLines.get(i).contains(":") && instructionLines.get(i).length() - 1 == instructionLines.get(i).indexOf(":")) {
                String save = instructionLines.get(i) + instructionLines.get(i + 1);
                instructionLines.remove(i);
                instructionLines.remove(i);
                instructionLines.add(i, save);
            }
        }

        for (int i = 0; i < instructionLines.size(); i++) {
            if (instructionLines.get(i).contains(":")) {
                labelToLineNumber.put(instructionLines.get(i).substring(0, instructionLines.get(i).indexOf(":")), i);
            }
        }
    }


}
