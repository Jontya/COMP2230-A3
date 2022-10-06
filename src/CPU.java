import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;

public abstract class CPU {
    protected int currTime;
    protected int timeQuantum;
    protected String algoName;
    protected Queue<Process> readyQueue;
    protected Queue<Process> blockedQueue;
    protected ArrayList<Process> processList;
    protected PriorityQueue<Process> finishedList;

    private Process currProcess;

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
        for(int i = 0; i < processList.size(); i++){
            readyQueue.add(processList.get(i));
        }

        // Round Robin Scheduling Algorithm
        while(finishedList.size() != processList.size()){
            
        }
    }

    public void blockCurrProcess(){
        currProcess.newFault(currTime);
        if(!currProcess.getStatus().equals("blocked")){
            currProcess.swapStatus();
        }
        readyQueue.remove();
        blockedQueue.add(currProcess);
    }

    public String getSimulationReport(){
        String out =  "GCLOCK - " + algoName + " Replacement:\n";
        out += "PID  Process Name      Turnaround Time  # Faults  Fault Times\n";
        while(!finishedList.isEmpty()){
            Process temp = finishedList.remove();
            out += String.format("%-4d %-17s %-16d %-9d %-4s", temp.getProcessID(), temp.getProcessName(), temp.getTimeFinished(), temp.getNumFaults(), temp.getFaultTimes()) + "\n";
        }
        return out;
    }

    protected abstract void addToMainMem(Page page);

    protected abstract boolean checkInMainMem(Process process);

    protected abstract void removeProcessFromMemory(Process process);

}
