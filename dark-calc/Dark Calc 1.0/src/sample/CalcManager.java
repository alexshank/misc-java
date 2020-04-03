package sample;

/**
 * Created by Alex on 6/21/2016.
 * Class that handles Dark Calc functionality
 */
public class CalcManager
{

    // private instance variables
    private double storedValue;
    private String operator;
    private boolean isReady;
    private boolean justCalculated;

    // constructor
    public CalcManager()
    {
        storedValue = 0;
        operator = "";
        isReady = false;
        justCalculated = false;
    }

    // method that decides which operation is used
    public double delegate(Double dIn){
        switch(operator){
            case "": throw new IllegalArgumentException("Do nothing.");
            case "+": return add(dIn);
            case "-": return subtract(dIn);
            case "×": return multiply(dIn);
            case "÷": return divide(dIn);
            case "^": return raise(dIn);
            case "√": return nthRoot(dIn);
        }
        throw new IllegalArgumentException("Delegate is fucked up.");
    }

    /*******************************************
     * Operation methods
     */
    public double add(double x){
        return storedValue + x;
    }

    public double subtract(double x) {
        return storedValue - x;
    }

    public double multiply(double x) {
        return storedValue * x;
    }

    public double divide(double x) {
        return storedValue / x;
    }

    public double raise(double x) {
        return Math.pow(storedValue, x);
    }

    public double nthRoot(double x) {
        if(storedValue < 0 && x % 2 != 1)
        {
            throw new IllegalArgumentException("A negative number to an even nth root will always be imaginary.");
        }
        return Math.pow(storedValue, 1 / x);
    }
    /*
     * End of operation methods
     ************************************************/

    /************************************************
     * Setters and getters
     */
    public double getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(double storedValue) {
        this.storedValue = storedValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {
        isReady = ready;
    }

    public boolean getJustCalculated() {
        return justCalculated;
    }

    public void setJustCalculated(boolean justCalculated) {
        this.justCalculated = justCalculated;
    }
    /*
     * End of setters and getters
     ************************************************/

}// end of CalcManager
