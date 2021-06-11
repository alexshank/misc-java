import './board-square.css';
import {useState} from 'react';

import BlackPawn from '../images/black-pawn.png';
import BlackCastle from '../images/black-castle.png';
import BlackBishop from '../images/black-bishop.png';
import BlackQueen from '../images/black-queen.png';
import BlackKing from '../images/black-king.png';
import BlackKnight from '../images/black-knight.png';

import WhitePawn from '../images/white-pawn.png';
import WhiteCastle from '../images/white-castle.png';
import WhiteBishop from '../images/white-bishop.png';
import WhiteQueen from '../images/white-queen.png';
import WhiteKing from '../images/white-king.png';
import WhiteKnight from '../images/white-knight.png';

const pieceMap = {
    'bp': BlackPawn,
    'bc': BlackCastle,
    'bb': BlackBishop,
    'bq': BlackQueen,
    'bk': BlackKing,
    'bn': BlackKnight,

    'wp': WhitePawn,
    'wc': WhiteCastle,
    'wb': WhiteBishop,
    'wq': WhiteQueen,
    'wk': WhiteKing,
    'wn': WhiteKnight,
}

function BoardSquare(props){
    const [row, setRow] = useState(props.row);
    const [column, setColumn] = useState(props.column);

    // calculate black or white square
    var classColor = 'board-square-white';
    if(props.isWhite === false){
        classColor = 'board-square-black';
    }

    // calculate source or destination square
    if(getCoordinateString(row, column).coordinates === props.sourceSquare?.coordinates){
        classColor = 'board-square-source';
    }else if(getCoordinateString(row, column).coordinates === props.destinationSquare?.coordinates){
        classColor = 'board-square-destination';
    }

    // handle empty square
    if(props.piece === null){
        return <div className={classColor}
            onClick={() => props.handleSquareClick(getCoordinateString(row, column))}></div>
    }else{
        return (
            <div onClick={() => props.handleSquareClick(getCoordinateString(row, column))} className={classColor}
                >
                <img src={pieceMap[props.piece.shorthand]} alt="pawn"/>
            </div>);
    }
}

function getCoordinateString(row, column){
    const rowString = 8 - row;
    const columnString = String.fromCharCode(column + 97)
    return {
        row: row,
        column: column,
        coordinates: columnString + rowString
    };
}


export default BoardSquare;