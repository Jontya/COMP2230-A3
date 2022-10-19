//---------------------------------------------------------------------------------------------------
/** COMP2240 A3
*** Jonty Atkinson (C3391110)
*** 19/10/22
***
*** Fixed:
*** Provides implementation for the fixed-local resident set management scheme. Includes
*** methods to check if a page is in memory, load a page into memory, remove a process from
*** memory and prime the ready queue. 
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.HashMap;

public class Fixed extends CPU{
    private int numAllocatedFrames;
    private HashMap<String, GCLOCK> mainMemory;

    public Fixed(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
        mainMemory = new HashMap<>();
        numAllocatedFrames = _frames / processList.size();
    }

    // Primes the ready queue with the processes in the ready queue
    @Override
    protected void primeReadyQueue() {
        for(int i = 0; i < processList.size(); i++){
            readyQueue.add(processList.get(i));
            mainMemory.put(processList.get(i).getProcessName(), new GCLOCK());
        }
    }

    @Override
    protected void loadIntoMemory() {
        // Gets the processes frame buffer and creates a new page to be loaded
        GCLOCK temp = mainMemory.get(currProcess.getProcessName());
        Page page = new Page(currProcess.getCurrentInstruction(), currProcess.getProcessName());
        // If the buffer has space the page will be added to the buffer
        if(temp.getBufferSize() != numAllocatedFrames){
            temp.addNewFrame(page);
        }
        else{
            // Else the GCLOCK algorithm will find a page to be replaced
            temp.replaceCurrFrame(page);
        }  
    }

    // Checks if a page is loaded into memory for a current process
    @Override
    protected boolean checkMemory(){
        if(mainMemory.get(currProcess.getProcessName()).checkBuffer(currProcess)){
            return true;
        }
        return false;
    }

    // Removes a process from memory
    @Override
    protected void removeFromMemory() {
        mainMemory.remove(currProcess.getProcessName());
    }
}
