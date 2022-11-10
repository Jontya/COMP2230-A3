//---------------------------------------------------------------------------------------------------
/** COMP2240 A3
*** Jonty Atkinson (C3391110)
*** 19/10/22
***
*** Process:
*** Provided functionality used by the ready queue, finished queue, blocked queue and round 
*** robin algorithm in the CPU class
**/
//---------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Queue;

public class Process implements Comparable<Process>{
    private Queue<Integer> processInstructions;
    private ArrayList<Integer> pageFaults;
    private int currInstruction;
    private String processName;
    private String currStatus;
    private int timeFinished;
    private int nextEvent;

    public Process(String _processName, Queue<Integer> _pageInstructions){
        processInstructions = _pageInstructions;
        pageFaults = new ArrayList<>();
        processName = _processName;
        currStatus = "ready";
        currInstruction = 0;
        timeFinished = 0;
        nextEvent = 0;
    }

    // Getters / Setters
    public String getProcessStatus(){
        return currStatus;
    }

    public void addNewPageFault(int time){
        pageFaults.add(time);
    }

    public int getPageFaultCount(){
        return pageFaults.size();
    }

    public String getProcessName(){
        return processName;
    }

    public int getPageCount(){
        return pageFaults.size();
    }

    public int getTimeFinished(){
        return timeFinished;
    }

    public void setFinishedTime(int _timeFinished){
        timeFinished = _timeFinished;
    }

    public int getCurrentInstruction(){
        return currInstruction;
    }

    public int getNextEvent(){
        return nextEvent;
    }

    public void setNextEvent(int time){
        nextEvent = time;
    }

    public void nextInstruction(){
        currInstruction = processInstructions.remove();
    }

    // Check if the process is finished
    public boolean isFinished(){
        if(processInstructions.isEmpty()){
            return true;
        }
        return false;
    }

    // Swaps the process status
    public void swapStatus(){
        if(currStatus.equals("ready")){
            currStatus = "blocked";
        }
        else{
            currStatus = "ready";
        }
    }

    // Returns a string with all the page fault time (Used in the stat reporter)
    public String getFaultTimes(){
        String out = "{";
        for(int i = 0; i < pageFaults.size(); i++){
            if((i + 1) != pageFaults.size()){
                out += Integer.toString(pageFaults.get(i)) + ", ";
            }
            else{
                out += Integer.toString(pageFaults.get(i));
            }
        }
        out += "}";
        return out;
    }

    // Gets the processID (Numbers at the end of a process file)
    public String getProcessID(){
        String[] temp = processName.split("\\.");
        String filename = temp[temp.length - 2];
        return Character.toString(filename.charAt(filename.length() - 1));
    }

    // Compare Method used by the finished queue in CPU
    @Override
    public int compareTo(Process process) {
        if(getProcessID().compareTo(process.getProcessID()) > 0){
            return 1;
        }
        return -1;
    }

}
