package com.shankspace.journals.Journal;

import java.util.Date;

/**
 * Class to represent one journal entry
 */
public class JournalEntry {

    int _id;
    String _title;
    String _content;
    Date _date;

    public JournalEntry(int id, String title, String content){
        _id = id;
        _title = title;
        _content = content;
        _date = new Date();
    }


}
