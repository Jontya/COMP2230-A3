//---------------------------------------------------------------------------------------------------
/** COMP2240 A3
*** Jonty Atkinson (C3391110)
*** 19/10/22
***
*** GCLOCK:
*** Provides implementation for the Generalized Clock replacement algorithm. Implementation for
*** page replacement, checking a page exists in the buffer and clock pointer functionality
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;

public class GCLOCK {
    private ArrayList<Page> frames;
    private int currPointerIndex;

    public GCLOCK (){
        frames = new ArrayList<>();
        currPointerIndex = 0;
    }

    public void replaceCurrFrame(Page p){
        // While the page has not been replaced
        while(true){
            // If a page with reference counter 0 is found 
            if(frames.get(currPointerIndex).getReferenceCounter() == 0){
                // Page is replaced with the new page
                frames.set(currPointerIndex, p);
                // Pointer is moved to the next frame
                moveCurrPointer();
                return;
            }
            // Else the current frames reference counter is decremented and the pointer is moved to the next frame
            frames.get(currPointerIndex).decReferenceCounter();
            moveCurrPointer();
        }
    }

    public boolean checkBuffer(Process p){
        // Loops through the buffer
        for(int i = 0; i < frames.size(); i++){
            // If the process + page is found
            if(frames.get(i).getProcessName().equals(p.getProcessName()) && frames.get(i).getpageInstruction() == p.getCurrentInstruction()){
                // Check if it has ever been referenced
                if(!frames.get(i).getFirstRef()){
                    // If not the status is swapped
                    frames.get(i).swapFirstRef();
                }
                else{
                    // Else the reference counter is incremented
                    frames.get(i).incReferenceCounter();
                }
                // Returns true if found
                return true;
            }
        }
        // Returns false if not found
        return false;
    }

    public void moveCurrPointer(){
        // If the pointer is as the end of the buffer
        if(currPointerIndex + 1 == frames.size()){
            // It is reset to the first frame
            currPointerIndex = 0;
        }
        else{
            // Else it is incremented
            currPointerIndex++;
        }
    }

    public int getBufferSize(){
        return frames.size();
    }

    public void addNewFrame(Page p){
        frames.add(p);
    }
}
