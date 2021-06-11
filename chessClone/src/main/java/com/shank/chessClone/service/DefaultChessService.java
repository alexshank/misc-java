package com.shank.chessClone.service;

import com.shank.chessClone.model.BoardSquare;
import com.shank.chessClone.model.ChessBoard;
import com.shank.chessClone.model.ChessPiece;
import org.springframework.stereotype.Service;

@Service
public class DefaultChessService implements ChessService {
    @Override
    public ChessBoard movePiece(ChessBoard board, BoardSquare source, BoardSquare destination) {
        ChessPiece piece = board.getBoard()[source.getRow()][source.getColumn()];
        board.getBoard()[source.getRow()][source.getColumn()] = null;
        board.getBoard()[destination.getRow()][destination.getColumn()] = piece;
        return board;
    }
}
