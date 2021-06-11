package com.shank.chessClone.model;

import java.util.Set;

public class King extends ChessPiece {

    public King(boolean isWhite){
        super(isWhite, 'k');
    }

    @Override
    public Set<BoardSquare> getPotentialSquares() {
        return null;
    }


}
