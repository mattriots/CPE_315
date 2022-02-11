/*
  # Name: Matt DePauw
  # Section:  7
  # Description: This is the file that holds the main and also takes in all the input.
  #             *Able to accept script files*
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class lab4 {

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
        MipsData.cleanup(rawLines);

        //Read in the script file from cmd line input -> args[1]
        //If not there its handled with try/catch
        File scriptFile = null;
        try {
            scriptFile = new File(args[1]);
        } catch (IndexOutOfBoundsException e) {
            //Just so it wont error if theres no script
        }

        /*If there is no script file found just move on
            Else add it to a string array to deal with later
         */
        List<String> scriptLines = new ArrayList<>();
        if (scriptFile != null) {
            Scanner scriptScan = new Scanner(scriptFile);
            while (scriptScan.hasNext()) {
                scriptLines.add(scriptScan.nextLine());
            }
            scriptScan.close();
        }

        /*
          Make a new instance of Mips - this is where all the work is done
          @param List<String> scriptlines - Holds all the lines from the script
        */
        Mips runMips = new Mips(scriptLines);
        runMips.mipsOutput();
    }


}
