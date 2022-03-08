/*
  # Name: Matt DePauw
  # Section:  7
  # Description:  Simulates multiple types of Caches.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class lab6 {

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);

        Cache cache = new Cache();
        Cache2 cache2 = new Cache2();
        Cache3 cache3 = new Cache3();
        Cache4 cache4 = new Cache4();
        Cache5 cache5 = new Cache5();
        Cache6 cache6 = new Cache6();
        Cache7 cache7 = new Cache7();

        int lineNum = 0;
        while(scanner.hasNext()){
            scanner.next();
            int address = Integer.parseInt(scanner.next(), 16);

            cache.cache(address);
            cache2.cache(address);
            cache3.cache(address);
            cache4.cache(address, lineNum);
            cache5.cache(address, lineNum);
            cache6.cache(address, lineNum);
            cache7.cache(address);
            lineNum++;
        }
        cache.output();
        cache2.output();
        cache3.output();
        cache4.output();
        cache5.output();
        cache6.output();
        cache7.output();
    }


}
