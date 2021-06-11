package com.shank.chessClone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shank.chessClone.model.BoardSquare;
import com.shank.chessClone.model.ChessBoard;
import com.shank.chessClone.model.ChessGame;
import com.shank.chessClone.model.ChessPiece;
import com.shank.chessClone.service.ChessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("chess")
public class ChessController {

    private final ChessService service;

    @Autowired
    public ChessController(ChessService service){
        this.service = service;
    }

    @GetMapping("start")
    @CrossOrigin
    public ResponseEntity<ChessGame> start() {
        ChessGame cg = new ChessGame();
        return ResponseEntity.ok(cg);
    }

    @PostMapping("move")
    @CrossOrigin
    public ResponseEntity<ChessGame> start(@RequestBody ChessGame chessGame) {
        ChessBoard result = service.movePiece(chessGame.getBoard(),
                                      chessGame.getSourceSquare(),
                                      chessGame.getDestinationSquare());
        chessGame.setBoard(result);
        chessGame.setSourceSquare(null);
        chessGame.setDestinationSquare(null);
        return ResponseEntity.ok(chessGame);
    }

}
