package com.shank.chessClone.model;

import com.shank.chessClone.infrastructure.ChessPieceFactory;

public class ChessBoard {

    private ChessPiece[][] pieces = new ChessPiece[8][8];

    // create board from strings (used in front-end representation)
    public ChessBoard(String[][] pieces){
        ChessPieceFactory factory = new ChessPieceFactory();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                this.pieces[i][j] = factory.newChessPiece(pieces[i][j]);
            }
        }
    }

    // no arg constructor for starting board
    public ChessBoard(){
        // start with empty board
        for(ChessPiece[] row : pieces){
            for(ChessPiece piece : row){
                piece = null;
            }
        }

        // place pieces in starting positions
        for(int i = 0; i < 8; i++){
            pieces[6][i] = new Pawn(false);
            pieces[1][i] = new Pawn(true);
        }

        pieces[0][0] = new Castle(true);
        pieces[0][7] = new Castle(true);
        pieces[7][0] = new Castle(false);
        pieces[7][7] = new Castle(false);

        pieces[0][1] = new Knight(true);
        pieces[0][6] = new Knight(true);
        pieces[7][1] = new Knight(false);
        pieces[7][6] = new Knight(false);

        pieces[0][2] = new Bishop(true);
        pieces[0][5] = new Bishop(true);
        pieces[7][2] = new Bishop(false);
        pieces[7][5] = new Bishop(false);

        pieces[0][3] = new King(true);
        pieces[7][3] = new King(false);

        pieces[0][4] = new Queen(true);
        pieces[7][4] = new Queen(false);

        this.pieces = pieces;
    }

    public ChessPiece[][] getBoard() {
        return pieces;
    }

    public void setBoard(ChessPiece[][] pieces) {
        this.pieces = pieces;
    }
}
