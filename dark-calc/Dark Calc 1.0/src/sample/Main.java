package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application {

    CalcManager cm;
    TextField textField;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Dark Calc");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Calculator.png")));
        primaryStage.setResizable(false);   // simple program so don't change window size
        primaryStage.show();

        // create the manager object
        cm = new CalcManager();

        // create grid layout
        GridPane gridPane = new GridPane();

        // set column widths (as percentages)
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(25);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(col0, col1, col2, col3);

        // set the row heights
        RowConstraints row0 = new RowConstraints(70); // first row is 70px
        for(int i = 0; i < 6; i++)
        {
            gridPane.getRowConstraints().add(row0);     // add 6 row constraints
        }

        // create text field and add to the grid pane
        textField = new TextField();
        textField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(textField, 0, 0, 4, 1);
        textField.setEditable(false);   // only add stuff through the buttons (could have been a label)
        textField.setAlignment(Pos.CENTER_RIGHT);   // have text appear on right side

        // set the text field's functionality using a lambda, which is basically an anonymous function
        // don't let the text field have more than 10 characters
        textField.setOnKeyTyped(e -> {
            if(textField.getText().length() == 10) {
                e.consume();
            }
        });

        /************************************
         * Create the buttons and add to grid pane
         */

        // function buttons
        Button btnPlus = new Button("+");   // create object
        gridPane.add(btnPlus, 0, 1);    // place at col 1, row 2
        btnPlus.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);     // fill entire grid pane cell
        btnPlus.setOnAction(e -> prep(btnPlus.getText()));      // set button functionality (get calculator ready for calculation)

        Button btnMinus = new Button("−");
        gridPane.add(btnMinus, 1, 1);
        btnMinus.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMinus.setOnAction(e -> prep(btnMinus.getText()));

        Button btnMultiply = new Button("×");
        gridPane.add(btnMultiply, 2, 1);
        btnMultiply.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMultiply.setOnAction(e -> prep(btnMultiply.getText()));

        Button btnDivide = new Button("÷");
        gridPane.add(btnDivide, 3, 1);
        btnDivide.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnDivide.setOnAction(e -> prep(btnDivide.getText()));

        Button btnRaise = new Button("^");
        gridPane.add(btnRaise, 3, 2);
        btnRaise.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnRaise.setOnAction(e -> prep(btnRaise.getText()));

        Button btnSQRT = new Button("√");
        gridPane.add(btnSQRT, 3, 3);
        btnSQRT.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSQRT.setOnAction(e -> prep(btnSQRT.getText()));

        Button btnClear = new Button("AC");
        gridPane.add(btnClear, 3, 4);
        btnClear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnClear.setId("clear");    // set ID for CSS styling
        btnClear.setOnAction(e -> allClear());  // clear the manager's PIV's

        Button btnEquals = new Button("=");
        gridPane.add(btnEquals, 3, 5);
        btnEquals.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnEquals.setId("equals");    // set ID for CSS styling
        btnEquals.setOnAction(e -> calculate());  // tell the manager to make the calculation
        // end of function buttons

        // number buttons
        Button btn7 = new Button("7");
        gridPane.add(btn7, 0, 2);
        btn7.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn7.setOnAction(e -> addInput(btn7));

        Button btn4 = new Button("4");
        gridPane.add(btn4, 0, 3);
        btn4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn4.setOnAction(e -> addInput(btn4));

        Button btn1 = new Button("1");
        gridPane.add(btn1, 0, 4);
        btn1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn1.setOnAction(e -> addInput(btn1));

        Button btnDot = new Button(".");
        gridPane.add(btnDot, 0, 5);
        btnDot.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnDot.setOnAction(e -> addInput(btnDot));

        Button btn0 = new Button("0");
        gridPane.add(btn0, 1, 5, 2, 1);
        btn0.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn0.setOnAction(e -> addInput(btn0));

        Button btn8 = new Button("8");
        gridPane.add(btn8, 1, 2);
        btn8.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn8.setOnAction(e -> addInput(btn8));

        Button btn5 = new Button("5");
        gridPane.add(btn5, 1, 3);
        btn5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn5.setOnAction(e -> addInput(btn5));

        Button btn2 = new Button("2");
        gridPane.add(btn2, 1, 4);
        btn2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn2.setOnAction(e -> addInput(btn2));

        Button btn9 = new Button("9");
        gridPane.add(btn9, 2, 2);
        btn9.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn9.setOnAction(e -> addInput(btn9));

        Button btn6 = new Button("6");
        gridPane.add(btn6, 2, 3);
        btn6.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn6.setOnAction(e -> addInput(btn6));

        Button btn3 = new Button("3");
        gridPane.add(btn3, 2, 4);
        btn3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn3.setOnAction(e -> addInput(btn3));
        // end of number buttons

        /*
         * End of creating buttons
         *********************************************/

        // create a scene with the gridpane as the layout
        Scene scene = new Scene(gridPane, 300, 420);

        // add a style sheet to change the GUI look
        String css = this.getClass().getResource("stylish.css").toExternalForm(); // weird, but gets the stylesheet's filepath
        scene.getStylesheets().add(css);

        // set scene in stage
        primaryStage.setScene(scene);

        // show the stage
        primaryStage.show();
    }

    // method to add button string to the text field
    private void addInput(Button btn){

        // if initial value is set replace it
        if(textField.getText().equals("0") && btn.getText() != "."){
            textField.setText(btn.getText());
        }
        else if(cm.getJustCalculated() == true)
        {
            textField.setText(btn.getText());
            cm.setJustCalculated(false);
        }
        else if(textField.getText().length() < 10){
            textField.setText(textField.getText() + btn.getText());
        }
    }

    // method that is called when function buttons are clicked
    public void prep(String op){

        // function is pressed and calculation must be finished
        if(cm.getIsReady() == true)
        {

            calculate();
            cm.setOperator(op);
            cm.setStoredValue(Double.parseDouble(textField.getText()));
        }
        else if(cm.getIsReady() == false)
        {
            cm.setStoredValue(Double.parseDouble(textField.getText()));
            cm.setOperator(op);
            textField.setText("0");
            cm.setIsReady(true);
        }
    }

    // method that is called when equals button is clicked (or function button is used to continue calculation)
    public void calculate(){
        double input = Double.parseDouble(textField.getText());
        double answer = cm.delegate(input);
        textField.setText(Double.toString(answer));
        cm.setIsReady(false);
        cm.setJustCalculated(true);
    }

    // method that is called when the AC button is clicked
    public void allClear(){
        cm.setStoredValue(0);
        cm.setOperator("");
        cm.setIsReady(false);
        textField.setText("0");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
