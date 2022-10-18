import java.util.ArrayList;

public class GCLOCK {
    private ArrayList<Page> frames;
    private int currPointerIndex;

    public GCLOCK (){
        frames = new ArrayList<>();
        currPointerIndex = 0;
    }

    public void replaceCurrFrame(Page p){
        while(true){
            if(frames.get(currPointerIndex).getReferenceCounter() == 0){
                frames.set(currPointerIndex, p);
                moveCurrPointer();
                return;
            }
            frames.get(currPointerIndex).decReferenceCounter();
            moveCurrPointer();
        }
    }

    public boolean checkBuffer(Process p){
        for(int i = 0; i < frames.size(); i++){
            if(frames.get(i).getProcessName().equals(p.getProcessName()) && frames.get(i).getpageInstruction() == p.getCurrentInstruction()){
                frames.get(i).incReferenceCounter();
                moveCurrPointer();
                return true;
            }
        }
        return false;
    }

    public void moveCurrPointer(){
        if(currPointerIndex == frames.size() - 1){
            currPointerIndex = 0;
        }
        else{
            currPointerIndex++;
        }
    }

    public int removeProcess(Process p){
        int removeCount = 0;
        int index = 0;
        while(frames.size() != index){
            if(frames.get(index).getProcessName().equals(p.getProcessName())){
                frames.remove(index);
                removeCount++;

                if(currPointerIndex < index){
                    currPointerIndex--;
                }
            }
            else{
                index++;
            }
        }
        return removeCount;
    }

    public int getBufferSize(){
        return frames.size();
    }

    public void addNewFrame(Page p){
        frames.add(p);
    }

    public int getNumFrames(){
        return frames.size();
    }

    public void readPageInstruction(){
        frames.get(currPointerIndex).incReferenceCounter();
    }

    public void decCurrPointer(){
        frames.get(currPointerIndex).decReferenceCounter();
    }

    public void incCurrPointer(){
        frames.get(currPointerIndex).incReferenceCounter();
    }

    public int getCurrRefCounter(){
        return frames.get(currPointerIndex).getReferenceCounter();
    }
}
