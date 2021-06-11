package com.shank.chessClone.model;

import java.util.Set;

public abstract class ChessPiece {
    private boolean isWhite;
    private boolean isInPlay;
    private String shorthand;

    public ChessPiece(boolean isWhite, char pieceChar){
        this.isWhite = isWhite;
        this.isInPlay = true;
        this.shorthand = getPrefix() + String.valueOf(pieceChar);
    }

    /*
    public ChessPiece(boolean isWhite, boolean inPlay, String shorthand, Set<BoardSquare> potentialSquares){
        this.isWhite = isWhite;
        this.isInPlay = inPlay;
        this.shorthand = shorthand;
    }
    */

    protected char getPrefix(){
       return isWhite ? 'w' : 'b';
    }

    public boolean getIsWhite(){
        return this.isWhite;
    }

    public boolean isInPlay() {
        return isInPlay;
    }

    public void setInPlay(boolean inPlay) {
        isInPlay = inPlay;
    }

    public String getShorthand() {
        return shorthand;
    }

    public abstract Set<BoardSquare> getPotentialSquares();
}
