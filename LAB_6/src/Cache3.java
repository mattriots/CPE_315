public class Cache3 {
    public int[] tagArr;
    public int cacheSize;
    public int assoc;
    public int blockSize;
    public int hitCount;
    public double hitRate;
    public int count;

    public Cache3(){
        tagArr = new int[128]; //Each index is a byte
        cacheSize = tagArr.length;
        assoc = 1;
        blockSize = 4;
        hitCount = 0;
        hitRate = 0;
        count = 0;
    }

    public void cache(int address) {
        count++;
        int bytOffset = address & 3;
        int blkOffset = (address >> 2) % 3;
//        if(blkOffset > 0){
//            System.out.println(blkOffset);
//        }

        int index = (address / 16) % 128;
        int tag = address / 2048;

//        System.out.println("ByteOff:\t" + bytOffset);
//        System.out.println("BlkOff:\t" + blkOffset);
//        System.out.println("index:\t" + index);
//        System.out.println("tag:\t" + tag);


        if (tagArr[index] == 0) {
            tagArr[index] = tag;
        } else if (tagArr[index] != tag) {
            tagArr[index] = tag;
        } else if (tagArr[index] == tag) {
            hitCount++;
        }


    }

    public void output() {
        hitRate = (hitCount / (float) (count));
        hitRate *= 100;
        System.out.println("Cache #3");
        System.out.println("Cache size: " + cacheSize + "B" +
                "\tAssociativity: " + assoc +
                "\tBlock size: " + blockSize);
        System.out.print("Hits: " + hitCount);
        System.out.printf("\tHit Rate: %.2f", hitRate);
        System.out.print("%\n");
        System.out.println("---------------------------");
    }
}
