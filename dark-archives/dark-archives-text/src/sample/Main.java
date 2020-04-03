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
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {

    // password field, main stage, and css path
    private PasswordField pwf;
    private Stage myStage;
    private String cssFilePath;

    // JavaFX objects that have their visibility toggled
    private GridPane gridForInputArea;
    private VBox searchVBox;
    private VBox randomVBox;

    // lists, selected group index, and group names
    private ArrayList<ListView<Entry>> listViewList = new ArrayList<>();
    private ListView<Entry> selectedList;
    private int currentGroupIndex = 0;
    private String[] groupNames = {"Movies", "Books", "TV Shows", "Music", "Other"};

    // input fields and labels
    private TextField titleInputField;
    private TextArea notesInputField;
    private Label randomOutputLabel;
    private TextField searchInputField;
    private ListView<String> searchResultsList = new ListView<>();

    // control which of the three action button layouts (if any) are displayed above the lists
    private boolean showInput = true;
    private boolean showSearch = true;
    private boolean showRandom = true;

    // objects for the GUI that are created in one method and used in another
    private GridPane gridForActionButtons;
    private TabPane tabPaneForGroupLists;

    // variable that manages all the program's data
    private DataManager dm;


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

        // set window titleInputField and image
        primaryStage.setTitle("Dark Archive");
        primaryStage.getIcons().add(image);

        // don't let the window take up too much space
        primaryStage.setMaxHeight(800);
        primaryStage.setMaxWidth(600);

        // create login layout
        VBox vert = new VBox();

        // create imageview
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(175);
        imageView.setPreserveRatio(true);

        // create label
        Label titleLabel = new Label("Dark Archive");
        titleLabel.setFont(new Font(30));    // set font size

        // create password field
        pwf = new PasswordField();
        pwf.setMaxWidth(200);   // don't let it get too big
        pwf.setOnAction(e -> showMainScene(pwf.getText())); // when enter is pressed, show main scene

        // add components to the layout
        vert.getChildren().addAll(imageView, titleLabel, pwf);

        // center the layout
        vert.setAlignment(Pos.CENTER);

        // create a new Scene with the layout
        Scene login = new Scene(vert);

        // set the stylesheet for the program
        cssFilePath = this.getClass().getResource("stylish.css").toExternalForm(); // weird, but gets the stylesheet's filepath
        login.getStylesheets().add(cssFilePath);

        // set the Scene and show it
        primaryStage.setScene(login);
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
        primaryStage.show();

        // create DataManager and load data
        dm = new DataManager();
        dm.loadData();
        dm.indexLists();

    }// End of start method


    /**********************************************
     * method to validate user password
     */
    private void showMainScene(String in){
        if(in.equals("coastal")){
            createMainScene();
        }else{
            showAlert(Alert.AlertType.ERROR,"Login Failure","Your password didn't work.");
        }
        pwf.clear();
    }


    /**********************************************
     * method to create main screen
     */
    private void createMainScene(){

        // create layout and center the components at the top
        VBox mainVert = new VBox();
        mainVert.setAlignment(Pos.TOP_CENTER);

        // call methods for creating different parts of the main scene
        createMainActionButtons();
        createInputArea();
        createSearchArea();
        createRandomArea();
        createListArea();

        // start all the action areas hidden
        showInput = toggleVisibility(gridForInputArea, showInput);
        showSearch = toggleVisibility(searchVBox, showSearch);
        showRandom = toggleVisibility(randomVBox, showRandom);

        // create menu bar for program
        Menu file = new Menu("File");
        MenuItem saveDestination = new MenuItem("Save Destination");
        file.getItems().add(saveDestination);
        MenuBar menuBar = new MenuBar(file);

        // add all the components to the main layout
        mainVert.getChildren().addAll(menuBar, gridForActionButtons, randomVBox, searchVBox, gridForInputArea, tabPaneForGroupLists);

        // create the scene and add the stylesheet
        Scene scene = new Scene(mainVert);
        scene.getStylesheets().add(cssFilePath);

        // fill the lists from the data manager
        loadLists();

        // set the scene
        myStage.setScene(scene);

    }// End of method createMainScene


    /****************************************************
     * Create button area for main actions
     */
    public void createMainActionButtons(){

        // create the scene's action buttons
        Button collapse = new Button("Input");
        collapse.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        collapse.setOnAction(e -> {
            showInput = toggleVisibility(gridForInputArea, showInput);
        });
        Button search = new Button("Search");
        search.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        search.setOnAction(e -> {
            showSearch = toggleVisibility(searchVBox, showSearch);
        });
        Button random = new Button("Random");
        random.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        random.setOnAction(e -> {
            showRandom = toggleVisibility(randomVBox, showRandom);
        });

        // create grid layout for action buttons
        gridForActionButtons = new GridPane();
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(33.3);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33.3);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33.3);
        gridForActionButtons.getColumnConstraints().addAll(col0, col1, col2);
        gridForActionButtons.setPadding(new Insets(10, 10, 0, 10));   // space the left, right, and top of the 3 buttons
        GridPane.setHalignment(collapse, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(collapse, VPos.CENTER); // To align vertically in the cell
        GridPane.setHalignment(search, HPos.CENTER);
        GridPane.setValignment(search, VPos.CENTER);
        GridPane.setHalignment(random, HPos.CENTER);
        GridPane.setValignment(random, VPos.CENTER);
        gridForActionButtons.setHgap(5);

        // add action buttons to the grid
        gridForActionButtons.add(collapse, 0, 0);
        gridForActionButtons.add(search, 1, 0);
        gridForActionButtons.add(random, 2, 0);

    }// end of createMainActionButtons


    /****************************************************
     * Create input area layout
     */
    public void createInputArea(){

        // create grid layout for titleInputField field and input buttons, then set column widths
        gridForInputArea = new GridPane();
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(60);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(20);
        gridForInputArea.getColumnConstraints().addAll(col3, col4, col5);

        // create and add text field with on action
        titleInputField = new TextField();
        titleInputField.setPromptText("Title");
        titleInputField.setOnAction(e -> input(1));
        gridForInputArea.add(titleInputField, 0, 0);

        // create buttons and their actions, then add to the grid
        Button add = new Button("Add");
        add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // have button take up entire cell
        add.setOnAction(e -> input(1));
        gridForInputArea.add(add, 1,0);
        Button look = new Button("Look");
        look.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // have button take up entire cell
        look.setOnAction(e -> input(0));
        gridForInputArea.add(look, 2,0);

        // create text area for item notesInputField and add to the grid
        notesInputField = new TextArea();
        notesInputField.setPromptText("Notes...");
        notesInputField.setWrapText(true);
        notesInputField.setMaxHeight(100);
        notesInputField.setMinHeight(100);
        notesInputField.setOnKeyPressed(e -> {        // if showMainScene key is pressed, submit the item as seen before
            if (e.getCode() == KeyCode.ENTER)  {
                input(1);
            }
        });
        gridForInputArea.add(notesInputField, 0, 1, 3, 1);

        // set the grid padding and spacing
        gridForInputArea.setPadding(new Insets(10, 10, 0, 10));
        gridForInputArea.setHgap(5);
        gridForInputArea.setVgap(5);

    }// end of createInputArea


    /****************************************************
     * Create search area layout
     */
    public void createSearchArea(){

        searchVBox = new VBox();
        searchVBox.setPadding(new Insets(10, 10, 0, 10));
        searchInputField = new TextField();
        searchInputField.setId("search-field");      // give textfield css id for styling
        searchInputField.setPromptText("Search entries...");
        searchInputField.setEditable(true);
        searchInputField.setOnKeyTyped(e -> showSearchResults(searchInputField.getText()));     // refresh list on key presses

        // create initial observable list for the search results and place in the ListView
        showSearchResults("");
        searchVBox.getChildren().addAll(searchInputField, searchResultsList);

    }


    /****************************************************
     * Create random area layout
     */
    public void createRandomArea(){

        randomVBox = new VBox();
        randomVBox.setAlignment(Pos.CENTER);
        randomVBox.setPadding(new Insets(10, 10, 0, 10));
        randomOutputLabel = new Label("Click for a random list item!");
        randomOutputLabel.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                showRandomEntry();
            }
        });
        randomVBox.getChildren().add(randomOutputLabel);

    }


    /****************************************************
     * Create list area for all the data manager entries to be displayed
     */
    public void createListArea(){

        // create tabbed pane
        tabPaneForGroupLists = new TabPane();
        tabPaneForGroupLists.setPadding(new Insets(10, 10, 10, 10));
        tabPaneForGroupLists.setPrefHeight(600);

        // create group tabs and add them to tab pane
        ArrayList<Tab> tabPaneTabs = new ArrayList<>();
        for(int i = 0; i < 5; i++){

            tabPaneTabs.add(new Tab(groupNames[i]));
            tabPaneTabs.get(i).setClosable(false);
            tabPaneForGroupLists.getTabs().add(tabPaneTabs.get(i));


            /*************
             * working on now
             */
            ListView<Entry> singleList = new ListView<>();
            singleList.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>() {

                @Override
                public ListCell<Entry> call(ListView<Entry> singleList) {
                    return new EntryListViewCell();
                }

            });

            listViewList.add(singleList);



            tabPaneTabs.get(i).setContent(listViewList.get(i));
            listViewList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> addContextMenu());

        }

        // set the currently selected ListView
        selectedList = listViewList.get(0);

        // create action listener that sets the select ListView when tabs are changed
        tabPaneForGroupLists.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
                    // set the new selected list index (for data manager purposes)
                    currentGroupIndex = tabPaneForGroupLists.getSelectionModel().getSelectedIndex();
                    selectedList = listViewList.get(currentGroupIndex);
                }
        );

    }// end of createListArea


    /**********************************************
     * method to load lists
     */
    private void loadLists() {
        for(int i = 0; i < 5; i++){
            listViewList.get(i).getItems().clear();
            for(Entry e : dm.groupList.get(i)){
                listViewList.get(i).getItems().add(e);
            }
        }
    }


    /**********************************************
     * method to input a new list item (seen before or not)
     */
    private void input(int wasSeen){
        String inTitle = titleInputField.getText();
        String inNotes = notesInputField.getText();
        if(inTitle.equals("")){
            showAlert(Alert.AlertType.ERROR,"Title Error!","Title cannot be blank!");
        }else if(dm.entryAlreadyExists(inTitle, currentGroupIndex)){
            showAlert(Alert.AlertType.ERROR,"Duplicate Error!","Your entry already exists!");
        }else{
            dm.addEntry(inTitle, inNotes, wasSeen == 1, currentGroupIndex);
            showAlert(Alert.AlertType.CONFIRMATION,"Success!","Your entry was added!");
            titleInputField.clear();
            notesInputField.clear();
            loadLists();
        }
    }


    /**********************************************
     * method to remove item from data manager
     */
    private void showDeletePrompt(){

        // check if the user is sure
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();

        // if user confirms, delete item from data manager
        if(result.isPresent() && result.get() == ButtonType.OK){
            String sTitle = selectedList.getSelectionModel().getSelectedItem().title;
            dm.deleteEntry(sTitle, currentGroupIndex);
            showAlert(Alert.AlertType.INFORMATION,"Item Deleted","Your item was deleted!");
            loadLists();
        } else{
            alert.close();
        }

    }


    /**********************************************
     * method to show an item in the data manager (creates and displays a new window)
     */
    private void showEditWindow(){

        // keep old values so correct update is always made regardless of user actions
        String oldTitle = selectedList.getSelectionModel().getSelectedItem().title;
        int oldGroupIndex = currentGroupIndex;

        // get item's info
        Entry entry = dm.getEntry(oldTitle, oldGroupIndex);

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
        TextField editTextTitle = new TextField(entry.title);    // initialize with titleInputField info
        editTextTitle.setMaxWidth(300);
        Label editNotes = new Label("Notes: ");
        TextArea editTextNotes = new TextArea(entry.notes);    // initialize with notesInputField info
        editTextNotes.setMaxWidth(300);
        editTextNotes.setMaxHeight(100);
        editTextNotes.setWrapText(true);
        Label editGroup = new Label("Group: ");

        // create choicebox for groups
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.getItems().addAll("Movies", "Books", "TV Shows", "Music", "Other");
        cb.setValue(groupNames[currentGroupIndex]);

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
        if(entry.wasSeen){
            btnTrue.setSelected(true);
        }else{
            btnFalse.setSelected(true);
        }

        // add actions to the buttons
        btnCancel.setOnAction(e -> edit.close());
        btnUpdate.setOnAction(e -> {

            // get group index
            int iNewGroupIndex = 0;
            for(int i = 0; i < 5; i++){
                if(cb.getValue() == groupNames[i]){
                    iNewGroupIndex = i;
                }
            }

            // get if was seen
            int wasSeen = 1;
            if(btnFalse.isSelected()){
                wasSeen = 0;
            }

            // update the entry in the data manager
            dm.update(editTextTitle.getText(), editTextNotes.getText(), iNewGroupIndex, wasSeen==1, oldTitle, oldGroupIndex);
            showAlert(Alert.AlertType.INFORMATION,"Success!","The item was updated!");
            loadLists();
            edit.close();

        });

        // add objects to scene layout and create scene
        vertEdit.getChildren().addAll(editHeader, editTitle, editTextTitle, editNotes, editTextNotes, editGroup, cb, btnBox, hbox);
        Scene editScene = new Scene(vertEdit);

        // set the stylesheet for the scene
        editScene.getStylesheets().add(cssFilePath);
        edit.setScene(editScene);
        edit.show();

    }// End of showEditWindow method


    /**********************************************
     * method to display right-click menus on the item lists
     */
    private void addContextMenu()
    {

        // create menu
        ContextMenu context = new ContextMenu();
        MenuItem view = new MenuItem("View");
        view.setOnAction(e -> showDetailsWindow());
        MenuItem edit = new MenuItem("Edit");
        edit.setOnAction(e -> showEditWindow());
        SeparatorMenuItem sep = new SeparatorMenuItem();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(e -> showDeletePrompt());
        context.getItems().addAll(view, edit, sep, delete);

        // view the menu on the currently displayed list
        selectedList.setContextMenu(context);

    }


    /**********************************************
     * method to return a random item to the user
     */
    private void showRandomEntry(){
        Entry e = dm.getRandom();
        randomOutputLabel.setText(e.title + " - " + groupNames[e.groupIndex]);
    }


    /**********************************************
     * method to show an items details (creates and shows a new window)
     */
    private void showDetailsWindow() {

        // make new stage
        Stage view = new Stage();
        view.setTitle("View");
        view.setHeight(400);
        view.setWidth(400);
        view.setResizable(false);

        // create layout for stage
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.setSpacing(10);

        // get entry
        Entry e = dm.getEntry(selectedList.getSelectionModel().getSelectedItem().title, currentGroupIndex);

        // add entry info to the stage
        Label stageTitle = new Label("Item Info");
        stageTitle.setFont(new Font(40));
        Label itemID = new Label("Database ID: " + e.index);
        Label itemTitle = new Label("Title: " + e.title);
        Label itemNotes = new Label("Notes: " + e.notes);
        itemNotes.setMaxWidth(200);
        itemNotes.setWrapText(true);
        Label itemSeen = new Label("Seen: " + e.wasSeen);
        Label itemIndex = new Label("Group: " + groupNames[e.groupIndex]);   // convert group index to group name
        v.getChildren().addAll(stageTitle, itemID, itemTitle, itemNotes, itemSeen, itemIndex);
        Scene viewview = new Scene(v);

        // set the stylesheet for the scene
        viewview.getStylesheets().add(cssFilePath);

        // set the scene
        view.setScene(viewview);
        view.show();

    }// End of method showDetailsWindow


    /*********************************************
     * method to create alerts for the user
     */
    public void showAlert(Alert.AlertType alert_type, String title, String context_text){
        Alert al = new Alert(alert_type);
        al.setTitle(title);
        al.setContentText(context_text);
        al.show();
    }


    /**********************************************
     * method to collapse and expand the input area
     */
    private boolean toggleVisibility(Pane pane, boolean currentlyVisible){
        if(currentlyVisible){
            pane.setVisible(false);
            pane.setManaged(false);
        }else{
            pane.setVisible(true);
            pane.setManaged(true);
        }
        return !currentlyVisible;
    }


    /**********************************************
     * method to show search results in seach field's drop down
     */
    private void showSearchResults(String s){

        // get textfield text, if it's 2 or more characters return search results
        List<Entry> entryList;
        ArrayList<String> list = new ArrayList<>();
        if(s.length() > 1){
            entryList = dm.getMatchingEntries(s);
            for(Entry e : entryList){
                list.add( e.title + " - " + groupNames[e.groupIndex]);
            }
            searchResultsList.setItems(FXCollections.observableList(list));
        }else{
            searchResultsList.setItems(null);   // clear search results when all input is removed
        }

    }


}// End of class Main
