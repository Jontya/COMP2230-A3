import java.util.ArrayList;

public class Process implements Comparable<Process>{
    private String processName;
    private String currStatus;
    private int timeFinished;
    private int currInstruction;
    private ArrayList<Integer> pageInstructions;
    private ArrayList<Integer> pageFaults;

    public Process(String _processName, ArrayList<Integer> _pageInstructions){
        processName = _processName;
        currInstruction = 0;
        timeFinished = 0;
        currStatus = "ready";
        pageInstructions = _pageInstructions;
        pageFaults = new ArrayList<>();
    }

    public void nextInstruction(int time){
        currInstruction++;
        if(currInstruction == pageInstructions.size()){
            timeFinished = time;
            currStatus = "finished";
        }
    }

    public String getStatus(){
        return currStatus;
    }

    public void swapStatus(){
        if(currStatus.equals("ready")){
            currStatus = "blocked";
        }
        else{
            currStatus = "ready";
        }
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

    public boolean isFinished(){
        if(currStatus.equals("finished")){
            return true;
        }
        return false;
    }

    public String getFaultTimes(){
        String out = "{";
        for(int i = 0; i < pageFaults.size(); i++){
            out += Integer.toString(pageFaults.get(i));
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
