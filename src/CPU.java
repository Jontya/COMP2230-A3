import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public abstract class CPU {
    protected int currTime;
    protected int timeQuantum;
    protected String algoName;
    protected Queue<Process> readyQueue;
    protected ArrayList<Process> blockedQueue;
    protected ArrayList<Process> processList;
    protected PriorityQueue<Process> finishedList;
    protected Process currProcess;

    public CPU (int _timeQuantum, String _algoName, ArrayList<Process> _processList){
        currTime = 0;
        algoName = _algoName;
        timeQuantum = _timeQuantum;
        processList = _processList;
        readyQueue = new LinkedList<>();
        blockedQueue = new ArrayList<>();
        finishedList = new PriorityQueue<>();
    }

    public void run(){
        while(finishedList.size() != processList.size()){
            
        }
    }

    public void checkBlockedQueue(){
        if(!blockedQueue.isEmpty()){
            ArrayList<Process> temp = new ArrayList<>();
            int size = blockedQueue.size();
            for(int i = 0; i < size; i++){
                if(blockedQueue.get(i).getBlockedTime() == currTime){
                    temp.add(blockedQueue.get(i));
                }
            }

            for(int i = 0; i < temp.size(); i++){
                readyQueue.add(temp.get(i));
            }
        }
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

    protected abstract void loadIntoMemory();

    protected abstract boolean checkMemory();

    protected abstract void removeFromMemory();

}
