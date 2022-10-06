import java.util.ArrayList;

public class Variable extends CPU{
    public Variable(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
    }

    @Override
    protected void loadIntoMemory() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean checkMemory() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void removeFromMemory() {
        // TODO Auto-generated method stub
        
    }
}
