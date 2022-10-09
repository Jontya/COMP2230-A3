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
    protected void primeReadyQueue() {
        for(int i = 0; i < processList.size(); i++){
            readyQueue.add(processList.get(i));
            mainMemory.put(processList.get(i).getProcessName(), new ArrayList<Page>());
        }
    }

    @Override
    protected void loadIntoMemory() {
        if(mainMemory.get(currProcess.getProcessName()).size() < numAllocatedFrames){
            mainMemory.get(currProcess.getProcessName()).add(new Page(currProcess.getCurrentInstruction()));
        }
        else{
            ArrayList<Page> temp = mainMemory.get(currProcess.getProcessName());
            boolean replaced = false;
            while(!replaced){
                if(temp.get(currClockPointer).getReferenceCounter() == 0){
                    temp.set(currClockPointer, new Page(currProcess.getCurrentInstruction()));
                    replaced = true;
                }
                else{
                    temp.get(currClockPointer).decReferenceCounter();
                }

                if(!replaced){
                    if(currClockPointer == temp.size() - 1){
                        currClockPointer = 0;
                    }
                    else{
                        currClockPointer++;
                    }
                }
            }
            mainMemory.replace(currProcess.getProcessName(), temp);
        }
    }

    @Override
    protected boolean checkMemory(){
        if(mainMemory.containsKey(currProcess.getProcessName())){
            ArrayList<Page> temp = mainMemory.get(currProcess.getProcessName());
            for(int i = 0; i < temp.size(); i++){
                if(temp.get(i).getpageInstruction() == currProcess.getCurrentInstruction()){
                    temp.get(i).incReferenceCounter();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void removeFromMemory() {
        if(mainMemory.containsKey(currProcess.getProcessName())){
            mainMemory.remove(currProcess.getProcessName());
        }
        
    }
}
