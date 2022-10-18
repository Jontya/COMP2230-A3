import java.util.ArrayList;

public class Variable extends CPU{
    private GCLOCK mainMemory;
    private int frames;

    public Variable(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
        mainMemory = new GCLOCK();
        frames = _frames;
    }

    @Override
    protected void primeReadyQueue() {
        for(int i = 0; i < processList.size(); i++){
            readyQueue.add(processList.get(i));
        }
    }

    @Override
    protected void loadIntoMemory() {
        Page page = new Page(currProcess.getCurrentInstruction(), currProcess.getProcessName());
        if(frames != 0){
            mainMemory.addNewFrame(page);
            frames--;
        }
        else{
            mainMemory.replaceCurrFrame(page);
        }
    }

    @Override
    protected boolean checkMemory() {
        if(mainMemory.checkBuffer(currProcess)){
            return true;
        }
        return false;
    }

    @Override
    protected void removeFromMemory() {
        return;
    }
}
