public class LRU2 {

    private int tag;
    private int lineNum;


    public LRU2(){
        this.tag = 0;
        this.lineNum = 0;
    }


    public LRU2(int index, int lineNum){
        this.tag = index;
        this.lineNum = lineNum;
    }

    public int getTag(){
        return this.tag;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }


}
