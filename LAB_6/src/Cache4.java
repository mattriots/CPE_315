import java.util.*;

public class Cache4 {
    public LRU2[] tagArr1;
    public LRU2[] tagArr2;
    public int cacheSize;
    public int assoc;
    public int blockSize;
    public int hitCount;
    public double hitRate;
    public int count;

    public Cache4() {
        tagArr1 = new LRU2[1024];
        tagArr2 = new LRU2[1024];
        for (int i = 0; i < tagArr1.length; i++) {
            tagArr1[i] = new LRU2();
            tagArr2[i] = new LRU2();
        }
        cacheSize = 2048;
        assoc = 2;
        blockSize = 1;
        hitCount = 0;
        hitRate = 0;
        count = 0;
    }

    /*
    Still having issues getting this one to work.
    Getting too high of a hit count
    */

    public void cache(int address, int lineNum) {
        count++;
        int bytOffset = address & 3;
        int blkOffset = (address >> 2) & 1;
        int index = address % 1024;
        int tag = address / 1024;

//        System.out.println("ByteOff:\t" + bytOffset);
//        System.out.println("BlkOff:\t" + blkOffset);
//        System.out.println("index:\t" + index);
//        System.out.println("tag:\t" + tag);

        if (tagArr1[index].getTag() == tag) {
            hitCount++;
            tagArr1[index].setLineNum(lineNum);
//            System.out.println("Hit");
        } else if (tagArr2[index].getTag() == tag) {
            hitCount++;
            tagArr2[index].setLineNum(lineNum);
//            System.out.println("Hit");
        } else if (tagArr1[index].getTag() == 0) {
            tagArr1[index].setTag(tag);
            tagArr1[index].setLineNum(lineNum);//miss
        } else if (tagArr2[index].getTag() == 0) {
            tagArr2[index].setTag(tag);
            tagArr2[index].setLineNum(lineNum);//miss
        } else if (tagArr1[index].getLineNum() < tagArr2[index].getLineNum()) {
            tagArr1[index].setTag(tag);
            tagArr1[index].setLineNum(lineNum);
        } else if (tagArr1[index].getLineNum() > tagArr2[index].getLineNum()) {
            tagArr2[index].setTag(tag);
            tagArr2[index].setLineNum(lineNum);
        }

//        System.out.println("Arr1: " + tagArr1[index].getTag() + " , "
//                + tagArr1[index].getLineNum());
//        System.out.println("Arr2: " + tagArr2[index].getTag() + " , "
//                + tagArr2[index].getLineNum());
//        System.out.println("Index: " + index);
//        System.out.println("Tag: " + tag);
//        System.out.println("------------------------------");

    }


    public void output() {
        hitRate = (hitCount / (float) (count));
        hitRate *= 100;
        System.out.println("Cache #4");
        System.out.println("Cache size: " + cacheSize + "B" +
                "\tAssociativity: " + assoc +
                "\tBlock size: " + blockSize);
        System.out.print("Hits: " + hitCount);
        System.out.printf("\tHit Rate: %.2f", hitRate);
        System.out.print("%\n");
        System.out.println("---------------------------");
    }
}
