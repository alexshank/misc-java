package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // Creating all the variables from the fxml file
    @FXML
    private GridPane myGrid;     // Value injected by FXMLLoader
    @FXML
    private Label myLabel;

    // creating variables for handling computing
    private double val1 = 0;
    private double val2 = Double.MAX_VALUE;
    private String operator = "error";
    private boolean hasOperator = false;
    private boolean justComputed = false;

    /******************************
     * This method is called by the FXMLLoader once all the @FXML variables have been initialized
     * logic is now added to the fxml layout.
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // set the grid's column widths (as percentages)
        ColumnConstraints colRestraint = new ColumnConstraints();
        colRestraint.setPercentWidth(25);
        for(int i = 0; i < 4; i++){
            myGrid.getColumnConstraints().add(colRestraint);
        }
        // set the grid's row heights
        RowConstraints rowConstraint = new RowConstraints(70); // all rows are 70px
        for(int i = 0; i < 6; i++) {
            myGrid.getRowConstraints().add(rowConstraint);     // add 6 row constraints
        }
        // add a style sheet to change the GUI look
        String css = this.getClass().getResource("stylish.css").toExternalForm(); // weird, but gets the stylesheet's filepath
        myGrid.getStylesheets().add(css);
    }

    /************************
     * function functionality
     */
    @FXML
    public void functionBtn(ActionEvent e){
        if(hasOperator){
            val2 = Double.parseDouble(myLabel.getText());
            double ans = compute();
            myLabel.setText(Double.toString(ans));
            val1 = ans;
            val2 = Double.MAX_VALUE;
            operator = ((Button) e.getSource()).getText();
            hasOperator = true;
            justComputed = true;
        }else{
            operator = ((Button) e.getSource()).getText();
            val1 = Double.parseDouble(myLabel.getText());
            myLabel.setText("0");
            hasOperator = true;
        }

    }// end of functionBtn

    /************************
     * clear functionality
     */
    @FXML
    public void clearBtn(){
        val1 = 0;
        val2 = Double.MAX_VALUE;
        operator = "error";
        hasOperator = false;
        myLabel.setText("0");
    }// end of clearBtn

    /************************
     * equals functionality
     */
    @FXML
    public void equalsBtn(){
        if(hasOperator){
            val2 = Double.parseDouble(myLabel.getText());
            double ans = compute();
            myLabel.setText(Double.toString(ans));
            val1 = ans;
            val2 = Double.MAX_VALUE;
            operator = "error";
            hasOperator = false;
            justComputed = true;
        }
        else{
            val1 = Double.parseDouble(myLabel.getText());
        }
    }// end of equalsBtn

    /************************
     * add the clicked number to the display (look how clean this code is)
     */
    @FXML
    public void placeNumber(ActionEvent e){

        // check if the displayed number's getting too big
        if(myLabel.getText().length() == 13)
            return;

        // get number from button
        String num = ((Button) e.getSource()).getText();

        // check if the number's zero or is a previous answer, if so replace
        if(myLabel.getText().equals("0") || justComputed){
            myLabel.setText(num);
            justComputed = false;
        }
        else
            myLabel.setText(myLabel.getText() + num);
    }

    /******************
     * method that computes an answer and returns it as a double
     */
    private double compute(){

        // depending on the operator, make the calculation
        switch(operator){
            case "error": System.out.println("WARNING: There is no operator, but the boolean hasOperator says there is.");
            case "+": return val1 + val2;
            case "-": return val1 - val2;
            case "×": return val1 * val2;
            case "÷": return val1 / val2;
            case "^": return Math.pow(val1, val2);
            case "√":
                if(val1 < 0 && val2 % 2 != 1)
                {
                    System.out.println("A negative number to an even nth root will always be imaginary.");
                    return -Double.MAX_VALUE;
                }else {
                    return Math.pow(val1, 1 / val2);
                }
        }
        return -Double.MAX_VALUE;
    }

}// end of Controller.java
