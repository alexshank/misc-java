package com.shankspace.journals.Journal;

import java.util.ArrayList;

/**
 * Class to represent an entire journal and all its entries
 */
public class JournalContent {

    public ArrayList<JournalEntry> entries;

    public JournalContent(){
        entries = new ArrayList<JournalEntry>();
        createEntries();
    }

    // method to create sample journal entries
    public void createEntries(){

        for(int i = 0; i < 20; i++){
            entries.add(new JournalEntry(i, "Title." + i, "content." + i));
        }

    }

    public JournalEntry getEntry(int i){
        return entries.get(i);
    }

    public ArrayList<JournalEntry> getEntries(){
        return entries;
    }

}
