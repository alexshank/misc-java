package com.shank.chessClone.model;

import java.util.Set;

public class Queen extends ChessPiece {

    public Queen(boolean isWhite) {
        super(isWhite, 'q');
    }

    @Override
    public Set<BoardSquare> getPotentialSquares() {
        return null;
    }
}
