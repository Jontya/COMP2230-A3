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
                System.out.println(frames.get(currPointerIndex).getProcessName() + " " + frames.get(currPointerIndex).getpageInstruction() + " " + currPointerIndex);
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
                if(!frames.get(i).getFirstRef()){
                    frames.get(i).swapFirstRef();
                }
                else{
                    frames.get(i).incReferenceCounter();
                }
                return true;
            }
        }
        return false;
    }

    public void moveCurrPointer(){
        if(currPointerIndex + 1 == frames.size()){
            currPointerIndex = 0;
        }
        else{
            currPointerIndex++;
        }
    }

    public int getBufferSize(){
        return frames.size();
    }

    public void addNewFrame(Page p){
        frames.add(p);
    }
}
