import './column-labels.css'

function ColumnLabels(props){
    return (
        <div className='wrapper'>
            <div className='left-gutter'></div>

            <div className='letter-labels'>
                <div className='letter-label'>A</div>
                <div className='letter-label'>B</div>
                <div className='letter-label'>C</div>
                <div className='letter-label'>D</div>
                <div className='letter-label'>E</div>
                <div className='letter-label'>F</div>
                <div className='letter-label'>G</div>
                <div className='letter-label'>H</div>
            </div>
        </div>
    );
}

export default ColumnLabels;