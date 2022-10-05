import java.util.ArrayList;

public class Variable extends CPU{
    public Variable(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
    }

    @Override
    protected void addToMainMem(Page page) {

    }

    @Override
    protected void checkInMainMem(Process process) {
        
    }
}
