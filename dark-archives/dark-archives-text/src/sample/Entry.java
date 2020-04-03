package sample;

/**
 * Created by ashan on 5/18/2017.
 */
public class Entry {

    // object variables
    public int groupIndex;
    public int index;
    public String title;
    public String notes;
    public boolean wasSeen;

    /*******
     * constructor
     */
    public Entry(int groupIndex, int index, String title, String notes, boolean wasSeen){
        this.groupIndex = groupIndex;
        this.index = index;
        this.title = title;
        this.notes = notes;
        this.wasSeen = wasSeen;
    }

}
