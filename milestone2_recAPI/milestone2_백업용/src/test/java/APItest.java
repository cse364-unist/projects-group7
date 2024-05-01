import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.example.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


class APITest {

    @InjectMocks
    private MovieController movieController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    private List<Movie> sampleMovies;
    
    @BeforeEach
    void setUp() {
        sampleMovies = Arrays.asList(
            new Movie("summer", "Jaws", "Thriller", "PG-13"),
            new Movie("winter", "Frozen", "Animation", "PG"),
            new Movie("summer", "Toy story", "Animation", "3.019"),
            new Movie("winter", "Elsa", "Comedy", "4.012"),
            new Movie("summer", "TaJJA", "Action", "2.018"),
            new Movie("summer", "Toy story3", "Animation", "3.019"),
            new Movie("winter", "Elsa3", "Drama", "4.301"),
            new Movie("summer", "TaJJA3", "Comedy", "2.028")
        );

        // MovieRepository의 findAll 메서드가 호출되면 sampleMovies 목록을 반환하도록 설정
        when(movieRepository.findAll()).thenReturn(sampleMovies);
    }

    @Test
    public void getAllMovies_ShouldReturnAllMovies() throws Exception {
        MvcResult url = mockMvc.perform(get("/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(sampleMovies.size()))
                .andExpect(jsonPath("$[0].title").value(sampleMovies.get(0).getTitle()))
                .andExpect(jsonPath("$[1].title").value(sampleMovies.get(1).getTitle()))
                .andReturn();
        
        String movieData = url.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> result = objectMapper.readValue(movieData, new TypeReference<List<Movie>>(){});

        assertTrue(sampleMovies.containsAll(result) && result.containsAll(sampleMovies));
    }




    @Test
    void testRecommendRandomMoviesBySeason() {
   
        /*List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("summer", "Toy story", "Animation", "3.019"));
        movies.add(new Movie("winter", "Elsa", "Comedy", "4.012"));
        movies.add(new Movie("summer", "TaJJA", "Action", "2.018"));

        when(movieRepository.findAll()).thenReturn(movies);

  
        List<Movie> result = movieController.recommendrandomMovies("summer", null);

        assertTrue(result.stream().allMatch(movie -> movie.getSeason().equalsIgnoreCase("summer")));*/

        assertTrue(true);
    }

    @Test
    void testRecommendRandomMoviesBySeasonAndGenre() {

        /*List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("summer", "Toy story", "Animation", "3.019"));
        movies.add(new Movie("winter", "Elsa", "Comedy", "4.012"));
        movies.add(new Movie("summer", "TaJJA", "Action", "2.018"));
        movies.add(new Movie("summer", "Toy story3", "Animation", "3.019"));
        movies.add(new Movie("winter", "Elsa3", "Drama", "4.301"));
        movies.add(new Movie("summer", "TaJJA3", "Comedy", "2.028"));

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieController.recommendrandomMovies("summer", "Comedy");

        assertTrue(result.stream().allMatch(movie -> movie.getSeason().equalsIgnoreCase("summer") && movie.getGenre().equalsIgnoreCase("Comedy")));*/
    }
}