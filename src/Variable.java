import java.util.ArrayList;
import java.util.Iterator;

public class Variable extends CPU{
    private ArrayList<Page> mainMemory;
    private int frames;

    public Variable(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
        mainMemory = new ArrayList<>();
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
        if(frames != 0){
            mainMemory.add(new Page(currProcess.getCurrentInstruction(), currProcess.getProcessName()));
            frames--;
        }
        else{
            boolean replaced = false;
            while(!replaced){
                for(int i = 0; i < mainMemory.size(); i++){
                    if(mainMemory.get(currClockPointer).getReferenceCounter() == 0){
                        mainMemory.set(currClockPointer, new Page(currProcess.getCurrentInstruction(), currProcess.getProcessName()));
                        frames--;
                        replaced = true;
                        break;
                    }
                    else{
                        mainMemory.get(currClockPointer).decReferenceCounter();
                    }

                    if(currClockPointer == mainMemory.size() - 1){
                        currClockPointer = 0;
                    }
                    else{
                        currClockPointer++;
                    }
                }
            }
        }
        
    }

    @Override
    protected boolean checkMemory() {
        for(int i = 0; i < mainMemory.size(); i++){
            if(mainMemory.get(i).getProcessName().equals(currProcess.getProcessName())){
                if(mainMemory.get(i).getpageInstruction() == currProcess.getCurrentInstruction()){
                    mainMemory.get(i).incReferenceCounter();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void removeFromMemory() {
        
    }
}
