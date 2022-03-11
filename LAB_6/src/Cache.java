

public class Cache {
    public int[] tagArr;
    public int cacheSize;
    public int assoc;
    public int blockSize;
    public int hitCount;
    public float hitRate;
    public int count;

    public Cache(){
        tagArr = new int[2048];
        cacheSize = tagArr.length;
        assoc = 1;
        blockSize = 1;
        hitCount = 0;
        count = 0;
    }

    public void cache(int address){
        count++;

        int index = address & 2047;
        int tag = address >> 11;

        if(tagArr[index] == 0){
            tagArr[index] = tag;
        }else if(tagArr[index] != tag){
            tagArr[index] = tag;
        }else if(tagArr[index] == tag){
            hitCount++;
        }

    }

    public void output(){
        hitRate = (hitCount / (float)(count));
        hitRate *= 100;
        System.out.println("Cache #1");
        System.out.println("Cache size: "+ cacheSize + "B" +
                "\tAssociativity: "+ assoc +
                "\tBlock size: "+ blockSize);
        System.out.print("Hits: " + hitCount);
        System.out.printf("\tHit Rate: %.2f",  hitRate );
        System.out.print("%\n");
        System.out.println("---------------------------");
    }



}
