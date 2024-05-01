package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    // show all movies list
    @GetMapping("/movies")
    public List<Movie> getAllUsers() {
        return movieRepository.findAll();
    }

    // show random movies 
    @GetMapping("/movies/random")
    public List<Movie> getRandomMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        Collections.shuffle(allMovies); 
        return allMovies.stream().limit(10).collect(Collectors.toList()); 
    }

    // show random movie about season and we can choose genre optionally
    @GetMapping("/movies/recommend")
    public List<Movie> recommendrandomMovies(
        @RequestParam String season,
        @RequestParam(required = false) String genre) { 
        Stream<Movie> filter_movie = movieRepository.findAll().stream()
            .filter(movie -> movie.getSeason().equalsIgnoreCase(season));
        if (genre != null) {
            filter_movie = filter_movie.filter(movie -> {
                List<String> movieGenres = Arrays.asList(movie.getGenre().split(",\\s*"));
                return movieGenres.contains(genre.trim());
            });
        }
        List<Movie> filter_Movies = filter_movie.collect(Collectors.toList());
        Collections.shuffle(filter_Movies);
        return filter_Movies.stream().limit(10).collect(Collectors.toList());
    }

    // show top 10 movie about season and we can choose genre optionally
    @GetMapping("/movies/recommend/top10")
    public List<Movie> recommendtop10Movies(
        @RequestParam String season,
        @RequestParam(required = false) String genre) { 
        Stream<Movie> filter_movie = movieRepository.findAll().stream()
            .filter(movie -> movie.getSeason().equalsIgnoreCase(season));
        if (genre != null) {
            filter_movie = filter_movie.filter(movie -> {
                List<String> movieGenres = Arrays.asList(movie.getGenre().split(",\\s*"));
                return movieGenres.contains(genre.trim());
            });
        }
        List<Movie> top10_movies = filter_movie
                                .sorted((movie1, movie2) -> {
                                    double rating1 = Double.parseDouble(movie1.getRating());
                                    double rating2 = Double.parseDouble(movie2.getRating());
                                    return Double.compare(rating2, rating1); // 내림차순 정렬
                                })
                                .limit(10)
                                .collect(Collectors.toList());
        return top10_movies;
    }
}