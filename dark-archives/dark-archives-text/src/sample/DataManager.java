package sample;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataManager {

    // object variables
    public ArrayList<ArrayList<Entry>> groupList;
    private String[] groupPaths;

    /*******
     * constructor
     */
    public DataManager(){

        // initialize list of groups and add groups
        groupList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            this.groupList.add(new ArrayList<>());
        }

        this.groupPaths = new String[] {"\\movies.txt","\\books.txt","\\tv.txt","\\music.txt","\\other.txt"};

    }


    /*******
     * read all the program's data from text files
     */
    public void loadData(){
        try {
            // variables for storing read data
            BufferedReader br = null;
            String line;
            String title;
            String notes;
            int index;
            boolean wasSeen;

            // find save directory
            String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Dark Archive Save Files";

            // loop through all the files
            for(int i = 0; i < 5; i++) {

                // create file reader
                br = new BufferedReader(new FileReader(path + this.groupPaths[i]));

                // read each line of the file
                line = br.readLine();
                while (line != null) {

                    // get each variable value from each line in the file (remove "" from beginning and end)
                    String[] arr = line.split("<>");
                    index = Integer.parseInt(arr[0]);
                    title = arr[1];
                    notes = arr[2];
                    wasSeen = arr[3].equals("true");

                    // depending on the group, add the item to the correct list
                    Entry entry = new Entry(i, index, title, notes, wasSeen);
                    groupList.get(i).add(entry);
                    line = br.readLine();
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*******
     * write all the program's data to text files
     */
    public void saveData(){
        try {

            // create save directory if not already present
            String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Dark Archive Save Files";
            File newDir = new File(path);
            if(!newDir.exists()){
                newDir.mkdir();
            }

            // print data to files
            PrintWriter pw;
            for(int i = 0; i < 5; i++){
                // write each entry as a line in each group's corresponding text file
                pw = new PrintWriter(path + groupPaths[i]);
                for(Entry e : groupList.get(i)){
                    pw.write(e.index + "<>" + e.title + "<>" + e.notes + "<>" + e.wasSeen + "\n");
                }
                pw.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**************
     * index each list in alphabetical order by title
     */
    public void indexLists(){
        for(ArrayList<Entry> list : groupList){

            // override comparison method using lambda expression
            if (list.size() > 0) {

                // use regex to sort by ignore "The" at the beginning
                Collections.sort(list, (object1, object2) -> object1.title.replaceAll("^(?i)The ", "").toLowerCase().compareTo(object2.title.replaceAll("^(?i)The ", "").toLowerCase()));

                // list unseen entries first
                Collections.sort(list, (e1, e2) -> Boolean.compare(e1.wasSeen, e2.wasSeen));

            }

            // index each sorted entry
            int index = 0;
            for(Entry e : list){
                e.index = index;
                index++;
            }

        }
    }


    /***********
     * return true if an entry exists with the given title and group index
     */
    public boolean entryAlreadyExists(String title, int groupIndex){
        return getEntry(title, groupIndex) != null;
    }


    /******
     * get item based on title and group index
     */
    public Entry getRandom(){
        int randomGroupIndex = ThreadLocalRandom.current().nextInt(0, 5);   // (min, max + 1)
        int randomEntry = ThreadLocalRandom.current().nextInt(0,groupList.get(randomGroupIndex).size());
        return groupList.get(randomGroupIndex).get(randomEntry);
    }


    /************
     * get item based on title and group index
     */
    public Entry getEntry(String title, int groupIndex){
        for(ArrayList<Entry> list : groupList){
            for(Entry e : list){
                if(e.title.toLowerCase().equals(title.toLowerCase()) && e.groupIndex == groupIndex){
                    return e;
                }
            }
        }
        return null;
    }


    /***********
     * return list of entries that have titles containing a given string
     */
    public ArrayList<Entry> getMatchingEntries(String s){
        ArrayList<Entry> entryList = new ArrayList<>();
        for(List<Entry> list : groupList){
            for(Entry e : list){
                if(e.title.toLowerCase().contains(s.toLowerCase())){
                    entryList.add(e);
                }
            }
        }
        return entryList;
    }


    /**********
     * add an entry to the data manager and save the data
     */
    public void addEntry(String title, String notes, boolean wasSeen, int groupIndex){
        Entry e = new Entry(groupList.get(groupIndex).size(), groupIndex, title, notes, wasSeen);
        groupList.get(groupIndex).add(e);
        indexLists();
        saveData();
    }


    /**********
     * update an entry in the data manager and save the data
     */
    public void update(String title, String notes, int groupIndex, boolean wasSeen, String oldTitle, int oldGroupIndex){
        Entry e = getEntry(oldTitle,oldGroupIndex);
        e.title = title;
        e.notes = notes;
        e.groupIndex = groupIndex;
        e.wasSeen = wasSeen;

        indexLists();
        saveData();
    }


    /**********
     * delete an entry in the data manager and save the data
     */
    public void deleteEntry(String title, int groupIndex){
        for(ArrayList<Entry> list : groupList){
            // use iterator so that entries can be removed while iterating
            Iterator<Entry> iterator = list.iterator();
            while(iterator.hasNext()){
                Entry e = iterator.next();
                if(e.title.equals(title) && e.groupIndex == groupIndex){
                    iterator.remove();
                }
            }
        }
        indexLists();
        saveData();
    }

}