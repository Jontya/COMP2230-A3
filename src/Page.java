public class Page { 
    private String processName;
    private int pageInstruction;
    private int referenceCounter;
    private boolean firstRef;

    public Page(int _pageInstruction){
        pageInstruction = _pageInstruction;
        referenceCounter = 0;
        firstRef = false;
    }

    public Page(int _pageInstruction, String _processName){
        pageInstruction = _pageInstruction;
        referenceCounter = 0;
        processName = _processName;
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

    public String getProcessName(){
        return processName;
    }

    public boolean getFirstRef(){
        return firstRef;
    }

    public void swapFirstRef(){
        firstRef = true;
    }
}
