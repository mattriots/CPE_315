/*
  # Name: Matt DePauw
  # Section:  7
  # Description:  This program converts MIPS assembly code to binary.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class lab3 {

    public static void main(String[] args) throws FileNotFoundException {

        //Read in the file from cmd line input -> args[0]
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        List<String> rawLines = new ArrayList<>();

        //Add all the raw lines to an array list
        //Then send it off to cleanup
        while (scanner.hasNext()) {
            rawLines.add(scanner.nextLine());
        }

        //This is all the instruction lines cleaned up
        List<String> cleanLines = cleanup(rawLines);

        /* ==========================================================================
        Below is the creation of 2 Hashmaps, info stored in them, and optional print outs
        These hashmaps serve 2 purposes
        lineNumtoFullInstr - All the line numbers to their respective instruction line
        labelToLineNum - A label to its respective Line number. This is useful for jumps later
         */
        Map<Integer, String> lineNumtoFullInstr = new HashMap<>();
        Map<String, Integer> labelToLineNum = new HashMap<>();

        for (int i = 0; i < cleanLines.size(); i++) {
            lineNumtoFullInstr.put(i + 1, cleanLines.get(i));
        }


        for (int i = 0; i < cleanLines.size(); i++) {
            if (cleanLines.get(i).contains(":")) {
                labelToLineNum.put(cleanLines.get(i).substring(0, cleanLines.get(i).indexOf(":")), i + 1);
            }
        }
//
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
        Binary bin2;
        for (Integer num : lineNumtoFullInstr.keySet()) {
            bin2 = new Binary(labelToLineNum, num, lineNumtoFullInstr.get(num));
            bin2.binType();
        }
    }

    //Parses and cleans up the .asm file.
    //Removes all Comments on their own line, comments after commands
    //Removes white space in front of instructions
    //Also combines any lone Labels with the line below it
    public static List<String> cleanup(List<String> rawLines) {
        List<String> cleanup = new ArrayList<>();

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
