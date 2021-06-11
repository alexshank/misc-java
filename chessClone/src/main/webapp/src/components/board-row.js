import BoardSquare from './board-square';
import './board-row.css';



function BoardRow(props){
    const boardRow = props.pieces.map((piece, index) => {
        const willBeWhite = (props.startWhite && index % 2 === 1) || (!props.startWhite && index % 2 === 0);
        return (<BoardSquare handleSquareClick={(coordinates) => props.squareHandler(coordinates)}
                            isWhite={willBeWhite}
                            piece={piece}
                            key={index}
                            row={props.row}
                            column={index}
                            sourceSquare={props.sourceSquare}
                            destinationSquare={props.destinationSquare}/>
            );
        });
    return <div className='board-row'>{boardRow}</div>
}

export default BoardRow;