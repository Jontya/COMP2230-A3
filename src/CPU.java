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
        while(finishedList.size() != processList.size()){
            if(readyQueue.isEmpty()){
                if(blockedQueue.isEmpty()){
                    return;
                }

                currTime = blockedQueue.peek().getNextEvent();
                checkBlockedQueue();
                
            }

            currProcess = readyQueue.remove();
            if(currProcess.getProcessStatus().equals("ready")){
                currProcess.nextInstruction();
            }
            else{
                currProcess.swapStatus();
            }

            for(int i = 0; i < timeQuantum; i++){
                if(!checkMemory()){
                    loadIntoMemory();
                    currProcess.swapStatus();
                    currProcess.setNextEvent(currTime + 6);
                    currProcess.addNewPageFault(currTime);
                    blockedQueue.add(currProcess);
                    break;
                }

                currTime++;
                if(!blockedQueue.isEmpty()){
                    checkBlockedQueue();
                }

                if(currProcess.isFinished()){
                    currProcess.setFinishedTime(currTime);
                    finishedList.add(currProcess);
                    break;
                }
                else{
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

    public void checkBlockedQueue(){
        boolean checked = false;
        while(!checked){
            if(!blockedQueue.isEmpty()){
                if(blockedQueue.peek().getNextEvent() == currTime){
                    readyQueue.add(blockedQueue.remove());
                }
                else{
                    checked = true;
                }
            }
            else{
                checked = true;
            }
        }
    }

    protected abstract void loadIntoMemory();

    protected abstract boolean checkMemory();

    protected abstract void removeFromMemory();


    
    public String getSimulationReport(){
        String out =  "GCLOCK - " + algoName + " Replacement:\n";
        out += "PID  Process Name      Turnaround Time  # Faults  Fault Times\n";
        while(!finishedList.isEmpty()){
            Process temp = finishedList.remove();
            out += String.format("%-4d %-17s %-16d %-9d %-4s", temp.getProcessID(), temp.getProcessName(), temp.getTimeFinished(), temp.getPageFaultCount(), temp.getFaultTimes()) + "\n";
        }
        return out;
    }

}
