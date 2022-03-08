public class Cache5 {
    public LRU2[] tagArr1;
    public LRU2[] tagArr2;
    public LRU2[] tagArr3;
    public LRU2[] tagArr4;
    public int cacheSize;
    public int assoc;
    public int blockSize;
    public int hitCount;
    public double hitRate;
    public int count;

    public Cache5() {
        tagArr1 = new LRU2[512];
        tagArr2 = new LRU2[512];
        tagArr3 = new LRU2[512];
        tagArr4 = new LRU2[512];
        for (int i = 0; i < tagArr1.length; i++) {
            tagArr1[i] = new LRU2();
            tagArr2[i] = new LRU2();
            tagArr3[i] = new LRU2();
            tagArr4[i] = new LRU2();
        }
        cacheSize = 2048;
        assoc = 4;
        blockSize = 1;
        hitCount = 0;
        hitRate = 0;
        count = 0;
    }

    public void cache(int address, int lineNum) {
        count++;

        int index = address % 512;
        int tag = address / 512;


        if (tagArr1[index].getTag() == tag) {
            hitCount++;
            tagArr1[index].setLineNum(lineNum);
//
        } else if (tagArr2[index].getTag() == tag) {
            hitCount++;
            tagArr2[index].setLineNum(lineNum);
//
        } else if (tagArr3[index].getTag() == tag) {
            hitCount++;
            tagArr3[index].setLineNum(lineNum);
//
        } else if (tagArr4[index].getTag() == tag) {
            hitCount++;
            tagArr4[index].setLineNum(lineNum);
//
        }
        // Find the first empty one
        else if (tagArr1[index].getTag() == 0) {
            tagArr1[index].setTag(tag);
            tagArr1[index].setLineNum(lineNum);//miss
        } else if (tagArr2[index].getTag() == 0) {
            tagArr2[index].setTag(tag);
            tagArr2[index].setLineNum(lineNum);//miss
        } else if (tagArr3[index].getTag() == 0) {
            tagArr3[index].setTag(tag);
            tagArr3[index].setLineNum(lineNum);//miss
        } else if (tagArr4[index].getTag() == 0) {
            tagArr4[index].setTag(tag);
            tagArr4[index].setLineNum(lineNum);//miss
        }
        //Find the oldest
        else if (oldest(index) == tagArr1[index].getLineNum()) {
            tagArr1[index].setTag(tag);
            tagArr1[index].setLineNum(lineNum);
        } else if (oldest(index) == tagArr2[index].getLineNum()) {
            tagArr2[index].setTag(tag);
            tagArr2[index].setLineNum(lineNum);
        } else if (oldest(index) == tagArr3[index].getLineNum()) {
            tagArr3[index].setTag(tag);
            tagArr3[index].setLineNum(lineNum);
        } else if (oldest(index) == tagArr4[index].getLineNum()) {
            tagArr4[index].setTag(tag);
            tagArr4[index].setLineNum(lineNum);
        }

    }

    public int oldest(int index) {
        int one = tagArr1[index].getLineNum();
        int two = tagArr2[index].getLineNum();
        int three = tagArr3[index].getLineNum();
        int four = tagArr4[index].getLineNum();

        return Math.min(one, Math.min(two, Math.min(three, four)));
    }

    public void output() {
        hitRate = (hitCount / (float) (count));
        hitRate *= 100;
        System.out.println("Cache #5");
        System.out.println("Cache size: " + 2048 + "B" +
                "\tAssociativity: " + assoc +
                "\tBlock size: " + blockSize);
        System.out.print("Hits: " + hitCount);
        System.out.printf("\tHit Rate: %.2f", hitRate);
        System.out.print("%\n");
        System.out.println("---------------------------");
    }
}
