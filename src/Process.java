import java.util.ArrayList;
import java.util.Queue;

public class Process implements Comparable<Process>{
    private String processName;
    private String currStatus;
    private int blockedTime;
    private int timeFinished;
    private int currInstruction;
    private Queue<Integer> pageInstructions;
    private ArrayList<Integer> pageFaults;

    public Process(String _processName, Queue<Integer> _pageInstructions){
        processName = _processName;
        currInstruction = 0;
        timeFinished = 0;
        currStatus = "ready";
        pageInstructions = _pageInstructions;
        pageFaults = new ArrayList<>();
    }

    public String getStatus(){
        return currStatus;
    }

    public void newFault(int time){
        pageFaults.add(time);
    }

    public String getProcessName(){
        return processName;
    }

    public int getNumFaults(){
        return pageFaults.size();
    }

    public int getTimeFinished(){
        return timeFinished;
    }

    public int getCurrentInstruction(){
        return currInstruction;
    }

    public int getBlockedTime(){
        return blockedTime;
    }

    public void setBlockedTime(int time){
        blockedTime = time;
    }

    public void nextInstruction(){
        if(!pageInstructions.isEmpty()){
            if(currStatus.equals("blocked")){
                currStatus = "ready";
            }
            else{
                currInstruction = pageInstructions.remove();
            }
        }
    }

    public boolean isFinished(){
        if(pageInstructions.size() == 0){
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
