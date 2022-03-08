/*
  # Name: Matt DePauw
  # Section:  7
  # Description:  Data structure to hold the tag and line number for LRU
 */


public class LRU {

    private int tag;
    private int lineNum;


    public LRU(){
        this.tag = 0;
        this.lineNum = 0;
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
