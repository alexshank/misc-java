package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main extends Application {

    // fields (variables used in multiple methods)
    private PasswordField pwf;
    private Stage myStage;  // make primaryStage accessible from anywhere
    private String css;     // location of css file
    private GridPane grid;      // instance variables so the layouts can be hidden when their collapse methods are called
    private VBox searchVBox;
    private VBox randomVBox;
    private ListView<String> movieList;     // lists...
    private ListView<String> bookList;
    private ListView<String> tvList;
    private ListView<String> musicList;
    private ListView<String> otherList;
    private ListView<String> selectedList;  // selected list and selected list's index
    private int groupIndex = 0;
    private TextField title;
    private TextArea notes;
    private Label randomOutput;

    private TextField searchField;
    private ListView<String> searchResults = new ListView<>();

    // control which of the three action button layouts (if any) are displayed above the lists
    private boolean showInput = true;
    private boolean showSearch = true;
    private boolean showRandom = true;

    /**********************************************
     * initial java method that calls initial javafx method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**********************************************
     * method that runs at start and creates the initial GUI
     */
    public void start(Stage primaryStage) throws Exception{

        // myStage and primaryStage reference the same object
        myStage = primaryStage;

        // create image object
        Image image = new Image(getClass().getResourceAsStream("folder.png"));

        // set window title and image
        primaryStage.setTitle("Dark Archive");
        primaryStage.getIcons().add(image);

        // don't let the window take up too much space
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(400);

        // create login layout
        VBox vert = new VBox();

        // create imageview
        ImageView iv = new ImageView(image);
        iv.setFitHeight(175);
        iv.setPreserveRatio(true);

        // create label
        Label title = new Label("Dark Archive");
        title.setFont(new Font(30));    // set font size

        // create password field
        pwf = new PasswordField();
        pwf.setMaxWidth(200);   // don't let it get too big
        pwf.setOnAction(e -> enter(pwf.getText())); // when enter is pressed call an enter method

        // add components to the layout
        vert.getChildren().addAll(iv, title, pwf);

        // center the layout
        vert.setAlignment(Pos.CENTER);

        // create a new Scene with the layout
        Scene login = new Scene(vert);

        // set the stylesheet for the program
        css = this.getClass().getResource("stylish.css").toExternalForm(); // weird, but gets the stylesheet's filepath
        login.getStylesheets().add(css);

        // set the Scene and view it
        primaryStage.setScene(login);
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
        primaryStage.show();

    }// End of start method

    /**********************************************
     * method to validate user password
     */
    private void enter(String in){
        if(in.equals("coastal")){
            createMainScene();
            pwf.clear();
        }
        else{
            Alert awShit = new Alert(Alert.AlertType.ERROR);
            awShit.setTitle("Oops");
            awShit.setHeaderText("Try again!");
            awShit.setContentText("Your password didn't work.");
            awShit.showAndWait();
            pwf.clear();
        }
    }// End of method enter

    /**********************************************
     * method to create main screen
     */
    private void createMainScene(){

        // create layout and center the components at the top
        VBox mainVert = new VBox();
        mainVert.setAlignment(Pos.TOP_CENTER);

        /****************************************************
         * Create button area for main actions
         */
        // create the scene's action buttons
        Button collapse = new Button("Input");
        collapse.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        collapse.setOnAction(e -> collapseInput());
        Button search = new Button("Search");
        search.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        search.setOnAction(e -> collapseSearch());
        Button random = new Button("Random");
        random.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        random.setOnAction(e -> collapseRandom());

        // create grid layout for action buttons
        GridPane gridForButtons = new GridPane();
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(33.3);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33.3);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33.3);
        gridForButtons.getColumnConstraints().addAll(col0, col1, col2);
        gridForButtons.setPadding(new Insets(10, 10, 0, 10));   // space the left, right, and top of the 3 buttons
        GridPane.setHalignment(collapse, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(collapse, VPos.CENTER); // To align vertically in the cell
        GridPane.setHalignment(search, HPos.CENTER);
        GridPane.setValignment(search, VPos.CENTER);
        GridPane.setHalignment(random, HPos.CENTER);
        GridPane.setValignment(random, VPos.CENTER);
        gridForButtons.setHgap(5);

        // add buttons to the grid for buttons
        gridForButtons.add(collapse, 0, 0);
        gridForButtons.add(search, 1, 0);
        gridForButtons.add(random, 2, 0);
        /*
         * End of creating action button area
         ****************************************************/


        /****************************************************
         * Create input area layout
         */
        // create grid layout for title field and input buttons, then set column widths
        grid = new GridPane();
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(60);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(20);
        grid.getColumnConstraints().addAll(col3, col4, col5);

        // create and add text field with on action
        title = new TextField();
        title.setPromptText("Title");
        title.setOnAction(e -> input(1));
        grid.add(title, 0, 0);

        // create buttons and their actions, then add to the grid
        Button add = new Button("Add");
        add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // have button take up entire cell
        add.setOnAction(e -> input(1));
        grid.add(add, 1,0);
        Button look = new Button("Look");
        look.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // have button take up entire cell
        look.setOnAction(e -> input(0));
        grid.add(look, 2,0);
        collapseInput();    // start collapsed


        // create text area for item notes and add to the grid
        notes = new TextArea();
        notes.setPromptText("Notes...");
        notes.setWrapText(true);
        notes.setMaxHeight(100);
        notes.setMinHeight(100);
        notes.setOnKeyPressed(e -> {        // if enter key is pressed, submit the item as seen before
            if (e.getCode() == KeyCode.ENTER)  {
                input(1);
            }
        });
        grid.add(notes, 0, 1, 3, 1);

        // set the grid padding and spacing
        grid.setPadding(new Insets(10, 10, 0, 10));
        grid.setHgap(5);
        grid.setVgap(5);
        /*
         * End of input area
         ***************************************************/

        /****************************************************
         * Create search area layout
         */
        searchVBox = new VBox();
        searchVBox.setPadding(new Insets(10, 10, 0, 10));
        searchField = new TextField();
        searchField.setId("search-field");      // give textfield css id for styling
        searchField.setPromptText("Search entries...");
        searchField.setEditable(true);
        searchField.setOnKeyTyped(e -> showMatches(searchField.getText()));     // refresh list on key presses
        collapseSearch();   // start collapsed

        // create initial observable list for the search results and place in the ListView
        showMatches("");

        searchVBox.getChildren().addAll(searchField, searchResults);
        /*
         * End of search area
         ***************************************************/

        /****************************************************
         * Create random area layout
         */
        randomVBox = new VBox();
        randomVBox.setAlignment(Pos.CENTER);
        randomVBox.setPadding(new Insets(10, 10, 0, 10));
        randomOutput = new Label("Click for a random list item!");
        // still don't really know what a lambda is... but ok
        randomOutput.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                random();
            }
        });
        randomVBox.getChildren().add(randomOutput);
        collapseRandom();
        /*
         * End of random area
         ***************************************************/


        /****************************************************
         * Create list area for all the database entries to be displayed
         */
        // create tabbed pane
        TabPane tp = new TabPane();
        tp.setPadding(new Insets(10, 10, 10, 10));
        tp.setPrefHeight(600);

        // create the tabs
        Tab movies1 = new Tab("Movies");
        movies1.setClosable(false);
        Tab books2 = new Tab("Books");
        books2.setClosable(false);
        Tab tvShows3 = new Tab("TV Shows");
        tvShows3.setClosable(false);
        Tab music4 = new Tab("Music");
        music4.setClosable(false);
        Tab other5 = new Tab("Other");
        other5.setClosable(false);

        // add the tabs to the tabbed pane
        tp.getTabs().addAll(movies1, books2, tvShows3, music4, other5);

        // create add ListViews to the Tabs
        movieList = new ListView<>();    // movies
        movies1.setContent(movieList);
        bookList = new ListView<>();     // books
        books2.setContent(bookList);
        tvList = new ListView<>();     // tv shows
        tvShows3.setContent(tvList);
        musicList = new ListView<>();     // music
        music4.setContent(musicList);
        otherList = new ListView<>();     // other
        other5.setContent(otherList);

        // set the currently selected ListView
        selectedList = movieList;

        // create action listener that sets the select ListView when tabs are changed
        tp.getSelectionModel().selectedItemProperty().addListener(
                // lambda (ov, t, t1) repalces "new ChangeListener<Tab>()"
                (ov, t, t1) -> {

                    // set the new selected list index (for database purposes)
                    groupIndex = tp.getSelectionModel().getSelectedIndex();
                    switch(groupIndex){
                        case 0:  selectedList = movieList;
                            break;
                        case 1: selectedList = bookList;
                            break;
                        case 2: selectedList = tvList;
                            break;
                        case 3: selectedList = musicList;
                            break;
                        case 4: selectedList = otherList;
                            break;
                    }
                }
        );// End of change listener

        // create right-click action listener for ListViews that call the method that creates context menus and shows them
        movieList.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showContextMenu());
        bookList.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showContextMenu());
        tvList.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showContextMenu());
        musicList.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showContextMenu());
        otherList.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showContextMenu());
        /*
         * End of creating list area
         ****************************************************/

        // add all the components to the main layout
        mainVert.getChildren().addAll(gridForButtons, randomVBox, searchVBox, grid, tp);

        // create the scene and add the stylesheet
        Scene scene = new Scene(mainVert);
        scene.getStylesheets().add(css);

        // fill the lists from the database
        loadLists();

        // set the scene
        myStage.setScene(scene);

    }// End of method createMainScene

    /**********************************************
     * method to load lists
     */
    private void loadLists(){
        // clear all the lists
        movieList.getItems().clear();
        bookList.getItems().clear();
        tvList.getItems().clear();
        musicList.getItems().clear();
        otherList.getItems().clear();

        // declare variables that will be used in the following loops
        String cmd;
        ResultSet r;
        String s;
        try {
            // loop through all the groups
            for(int i = 0; i < 5; i++){

                // get all the items from the group, and order them by if they were seen, then by alphabetical order
                cmd = "SELECT title, wasSeen FROM items WHERE groupIndex=" + i + " ORDER BY wasseen ASC, title";
                r = DBManager.ExecuteQuery(cmd);

                // go through all the items
                while(r.next()){

                    // get the item's title
                    s = r.getString(1);

                    // check if the item was seen / heard before
                    if(!r.getBoolean(2)){
                        // need to do something with the item so the user knows it hasn't been seen

                    }

                    // depending on the group, add the item to the correct list that will be displayed in the TabPane
                    switch(i){
                        case 0: movieList.getItems().add(s);
                            break;
                        case 1: bookList.getItems().add(s);
                            break;
                        case 2: tvList.getItems().add(s);
                            break;
                        case 3: musicList.getItems().add(s);
                            break;
                        case 4: otherList.getItems().add(s);
                            break;
                    }// end of switch
                }// end of while loop
            }// end of for loop
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// End of loadLists

    /**********************************************
     * method to input a new list item (seen before or not)
     */
    private void input(int wasSeen){

        try{
            String inTitle = title.getText();
            String inNotes = notes.getText();

            // handle ' marks in Strings that could cause SQL errors (for now it will just throw an error)

            // check if title is blank or already exists
            if(inTitle.equals("")){
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Title Error!");
                al.setContentText("Title cannot be blank!");
                al.showAndWait();
            }
            else if(entryExists(inTitle))
            {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Duplicate Error!");
                al.setContentText("Your entry already exists!");
                al.showAndWait();
            }
            else{
                String cmd = "INSERT INTO dbo.ITEMS (GroupIndex, Title, Notes, wasSeen) VALUES (" + groupIndex + ", '" + inTitle.replace("'", "''") + "', '" + inNotes.replace("'", "''") + "', " + wasSeen + ")";
                DBManager.Execute(cmd);
                Alert al = new Alert(Alert.AlertType.NONE);
                al.setTitle("Success!");
                al.setContentText("Your entry was added!");
                al.getButtonTypes().addAll(ButtonType.OK);
                al.show();
                title.clear();
                notes.clear();
                loadLists();
            }
        } catch(SQLException | ClassNotFoundException e1){
            e1.printStackTrace();
            Alert al = new Alert(Alert.AlertType.NONE);
            al.setTitle("Error!");
            al.setContentText("Your entry could not be entered into the database! Try checking if your entry includes any apostrophes. They throw an SQL error that will be handled in future versions of DarkArchive.");
            al.getButtonTypes().addAll(ButtonType.OK);
            al.show();
        }
    }// End of method input

    /**********************************************
     * method to remove item from database
     */
    private void delete(){

        // check if the user is sure
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete?");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){

            // make the deletion
            String sTitle = selectedList.getSelectionModel().getSelectedItem();

            String cmd = "DELETE FROM items WHERE (title='" + sTitle.replace("'", "''") + "' AND groupIndex=" + groupIndex + ")";
            try {
                DBManager.Execute(cmd);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("error in delete method with sql");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("error in delete method with class not found");
            }

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Item Deleted");
            al.setContentText("Your item was deleted!");
            al.show();

            loadLists();

        } else {
            // cancel the deletion
            alert.close();
        }
    }

    /**********************************************
     * method to update an item in the database
     */
    private void update(String t, String n, int g, int b,String oldTitle, int oldGroupIndex){
        try {
            System.out.println("The item is being updated...");
            String cmd = "UPDATE items SET title='" + t.replace("'", "''") + "', notes='" + n.replace("'", "''") + "', groupindex=" + g + ", wasSeen=" + b + " WHERE (title='" + oldTitle.replace("'", "''") + "' AND groupIndex=" + oldGroupIndex + ")";
            DBManager.Execute(cmd);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("The item was updated!");
            alert.show();

        } catch (SQLException e) {
            System.out.println("sql error in update method.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("class not found error in update method.");
        }
    }

    /**********************************************
     * method to edit an item in the database
     * (creates and displays a new window)
     */
    private void edit(){
        try{

            // these old values have to be stored so that if the user
            // keeps the edit screen open while selecting a different ListView item
            // the edit screen can still find and update the correct item in the database
            String oldTitle = selectedList.getSelectionModel().getSelectedItem();
            int oldGroupIndex = groupIndex;

            // get item's info
            ResultSet r = getItemInfo(oldTitle, oldGroupIndex);
            r.next();

            // create new window
            Stage edit = new Stage();
            edit.setTitle("Edit");
            edit.setHeight(400);
            edit.setWidth(400);
            edit.setResizable(false);

            VBox vertEdit = new VBox();
            vertEdit.setAlignment(Pos.CENTER);
            vertEdit.setSpacing(5);

            Label editHeader = new Label("Edit Item");
            editHeader.setFont(new Font(40));
            Label editTitle = new Label("Title:");
            TextField editTextTitle = new TextField(r.getString(2));    // initialize with title info
            editTextTitle.setMaxWidth(300);
            Label editNotes = new Label("Notes: ");
            TextArea editTextNotes = new TextArea(r.getString(3));    // initialize with notes info
            editTextNotes.setMaxWidth(300);
            editTextNotes.setMaxHeight(100);
            editTextNotes.setWrapText(true);
            Label editGroup = new Label("Group: ");

            // create choicebox for groups
            ChoiceBox<String> cb = new ChoiceBox<>();
            cb.getItems().addAll("Movies", "Books", "TV Shows", "Music", "Other");
            cb.setValue(groupIndexToString(groupIndex));

            Label editSeen = new Label("Seen:");

            // create radio button group
            ToggleGroup group = new ToggleGroup();
            RadioButton btnTrue = new RadioButton("True");
            btnTrue.setToggleGroup(group);
            btnTrue.setSelected(true);
            RadioButton btnFalse = new RadioButton("False");
            btnFalse.setToggleGroup(group);

            HBox btnBox = new HBox();
            btnBox.setAlignment(Pos.CENTER);
            btnBox.setSpacing(10);
            btnBox.getChildren().addAll(editSeen, btnTrue, btnFalse);

            HBox hbox = new HBox();
            Button btnUpdate = new Button("Update");
            Button btnCancel = new Button("Cancel");
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(10);
            hbox.getChildren().addAll(btnUpdate, btnCancel);

            // set which button in the group is selected
            if(r.getBoolean(4))
            {
                btnTrue.setSelected(true);
            }
            else{
                btnFalse.setSelected(true);
            }

            // add actions to the buttons
            btnCancel.setOnAction(e -> edit.close());       // close the window
            btnUpdate.setOnAction(e -> {                    // get update info and update the item

                // get info for the update
                int iNewGroupIndex = groupStringToIndex(cb.getValue());
                if(iNewGroupIndex == -1){
                    throw new IllegalArgumentException();
                }

                int iBit = 1;
                if(btnFalse.isSelected()){
                    iBit = 0;
                }
                else if(btnTrue.isSelected()){
                    iBit = 1;
                }

                //call the update method
                update(editTextTitle.getText(), editTextNotes.getText(), iNewGroupIndex, iBit, oldTitle, oldGroupIndex);

                // reload the ListViews
                loadLists();

                // close the window
                edit.close();

            });

            vertEdit.getChildren().addAll(editHeader, editTitle, editTextTitle, editNotes, editTextNotes, editGroup, cb, btnBox, hbox);

            Scene editScene = new Scene(vertEdit);

            // set the stylesheet for the scene
            editScene.getStylesheets().add(css);

            edit.setScene(editScene);
            edit.show();

        }
        catch (SQLException e){
            System.out.println("SQL exception thrown by edit method");
            e.printStackTrace();
        }
        catch(IllegalArgumentException e){
            System.out.println("The value from the edit choicebox was invalid");
            e.printStackTrace();
        }
    }// End of edit method

    /**********************************************
     * method to display right-click menus on the item lists
     */
    private void showContextMenu()
    {
        // create menu
        ContextMenu context = new ContextMenu();
        MenuItem view = new MenuItem("View");
        view.setOnAction(e -> {
            try {
                view();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        MenuItem edit = new MenuItem("Edit");
        edit.setOnAction(e -> edit());
        SeparatorMenuItem sep = new SeparatorMenuItem();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(e -> delete());
        context.getItems().addAll(view, edit, sep, delete);

        // view the menu on the currently displayed list
        selectedList.setContextMenu(context);
    }// End of showContextMenu method

    /**********************************************
     * method to return a random item to the user
     */
    private void random(){
        try {
            // return one random row from the database's item table
            String cmd = "SELECT TOP 1 title, groupIndex FROM items ORDER BY NEWID()";
            ResultSet r = DBManager.ExecuteQuery(cmd);

            // move the pointer to the lone row
            r.next();

            // display the random data to the user
            randomOutput.setText(groupIndexToString(r.getInt(2)) + " - " + r.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// End of method random

    /**********************************************
     * method to view an items details
     * (creates and shows a new window)
     */
    private void view() throws SQLException {

        Stage view = new Stage();
        view.setTitle("View");
        view.setHeight(400);
        view.setWidth(400);
        view.setResizable(false);

        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.setSpacing(10);

        // get item's info
        ResultSet r = getItemInfo(selectedList.getSelectionModel().getSelectedItem(), groupIndex);

        // move the ResultSet pointer to the first (and only row)
        r.next();

        Label stageTitle = new Label("Item Info");
        stageTitle.setFont(new Font(40));
        Label itemID = new Label("Database ID: " + r.getInt(1));
        Label itemTitle = new Label("Title: " + r.getString(2));
        Label itemNotes = new Label("Notes: " + r.getString(3));
//        itemNotes.getStyleClass().add("maxWidthLabel");
        itemNotes.setMaxWidth(200);
        itemNotes.setWrapText(true);
        Label itemSeen = new Label("Seen: " + r.getBoolean(4));
        Label itemIndex = new Label("Group: " + groupIndexToString(r.getInt(5)));   // convert group index to group name
        v.getChildren().addAll(stageTitle, itemID, itemTitle, itemNotes, itemSeen, itemIndex);
        Scene viewview = new Scene(v);

        // set the stylesheet for the scene
        viewview.getStylesheets().add(css);

        view.setScene(viewview);
        view.show();

    }// End of method view

    /**********************************************
     * method to convert group index to group name
     */
    private String groupIndexToString(int i){
        switch(i){
            case 0: return "Movies";
            case 1: return "Books";
            case 2: return "TV Shows";
            case 3: return "Music";
            case 4: return "Other";
        }
        return "Invalid group index passed to the groupIndexToString() method.";
    }

    /**********************************************
     * method to convert group name to group index
     */
    private int groupStringToIndex(String s){
        switch(s){
            case "Movies": return 0;
            case "Books": return 1;
            case "TV Shows": return 2;
            case "Music": return 3;
            case "Other": return 4;
        }
        return -1;
    }

    /**********************************************
     * method to get item info based on title and group
     */
    private ResultSet getItemInfo(String psTitle, int piGroupIndex){
        String cmd = "SELECT id, title, notes, wasSeen, groupIndex FROM items WHERE (title='" + psTitle.replace("'", "''") + "' AND groupindex=" + piGroupIndex + ")";
        ResultSet r = null;
        try {
            r = DBManager.ExecuteQuery(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /**********************************************
     * method to check if an entry already exists
     */
    private boolean entryExists(String title){

        // if ResultSet isn't empty return true because the entry exists
        try {

            String cmd = "SELECT title FROM items WHERE groupIndex=" + groupIndex;
            ResultSet r = DBManager.ExecuteQuery(cmd);

            String sTemp;
            while(r.next()){
                sTemp = r.getString(1).toLowerCase();
                if(sTemp.equals(title.toLowerCase()))
                    return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error with entryExists method");
        }
        catch (Exception e){
            Alert al = new Alert(Alert.AlertType.NONE);
            al.setTitle("Error!");
            al.setContentText("Your entry could not be entered into the database! Try checking if your entry includes any apostrophes. They throw an SQL error that will be handled in future versions of DarkArchive.");
            al.getButtonTypes().addAll(ButtonType.OK);
            al.show();
            e.printStackTrace();
        }
        return false;
    }

    /**********************************************
     * method to collapse and expand the input area
     */
    private void collapseInput(){
        if(showInput){
            grid.setVisible(false);
            grid.setManaged(false);
            showInput = false;
        }
        else{
            grid.setVisible(true);
            grid.setManaged(true);
            showInput = true;
        }
    }// End of method collapseInput (is called when screen is initially created)

    /**********************************************
     * method to collapse and expand the search area
     */
    private void collapseSearch(){
        if(showSearch){
            searchVBox.setVisible(false);
            searchVBox.setManaged(false);
            showSearch = false;
        }
        else{
            searchVBox.setVisible(true);
            searchVBox.setManaged(true);
            showSearch = true;
        }
    }// End of method collapseSearch

    /**********************************************
     * method to collapse and expand the random area
     */
    private void collapseRandom(){
        if(showRandom){
            randomVBox.setVisible(false);
            randomVBox.setManaged(false);
            showRandom = false;
        }
        else{
            randomVBox.setVisible(true);
            randomVBox.setManaged(true);
            showRandom = true;
        }
    }// End of method collapseRandom

    /**********************************************
     * method to show search results in seach field's drop down
     */
    private void showMatches(String s){
        try {
            List<String> list = new ArrayList<>();

            // get textfield text, if it's 1 or more characters return search results
            if(s.length() > 0){

                // get all entries that include the text
                ResultSet r = DBManager.ExecuteQuery("select title, groupIndex from items where title LIKE '%" + s + "%'");

                // for everything that's returned, add it to the ListView
                while(r.next()){
                    list.add( r.getString(1) + " - " + groupIndexToString(r.getInt(2)) );
                }
            }

            searchResults.setItems(FXCollections.observableList(list));  // convert list to observablelist and place in search results ListView

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// End of method showMatches

}// End of class Main
