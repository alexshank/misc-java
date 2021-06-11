package com.shank.chessClone.infrastructure;

import com.shank.chessClone.model.*;

public class ChessPieceFactory {

    public static ChessPiece newChessPiece(String chessPieceString){
        boolean isWhite = chessPieceString.charAt(0) == 'w';

        switch(chessPieceString.charAt(1)){
            case 'p':
                return new Pawn(isWhite);
            case 'c':
                return new Castle(isWhite);
            case 'b':
                return new Bishop(isWhite);
            case 'n':
                return new Knight(isWhite);
            case 'k':
                return new King(isWhite);
            case 'q':
                return new Queen(isWhite);
            default:
                // handle empty square
                return null;
        }
    }


}
