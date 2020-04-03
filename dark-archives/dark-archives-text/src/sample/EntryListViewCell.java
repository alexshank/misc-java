package sample;

import javafx.scene.control.ListCell;

public class EntryListViewCell extends ListCell<Entry> {

    @Override
    protected void updateItem(Entry entry, boolean empty) {
        super.updateItem(entry, empty);

        if(empty || entry == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(entry.title);
            setGraphic(null);

            // color the entry if it hasn't been seen
            if(!entry.wasSeen){
                setStyle("-fx-background-color:red;");
            } else {
                setStyle("");
            }

        }

    }// end of updateItem override
}