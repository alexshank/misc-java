import './move-list.css';

function MoveList(props){
    return (<div className='move-list-container'>
            <p>API Response</p>
            <button disabled={!props.stillPlaying || !props.destinationSet} onClick={() => props.apiHandler()}>Move</button>
        </div>);
}

export default MoveList;