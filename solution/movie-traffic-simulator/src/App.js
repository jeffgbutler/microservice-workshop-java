import React, { Component } from 'react';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      started: false,
      baseURL: '',
      movies: []
    };
    this.updateURL = this.updateURL.bind(this);
  }

  updateURL(event) {
    let newURL = event.target.value;

    this.setState((prevState, props) => {
      return {
        started: prevState.started,
        movies: prevState.movies,
        baseURL: newURL
      }   
    });
  }
  
  start() {
    this.setState((prevState, props) => {
      return {
        started: true,
        movies: prevState.movies,
        baseURL: prevState.baseURL
      }
    });
    this.scheduleUpdate();
  }

  stop() {
    this.setState((prevState, props) => {
      return {
        started: false,
        movies: prevState.movies,
        baseURL: prevState.baseURL
      }
    });
  }

  clearHistory() {
    this.setState((prevState, props) => {
      return {
        started: prevState.started,
        movies: [],
        baseURL: prevState.baseURL
      }
    })
  }
  scheduleUpdate() {
    setTimeout(() => this.getMovie(), 1000);
  }

  addMovie(data) {
    let movies = this.state.movies;

    movies.unshift({
      id: data.id,
      title: data.title,
      runLength: data.runLength,
      releaseDate: data.releaseDate,
      castMembers: data.castMembers,
      awards: data.awards
    });
    movies = movies.slice(0, 5);
    this.setState((prevState, props) => {
      return {
        started: prevState.started,
        movies: movies,
        baseURL: prevState.baseURL
      }   
    })
  }

  getMovie() {
    let movieId = Math.floor(Math.random() * 20) + 1;
    fetch(`http://${this.state.baseURL}/movie/${movieId}`, {method: 'GET'})
    .then((res) => res.json())
    .then((data) => {
      this.addMovie(data);
    })
    .catch((err) => {
      console.log(err);
    })
    .then(() => {
      if (this.state.started) {
        this.scheduleUpdate();
      }
    })    
  }

  render() {
    let movies = this.state.movies;

    return (
      <div className="mainPage">
        <title>Movie Service Traffic Simulator</title>
        <h1>Movie Service Traffic Simulator</h1>
        <form>
          Base URL: <input id="urlBox" type="text" value={this.state.baseURL} onChange={this.updateURL}/>
        </form>
        <br/>
        <div>
          <button id="startButton" onClick={() => this.start()} disabled={this.state.started || this.state.baseURL === ""}>Start</button>
          &nbsp;
          <button id="stopButton" onClick={() => this.stop()} disabled={!this.state.started || this.state.baseURL === ""}>Stop</button>
          &nbsp;
          <button id="clearButton" onClick={() => this.clearHistory()}>Clear History</button>
        </div>

        <h2>Movie History</h2>
        <table border="1" cellSpacing="0" cellPadding="5">
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Release Date</th>
              <th>Run Length</th>
              <th>Cast Members</th>
              <th>Awards</th>
            </tr>
          </thead>
          <tbody>
            {movies.map((movie, index) => {
              return (
                <tr key={index}>
                  <td valign="top">{movie.id}</td>
                  <td valign="top">{movie.title}</td>
                  <td valign="top">{movie.releaseDate}</td>
                  <td valign="top">{movie.runLength}</td>
                  <td valign="top">{movie.castMembers.map((castMember, i) => {
                    return (
                      <div>{castMember.role} : {castMember.actor}</div>
                    )
                  })}</td>
                  <td valign="top">{movie.awards.map((award, i) => {
                    return (
                      <div>{award.year} : {award.award}</div>
                    )
                  })}</td>
                </tr>
              );
            })}
          </tbody>
        </table>              
      </div>
    );
  }
}

export default App;
