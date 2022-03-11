public class Cache2 {
    public int[] tagArr;
    public int assoc;
    public int blockSize;
    public int hitCount;
    public double hitRate;
    public int count;

    public Cache2() {
        tagArr = new int[256];
        assoc = 1;
        blockSize = 2;
        hitCount = 0;
        hitRate = 0;
        count = 0;
    }

    public void cache(int address) {
        count++;

        int index = (address >> 3) & 255;
        int tag = address >> 11 ;


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
        System.out.println("Cache #2");
        System.out.println("Cache size: " + 2048 + "B" +
                "\tAssociativity: " + assoc +
                "\tBlock size: " + blockSize);
        System.out.print("Hits: " + hitCount);
        System.out.printf("\tHit Rate: %.2f", hitRate);
        System.out.print("%\n");
        System.out.println("---------------------------");
    }
}
