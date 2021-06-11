package com.shank.chessClone.model;

import java.util.Set;

public class Knight extends ChessPiece {

    public Knight(boolean isWhite){
        super(isWhite, 'n');
    }

    @Override
    public Set<BoardSquare> getPotentialSquares() {
        return null;
    }


}
