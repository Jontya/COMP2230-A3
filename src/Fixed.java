import java.util.ArrayList;

public class Fixed extends CPU{
    public Fixed(int _frames, int _timeQuantum, ArrayList<Process> _processList, String _algoName){
        super(_timeQuantum, _algoName, _processList);
    }

    @Override
    protected void addToMainMem(Page page) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void checkInMainMem(Process process) {
        // TODO Auto-generated method stub
        
    }
}
