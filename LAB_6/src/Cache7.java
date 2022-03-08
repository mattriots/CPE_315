public class Cache7 {
    public int[] tagArr;
    public int cacheSize;
    public int assoc;
    public int blockSize;

    public int hitCount;
    public double hitRate;
    public int count;

    public Cache7(){
        tagArr = new int[4096];
        cacheSize = tagArr.length;
        assoc = 1;
        blockSize = 1;
        hitCount = 0;
        hitRate = 0;
        count = 0;
    }

    public void cache(int address){
        count++;
//        int index = address % cacheSize;

        int index = address % 4096;
        int tag = address / 4096;

        if(tagArr[index] == 0){
            tagArr[index] = tag;
        }else if(tagArr[index] != tag){
            tagArr[index] = tag;
        }else if(tagArr[index] == tag){
            hitCount++;
        }

//        System.out.println(tagArr[index]);
//        System.out.println(address + " " + index  + " ");
//        System.out.println("---------------------------");
    }

    public void output(){
        hitRate = (hitCount / (float)(count));
        hitRate *= 100;
        System.out.println("Cache #7");
        System.out.println("Cache size: "+ cacheSize + "B" +
                "\tAssociativity: "+ assoc +
                "\tBlock size: "+ blockSize);
        System.out.print("Hits: " + hitCount);
        System.out.printf("\tHit Rate: %.2f",  hitRate );
        System.out.print("%\n");
        System.out.println("---------------------------");
    }
}
