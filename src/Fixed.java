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

    @Override
    protected void primeReadyQueue() {
        for(int i = 0; i < processList.size(); i++){
            readyQueue.add(processList.get(i));
            mainMemory.put(processList.get(i).getProcessName(), new GCLOCK());
        }
    }

    @Override
    protected void loadIntoMemory() {
        GCLOCK temp = mainMemory.get(currProcess.getProcessName());
        Page page = new Page(currProcess.getCurrentInstruction(), currProcess.getProcessName());
        if(temp.getBufferSize() != numAllocatedFrames){
            temp.addNewFrame(page);
        }
        else{
            temp.replaceCurrFrame(page);
        }  
    }

    @Override
    protected boolean checkMemory(){
        if(mainMemory.get(currProcess.getProcessName()).checkBuffer(currProcess)){
            return true;
        }
        return false;
    }

    @Override
    protected void removeFromMemory() {
        mainMemory.remove(currProcess.getProcessName());
    }
}
