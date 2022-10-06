public class Page {
    private int pageInstruction;
    private int referenceCounter;

    public Page(int _pageInstruction){
        pageInstruction = _pageInstruction;
        referenceCounter = 0;
    }

    public int getpageInstruction(){
        return pageInstruction;
    }

    public int getReferenceCounter(){
        return referenceCounter;
    }

    public void decReferenceCounter(){
        referenceCounter--;
    }

    public void incReferenceCounter(){
        referenceCounter++;
    }
}
