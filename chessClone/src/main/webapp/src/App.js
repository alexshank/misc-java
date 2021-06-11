import React from 'react';
import './App.css';
import Board from './components/board';
import ColumnLabels from './components/column-labels';
import MoveList from './components/move-list';
import RowLabels from './components/row-labels';

class App extends React.Component {
    // store the layout of the board, whose turn it is, and game status
    constructor(props){
        super(props);
        this.state = null;
    }

    // create starting board on first load
    componentDidMount(){
      const url = 'http://localhost:8080/chess/start';
      const that = this;
      fetch(url)
        .then(response => response.json())
        .then(json => that.setState(json))
    }

    // handle when a square on the board is clicked
    handleSquareClick = (squareCoordinates) => {
      if(squareCoordinates.coordinates === this.state.destinationSquare?.coordinates){
        this.setState({destinationSquare: null})
      }else if(squareCoordinates.coordinates === this.state.sourceSquare?.coordinates){
        this.setState({
          destinationSquare: null,
          sourceSquare: null
        });
      }else if(this.state.sourceSquare != null){
        this.setState({destinationSquare: squareCoordinates});
      }else{
        this.setState({sourceSquare: squareCoordinates});
      }
    }

    // make API request to submit a move
    // TODO pass board, player, and source/dest to API for validation
    handleApiButtonClick = () => {
      console.log('Posting request...');
      console.log(this.state)
      const url = 'http://localhost:8080/chess/move';
      fetch(url, {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.state)
      })
      .then(response => response.json())
      .then(data => this.setState(data));
    }

    render(){
      return (
        <div className="App">
          <div className='board-wrapper'>

            <div className='horizontal-wrapper'>
              {/* row labels (numbers) */}
              <RowLabels />

              {/* visual of the board (holds all state) */}
              {
              this.state !== null && 
              <Board board={this.state.board.board}
                  sourceSquare={this.state.sourceSquare}
                  destinationSquare={this.state.destinationSquare}
                  squareHandler={(coordinates) => this.handleSquareClick(coordinates)}
                  />
              }
            </div>

            {/* column labels (letters) */}
            <ColumnLabels />
          </div>

          {/* list of moves and game history */}
          <div className="move-list">
            {this.state !== null && 
            <MoveList apiHandler={() => this.handleApiButtonClick()}
              destinationSet={this.state.destinationSquare !== null}
              stillPlaying={this.state.stillPlaying === true}/>
            }
          </div>
          
        </div>
      );
    }
}

export default App;
