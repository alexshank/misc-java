package com.shank.chessClone.model;

public class ChessGame {
    private ChessBoard board;
    private boolean whiteIsUp;
    private boolean stillPlaying;
    private BoardSquare sourceSquare;
    private BoardSquare destinationSquare;

    // no arg constructor for starting game
    public ChessGame(){
        this.board = new ChessBoard();
        this.whiteIsUp = true;
        this.stillPlaying = true;
        this.sourceSquare = null;
        this.destinationSquare = null;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public boolean isWhiteIsUp() {
        return whiteIsUp;
    }

    public void setWhiteIsUp(boolean whiteIsUp) {
        this.whiteIsUp = whiteIsUp;
    }

    public boolean isStillPlaying() {
        return stillPlaying;
    }

    public void setStillPlaying(boolean stillPlaying) {
        this.stillPlaying = stillPlaying;
    }

    public BoardSquare getSourceSquare() {
        return sourceSquare;
    }

    public void setSourceSquare(BoardSquare sourceSquare) {
        this.sourceSquare = sourceSquare;
    }

    public BoardSquare getDestinationSquare() {
        return destinationSquare;
    }

    public void setDestinationSquare(BoardSquare destinationSquare) {
        this.destinationSquare = destinationSquare;
    }
}
