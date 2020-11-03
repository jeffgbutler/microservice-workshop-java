new Vue({
  el: "#app",
  data: {
    baseURL: "localhost:8080",
    started: false,
    movies: [],
    errors: []
  },
  computed: {
    startDisabled: function() {
      return this.started || this.baseURL === "";
    },
    hasMovies: function() {
      return this.movies.length > 0;
    },
    hasErrors: function() {
      return this.errors.length > 0;
    }
  },
  methods: {
    start: function() {
      this.started = true;
      this.scheduleUpdate();
    },
    stop: function() {
      this.started = false;
    },
    clear: function() {
      this.movies = [];
      this.errors = [];
    },
    scheduleUpdate: function() {
      setTimeout(() => this.getMovie(), 1000);
    },
    addError: function(err) {
      this.errors.unshift({
        timestamp: new Date().toLocaleString(),
        message: err.message
      });
      this.errors = this.errors.slice(0, 10);
    },
    addMovie: function(data) {
      this.movies.unshift({
        id: data.id,
        title: data.title,
        runLength: data.runLength,
        releaseDate: data.releaseDate,
        castMembers: data.castMembers,
        awards: data.awards
      });
      this.movies = this.movies.slice(0, 5);
    },
    getMovie: function() {
      let movieId = Math.floor(Math.random() * 20) + 1;
      fetch(`http://${this.baseURL}/movie/${movieId}`, { method: "GET" })
        .then(res => res.json())
        .then(data => {
          this.addMovie(data);
        })
        .catch(err => {
          this.addError(err);
        })
        .then(() => {
          if (this.started) {
            this.scheduleUpdate();
          }
        });
    }
  }
});
