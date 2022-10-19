//---------------------------------------------------------------------------------------------------
/** COMP2240 A3
*** Jonty Atkinson (C3391110)
*** 19/10/22
***
*** Variable:
*** Provides implementation for the variable-global resident set management scheme. Includes
*** methods to check if a page is in memory, load a page into memory, remove a process from
*** memory and prime the ready queue. 
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;

public class Variable extends CPU{
    private GCLOCK mainMemory;
    private int frames;

    public Variable(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
        mainMemory = new GCLOCK();
        frames = _frames;
    }

    // Primes the ready queue
    @Override
    protected void primeReadyQueue() {
        for(int i = 0; i < processList.size(); i++){
            readyQueue.add(processList.get(i));
        }
    }

    @Override
    protected void loadIntoMemory() {
        // Creates a new page
        Page page = new Page(currProcess.getCurrentInstruction(), currProcess.getProcessName());
        // If there is still free frames
        if(frames != 0){
            // Adds page to a frame and decrements the number of free frames
            mainMemory.addNewFrame(page);
            frames--;
        }
        else{
            // Else the GCLOCK algorithm will find a page to be replaced
            mainMemory.replaceCurrFrame(page);
        }
    }

    // Checks if a process is already loaded into memory
    @Override
    protected boolean checkMemory() {
        if(mainMemory.checkBuffer(currProcess)){
            return true;
        }
        return false;
    }

    // Variable-Global does not need to remove any frames from memory
    @Override
    protected void removeFromMemory() {
        return;
    }
}
