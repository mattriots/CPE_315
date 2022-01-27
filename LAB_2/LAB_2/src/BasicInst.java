public class BasicInst {

    /*
    Could use this in the future to refator all the instructions to their own classes
     */

    protected String fullLine;
    protected String instruction;
    private BinaryData binaryData;


    public BasicInst(String fullLine){
        this.fullLine = fullLine;
        binaryData = new BinaryData();
    }
}
