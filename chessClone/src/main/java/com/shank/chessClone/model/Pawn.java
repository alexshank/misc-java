package com.shank.chessClone.model;

import java.util.Set;

public class Pawn extends ChessPiece {

    public Pawn(boolean isWhite){
        super(isWhite, 'p');
    }

    @Override
    public Set<BoardSquare> getPotentialSquares() {
        return null;
    }


}
