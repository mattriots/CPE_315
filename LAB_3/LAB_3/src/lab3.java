/*
  # Name: Matt DePauw
  # Section:  7
  # Description:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class lab3 {

    public static void main(String[] args) throws FileNotFoundException {

        //Read in the file from cmd line input -> args[0]
        File asmFile = new File(args[0]);
        Scanner asmScan = new Scanner(asmFile);
        List<String> rawLines = new ArrayList<>();

        //Add all the raw lines to an array list
        //Then send it off to cleanup
        while (asmScan.hasNext()) {
            rawLines.add(asmScan.nextLine());
        }
        asmScan.close(); //Close scanner for .asm file



        //Read in the script file from cmd line input -> args[1]
        //If not there its handled with try/catch
        File scriptFile = null;
        try {
            scriptFile = new File(args[1]);
        }catch (IndexOutOfBoundsException e){
            System.out.println("No Script file found");
        }

        /*If there is no script file found just move on
            Else add it to a string array to deal with later
         */
        List<String> scriptLines = new ArrayList<>();
        if(scriptFile != null) {
            Scanner scriptScan = new Scanner(scriptFile);
            while(scriptScan.hasNext()){
                scriptLines.add(scriptScan.nextLine());
            }
            scriptScan.close();
        }
        //Read out the script



        //This is all the instruction lines cleaned up
        List<String> instructionLines = cleanup(rawLines);

        /* ==========================================================================
        Below is the creation of 2 Hashmaps, info stored in them, and optional print outs
        These hashmaps serve 2 purposes
        lineNumtoFullInstr - All the line numbers to their respective instruction line
        labelToLineNum - A label to its respective Line number. This is useful for jumps later
         */
        Map<Integer, String> lineNumtoFullInstr = new HashMap<>();
        Map<String, Integer> labelToLineNum = new HashMap<>();

        for (int i = 0; i < instructionLines.size(); i++) {
            lineNumtoFullInstr.put(i + 1, instructionLines.get(i));
        }

        for (int i = 0; i < instructionLines.size(); i++) {
            if (instructionLines.get(i).contains(":")) {
                labelToLineNum.put(instructionLines.get(i).substring(0, instructionLines.get(i).indexOf(":")), i);
            }
        }

        Mips runMips = new Mips(scriptLines, instructionLines, labelToLineNum);
        runMips.mipsOutput();


        //Prints out hashmaps
//        for (Integer num : lineNumtoFullInstr.keySet()) {
//            System.out.println(num + " " + lineNumtoFullInstr.get(num));
//        }
////
//        for (String lab : labelToLineNum.keySet()) {
//            System.out.println(lab + " " + labelToLineNum.get(lab));
//        }
        //==============================================================//


        /*
        Currently the main call here
        Passing
        @ labelToLineNum <---- hashmap that has all the labels along with their line
        @ num <----- Line num for all
        @ lineNumetofullInstr.get(num) <--- each cleaned up line
         */

//        for (Integer num : lineNumtoFullInstr.keySet()) {
//            bin2 = new Binary(labelToLineNum, num, lineNumtoFullInstr.get(num));
//            bin2.binType();
//        }

    }


    /*
    Parses and cleans up the .asm file.
    Removes all Comments on their own line, comments after commands
    Removes white space in front of instructions
    Also combines any lone Labels with the line below it
    */

    public static List<String> cleanup(List<String> rawLines) {
        List<String> cleanup;

        cleanup = rawLines.stream()
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
        for (int i = 0; i < cleanup.size(); i++) {
            if (cleanup.get(i).contains(":") && cleanup.get(i).length() - 1 == cleanup.get(i).indexOf(":")) {
                String save = cleanup.get(i) + cleanup.get(i + 1);
                cleanup.remove(i);
                cleanup.remove(i);
                cleanup.add(i, save);
            }
        }
        return cleanup;
    }


}
