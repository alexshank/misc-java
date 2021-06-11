package com.shank.chessClone.model;

public class BoardSquare {
    private int row;
    private int column;
    private String coordinates;

    public BoardSquare(int row, int column){
        this.row = row;
        this.column = column;
        this.coordinates = numberToLetter(column) + String.valueOf(row);
    }

    public BoardSquare(String coordinates){
        this.row = (int) coordinates.charAt(0);
        this.column = letterToNumber(coordinates.charAt(1));
        this.coordinates = coordinates;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    private int letterToNumber(char columnLetter){
        int temp = (int) (columnLetter);
        return temp - 97;
    }

    private char numberToLetter(int columnNumber){
        char temp = (char) (columnNumber + 97);
        return temp;
    }
}
