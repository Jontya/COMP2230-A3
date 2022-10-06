import java.util.ArrayList;

public class Variable extends CPU{
    public Variable(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
    }

    @Override
    protected void addToMainMem(Page page) {

    }

    @Override
    protected boolean checkInMainMem(Process process) {
        return false;
    }

    @Override
    protected void removeProcessFromMemory(Process process) {
        // TODO Auto-generated method stub
        
    }
}
