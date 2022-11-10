//---------------------------------------------------------------------------------------------------
/** COMP2240 A3
*** Jonty Atkinson (C3391110)
*** 19/10/22
***
*** CPU:
*** Abstract CPU class which includes the definition for the abstract methods and provides the 
*** implementation of the Round Robin scheduling algorithm, blocked queue checking method and stat 
*** printing method.
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;


public abstract class CPU {
    protected int currTime;
    protected String algoName;
    protected int timeQuantum;
    protected Process currProcess;
    protected Queue<Process> readyQueue;
    protected Queue<Process> blockedQueue;
    protected ArrayList<Process> processList;
    protected PriorityQueue<Process> finishedList;

    public CPU (int _timeQuantum, String _algoName, ArrayList<Process> _processList){
        currTime = 0;
        algoName = _algoName;
        timeQuantum = _timeQuantum;
        processList = _processList;
        readyQueue = new LinkedList<>();
        blockedQueue = new LinkedList<>();
        finishedList = new PriorityQueue<>();
    }

    public void run(){
        // Primes the ready queue
        primeReadyQueue();
        // Begins the scheduling algorithm loop
        while(finishedList.size() != processList.size()){
            // If the ready queue is empty
            if(readyQueue.isEmpty()){
                // If the blocked queue is also empty the program is finished
                if(blockedQueue.isEmpty()){
                    return;
                }

                // Moves to the next value in time (Blocked queue head item)
                currTime = blockedQueue.peek().getNextEvent();
                // Checks the blocked queue for removals
                checkBlockedQueue();
            }

            // Loads the next process onto the CPU
            currProcess = readyQueue.remove();
            // Checks process status
            if(currProcess.getProcessStatus().equals("ready")){
                currProcess.nextInstruction(); // Moves to the next instruction
            }
            else{
                currProcess.swapStatus(); // Changes process status
            }

            // Loops for time quantum steps
            for(int i = 0; i < timeQuantum; i++){
                // If the processes current page instruction is not loaded into memory
                if(!checkMemory()){
                    // It will be loaded into memory
                    loadIntoMemory();
                    // Updates variables and loads into the blocked queue
                    currProcess.swapStatus();
                    currProcess.setNextEvent(currTime + 6);
                    currProcess.addNewPageFault(currTime);
                    blockedQueue.add(currProcess);
                    // Will break out of the loop
                    break;
                }

                // Else the instruction will be loaded
                currTime++;
                // Checks the blocked queue
                if(!blockedQueue.isEmpty()){
                    checkBlockedQueue();
                }

                // Checks if the process is finished
                if(currProcess.isFinished()){
                    // Removes from memory and adds to finished queue
                    currProcess.setFinishedTime(currTime);
                    finishedList.add(currProcess);
                    removeFromMemory();
                    break;
                }
                else{
                    // Else it will add back to ready queue or move to the next instruction
                    if(i == timeQuantum - 1){
                        readyQueue.add(currProcess);
                    }
                    else{
                        currProcess.nextInstruction();
                    }
                }
            }
        }
    }

    // Loops through the blocked queue and adds processes back into the ready queue if they become unblocked
    public void checkBlockedQueue(){
        while(true){
            if(!blockedQueue.isEmpty()){
                if(blockedQueue.peek().getNextEvent() == currTime){
                    readyQueue.add(blockedQueue.remove());
                }
                else{
                    return;
                }
            }
            else{
                return;
            }
        }
    }

    // Prints the simulations report statistics
    public String getSimulationReport(){
        String out =  "GCLOCK - " + algoName + " Replacement:\n";
        out += "PID  Process Name      Turnaround Time  # Faults  Fault Times\n";
        while(!finishedList.isEmpty()){
            Process temp = finishedList.remove();
            out += String.format("%-4s %-17s %-16d %-9d %-4s", temp.getProcessID(), temp.getProcessName(), temp.getTimeFinished(), temp.getPageFaultCount(), temp.getFaultTimes()) + "\n";
        }
        return out;
    }

    protected abstract void primeReadyQueue();

    protected abstract boolean checkMemory();

    protected abstract void loadIntoMemory();

    protected abstract void removeFromMemory();
}
