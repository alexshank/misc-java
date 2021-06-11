import BoardRow from './board-row';
import React from 'react';
import './board.css';

/*
 * represents the entire chess game 
 */
function Board(props) {
    // note the flip
    const boardRows = props.board.slice().reverse().map((row, index) => {
        return <BoardRow squareHandler={(coordinates) => props.squareHandler(coordinates)}
            startWhite={index % 2 === 1}
            pieces={row}
            key={index}
            row={7 - index}
            sourceSquare={props.sourceSquare}
            destinationSquare={props.destinationSquare}/>
    });
    return <div className="board">{boardRows}</div>;
}

export default Board;