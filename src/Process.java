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

    public boolean isFinished(){
        if(processInstructions.isEmpty()){
            return true;
        }
        return false;
    }

    public void swapStatus(){
        if(currStatus.equals("ready")){
            currStatus = "blocked";
        }
        else{
            currStatus = "ready";
        }
    }

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

    public int getProcessID(){
        String[] temp = processName.split("\\.");
        return Character.getNumericValue((temp[0].charAt(temp[0].length() - 1)));
    }

    @Override
    public int compareTo(Process process) {
        if(getProcessID() > process.getProcessID()){
            return 1;
        }
        return -1;
    }

}
