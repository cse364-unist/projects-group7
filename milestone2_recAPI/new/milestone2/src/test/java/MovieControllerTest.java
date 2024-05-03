import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.beans.Transient;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;


import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class MovieControllerTest {

    @MockBean
    private MovieRepository movieRepository;


    @Test
    public void testGetAllMovies() throws Exception {
        List <Movie> sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "1.013"),
            new Movie("winter", "Frozen", "Animation", "2.019"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJJA3", "Comedy", "2.028")
        );
    
        List<Movie> resultMovies = new MovieController(sampleMovies).getAllMovies();
        
        assertNotNull(resultMovies);
        assertEquals(8, resultMovies.size());
        assertTrue(resultMovies.containsAll(sampleMovies));    
    }

    @Test
    public void testRandomMovies() throws Exception {
        List <Movie> sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "1.013"),
            new Movie("winter", "Frozen", "Animation", "2.019"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJddd", "Comedy", "2.028"),
            new Movie("summer", "Miku", "Animation", "3.019"),
            new Movie("winter", "Elsa1", "Drama", "4.301"),
            new Movie("summer", "TaJJA8", "Comedy", "2.028")
        );    

        List<Movie> resultMovies = new MovieController(sampleMovies).getRandomMovies();
        List<String> movieNames = resultMovies.stream()
                                              .map(Movie::getTitle) 
                                              .collect(Collectors.toList());

        
        Set<String> distinctNames = new HashSet<>(movieNames);


        assertEquals(distinctNames.size(), movieNames.size());
        assertNotNull(resultMovies);
        assertEquals(10, resultMovies.size());

    }

    @Test
    public void testRecommendMoviesBySeason() throws Exception {
        List <Movie> sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "1.013"),
            new Movie("winter", "Frozen", "Animation", "2.019"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJddd", "Comedy", "2.028"),
            new Movie("summer", "Miku", "Animation", "3.019"),
            new Movie("winter", "Elsa1", "Drama", "4.301"),
            new Movie("summer", "TaJJA8", "Comedy", "2.028")
        );    

        List<Movie> resultMovies = new MovieController(sampleMovies).recommendrandomMovies("summer", null);
        List<String> movieSeason = resultMovies.stream()
                                              .map(Movie::getSeason) 
                                              .collect(Collectors.toList());

        assertTrue(movieSeason.stream()
            .allMatch(season -> season.equalsIgnoreCase("summer")));
        assertNotNull(resultMovies);
        assertEquals(7, resultMovies.size());

    }

    @Test
    public void testRecommendMoviesByGenre() throws Exception {
        List <Movie> sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "1.013"),
            new Movie("winter", "Frozen", "Animation", "2.019"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJddd", "Comedy", "2.028"),
            new Movie("summer", "Miku", "Animation", "3.019"),
            new Movie("winter", "Elsa1", "Drama", "4.301"),
            new Movie("summer", "TaJJA8", "Comedy", "2.028")
        );    

        List<Movie> resultMovies = new MovieController(sampleMovies).recommendrandomMovies("summer", "Comedy");
        List<String> movieSeason = resultMovies.stream()
                                              .map(Movie::getSeason) 
                                              .collect(Collectors.toList());

        
        assertTrue(resultMovies.stream()
            .allMatch(movie -> movie.getSeason().equalsIgnoreCase("summer") 
            && movie.getGenre().equalsIgnoreCase("Comedy")));
        assertNotNull(resultMovies);
        assertEquals(2, resultMovies.size());

    }

    @Test
    public void testRecommendTop10MoviesByGenre() throws Exception {
        List <Movie> sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "1.013"),
            new Movie("winter", "Frozen", "Animation", "2.019"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJddd", "Comedy", "2.028"),
            new Movie("summer", "Miku", "Animation", "3.019"),
            new Movie("winter", "Elsa1", "Drama", "4.301"),
            new Movie("summer", "TaJJA8", "Comedy", "2.028")
        );    

        List<Movie> resultMovies = new MovieController(sampleMovies).recommendtop10Movies("summer", "Comedy");
        List<Movie> resultMovies2 = new MovieController(sampleMovies).recommendtop10Movies("winter", "Comedy");
        List<String> movieSeason = resultMovies.stream()
                                              .map(Movie::getSeason) 
                                              .collect(Collectors.toList());

        
        assertTrue(resultMovies.stream()
            .allMatch(movie -> movie.getSeason().equalsIgnoreCase("summer") 
            && movie.getGenre().equalsIgnoreCase("Comedy")));
        assertNotNull(resultMovies);
        assertEquals(2, resultMovies.size());

    }

    @Test
    public void testRecommendMoviesBySeasonPer() throws Exception {
        List <Movie> sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "1.013"),
            new Movie("winter", "Frozen", "Animation", "2.019"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJddd", "Comedy", "2.028"),
            new Movie("summer", "Miku", "Animation", "3.019"),
            new Movie("winter", "Elsa1", "Drama", "4.301"),
            new Movie("summer", "TaJJA8", "Comedy", "2.028")
        );    

        List<Movie> resultMovies = new MovieController(sampleMovies).recommendrandomMovies("winter", null);
        
        assertNotNull(resultMovies);

    }

    @Test
    public void testRecommendtop10MoviesBySeason() throws Exception {
        List <Movie> sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "1.013"),
            new Movie("winter", "Frozen", "Animation", "2.019"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJddd", "Comedy", "2.028"),
            new Movie("summer", "Miku", "Animation", "3.019"),
            new Movie("winter", "Elsa1", "Drama", "4.301"),
            new Movie("summer", "TaJJA8", "Comedy", "2.028")
        );    

        List<Movie> resultMovies = new MovieController(sampleMovies).recommendtop10Movies("summer", null);
        List<String> movieSeason = resultMovies.stream()
                                              .map(Movie::getSeason) 
                                              .collect(Collectors.toList());

        assertTrue(movieSeason.stream()
            .allMatch(season -> season.equalsIgnoreCase("summer")));
        assertNotNull(resultMovies);
        assertEquals(7, resultMovies.size());

    }

    @Test
    public void testSetTitle() {
        Movie m = new Movie();
        String expectedTitle = "Titanic";
        m.setTitle(expectedTitle);
        assertEquals(expectedTitle, m.getTitle());
    }

    @Test
    public void testSetGenre() {
        Movie m = new Movie();
        String expectedGenre = "Drama";
        m.setGenre(expectedGenre);
        assertEquals(expectedGenre, m.getGenre());
    }

    @Test
    public void testSetRating() {
        Movie m = new Movie();
        String expectedRating = "9.0";
        m.setRating(expectedRating);
        assertEquals(expectedRating, m.getRating());
    }

    @Test
    public void testSetSeason() {
        Movie m = new Movie();
        String expectedSeason = "winter";
        m.setSeason(expectedSeason);
        assertEquals(expectedSeason, m.getSeason());
    }

    @Test
    public void hashCodeTest() {
        Movie movie = new Movie("summer", "TaJJA8", "Comedy", "2.028");
        int HashCode = 2;
        assertEquals(HashCode, movie.hashCode());
    }

    @Test
    public void equalsTest() {
        Movie movie = new Movie("summer", "TaJJA8", "Comedy", "2.028");
        Movie movie2 = new Movie("summer", "TaJJA8", "Comedy", "2.028");
        assertTrue(movie2.equals(movie));
    }

    @Test
    public void toStringRepresentation() {
        Movie movie = new Movie("summer", "TaJJA8", "Comedy", "2.028");
        String expected = "Movie{title='TaJJA8', genre='Comdey', season='summer', rating='2.028'}";
        assertEquals(expected, movie.toString());
    }

}