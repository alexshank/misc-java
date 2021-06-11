package com.shank.chessClone.service;

import com.shank.chessClone.model.BoardSquare;
import com.shank.chessClone.model.ChessBoard;

public interface ChessService {
    ChessBoard movePiece(ChessBoard board, BoardSquare source, BoardSquare destination);
}
