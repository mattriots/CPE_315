public class Cache4 {
    public LRU[] tagArr1;
    public LRU[] tagArr2;
    public int cacheSize;
    public int assoc;
    public int blockSize;
    public int hitCount;
    public double hitRate;
    public int count;

    public Cache4() {
        tagArr1 = new LRU[1024];
        tagArr2 = new LRU[1024];
        for (int i = 0; i < tagArr1.length; i++) {
            tagArr1[i] = new LRU();
            tagArr2[i] = new LRU();
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
        int index = address & 1023;
        int tag = address >> 10;



        if (tagArr1[index].getTag() == tag) {
            hitCount++;
            tagArr1[index].setLineNum(lineNum);

        } else if (tagArr2[index].getTag() == tag) {
            hitCount++;
            tagArr2[index].setLineNum(lineNum);

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
