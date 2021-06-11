package com.shank.chessClone.model;

import java.util.Set;

public class Bishop extends ChessPiece {

    public Bishop(boolean isWhite) {
        super(isWhite, 'b');
    }

    @Override
    public Set<BoardSquare> getPotentialSquares() {
        return null;
    }
}
