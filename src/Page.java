public class Page {
    private int pageID;
    private String processName;

    public Page(int _pageID, String _processName){
        pageID = _pageID;
        processName  = _processName;
    }

    public int getPageID(){
        return pageID;
    }

    public String getProcessName(){
        return processName;
    }
}
