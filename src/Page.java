//---------------------------------------------------------------------------------------------------
/** COMP2240 A3
*** Jonty Atkinson (C3391110)
*** 19/10/22
***
*** Page:
*** Contains the basic implementation for a page which will be stored in the GCLOCK class
**/
//---------------------------------------------------------------------------------------------------

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

    // Constructor
    public Page(int _pageInstruction, String _processName){
        pageInstruction = _pageInstruction;
        referenceCounter = 0;
        processName = _processName;
    }

    // Getters / Setters
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
