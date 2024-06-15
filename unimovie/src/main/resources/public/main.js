$(document).ready(function () {
    $("#recommendForm").submit(function (event) {
        event.preventDefault();
        const season = $("#season").val();
        const genre = $("#genre").val();
        fetchMovies('/movies/recommend', season, genre);
    });

    $("#getTop10").click(function () {
        const season = $("#season").val();
        const genre = $("#genre").val();
        fetchMovies('/movies/recommend/top10', season, genre);
    });

    function fetchMovies(endpoint, season, genre) {
        let url = `http://localhost:8080${endpoint}?season=${season}`;
        if (genre) {
            url += `&genre=${genre}`;
        }

        $.ajax({
            dataType: "json",
            url: url,
            method: "GET",
            success: function (data) {
                displayMovies(data);
            },
            error: function (error) {
                console.error("Error fetching movies:", error);
            }
        });
    }

    function displayMovies(movies) {
        const movieList = $("#movieList");
        movieList.empty();
        if (movies.length === 0) {
            movieList.append('<p>No movies found for the selected criteria.</p>');
        } else {
            movies.forEach(movie => {
                const movieCard = `
                    <div class="col-md-3">
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="card-title">${movie.title}</h5>
                                <p class="card-text">Season: ${movie.season}</p>
                                <p class="card-text">Genre: ${movie.genre}</p>
                                <p class="card-text">Rating: ${movie.rating}</p>
                            </div>
                        </div>
                    </div>
                `;
                movieList.append(movieCard);
            });
        }
    }
});
