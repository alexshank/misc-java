package com.shank.chessClone.model;

import java.util.Set;

public class Castle extends ChessPiece {

    public Castle(boolean isWhite){
        super(isWhite, 'c');
    }

    @Override
    public Set<BoardSquare> getPotentialSquares() {
        return null;
    }


}
