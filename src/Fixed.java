import java.util.ArrayList;
import java.util.HashMap;
public class Fixed extends CPU{
    private int numAllocatedFrames;
    private HashMap<String, ArrayList<Page>> mainMemory;

    public Fixed(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);

        mainMemory = new HashMap<>();
        numAllocatedFrames = _frames / processList.size();
    }

    @Override
    protected void addToMainMem(Page process) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean checkInMainMem(Process process) {
        if(mainMemory.containsKey(process.getProcessName())){
            ArrayList<Page> temp = mainMemory.get(process.getProcessName());
            for(int i = 0; i < temp.size(); i++){
                if(temp.get(i).getpageInstruction() == process.getCurrentInstruction()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void removeProcessFromMemory(Process process) {
        if(mainMemory.containsKey(process.getProcessName())){
            mainMemory.remove(process.getProcessName());
        }
    }
}
