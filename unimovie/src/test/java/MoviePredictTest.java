import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.unimovie.pre.MovieController_predict;

public class MoviePredictTest {

    @Test
    public void testPredictMovieSuccessValidInput() {
        // Arrange (set up test data)
        String validInput = "Michael Bay,165,0,808,Sophia Myles,974,245428137,Action|Adventure|Sci-Fi,Bingbing Li,242420,Kelsey Grammer,2,blockbuster|bumblebee the character|semi truck and trailer|texas|truck,918,English,USA,PG-13,210000000,2014,956,2.35,56000";

        // Act (call the API method)
        ResponseEntity<?> response = new MovieController_predict().predictMovieSuccess(validInput);

        // Assert (verify expected behavior)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testPredictMovieSuccessInvalidManyInput() {
        // Arrange (set up test data)
        String invalidInput = "Michael Bay,165,0,808,Sophia Myles,974,245428137,Action|Adventure|Sci-Fi,Bingbing Li,242420,Kelsey Grammer,2,blockbuster|bumblebee the character|semi truck and trailer|texas|truck,918,English,USA,PG-13,210000000,2014,956,2.35";
        // wrong amount of factors.

        // Act (call the API method)
        new MovieController_predict().predictMovieSuccess(invalidInput);
    }

    @Test
    public void testPredictMovieSuccessInvalidLessInput() {
        // Arrange (set up test data)
        String invalidInput = "Michael Bay,165,0,808,Sophia Myles,974,245428137,Action|Adventure|Sci-Fi,Bingbing Li,242420,Kelsey Grammer,2,blockbuster|bumblebee the character|semi truck and trailer|texas|truck,918,English,USA,PG-13,210000000,2014";
        // wrong amount of factors.

        // Act (call the API method)
        new MovieController_predict().predictMovieSuccess(invalidInput);
    }

    @Test
    public void testPredictMovieEmptyInput() {
        // Arrange (set up test data)
        String emptyInput = "";

        // Act (call the API method)
        ResponseEntity<?> response = new MovieController_predict().predictMovieSuccess(emptyInput);
    }

    @Test
    public void testPredictMovieInvalidFormatInput() {
        // Arrange (set up test data)
        String invalidFormatInput = "Michael Bay,abc,0,808,Sophia Myles,974,245428137,Action|Adventure|Sci-Fi,Bingbing Li,242420,Kelsey Grammer,2,blockbuster|bumblebee the character|semi truck and trailer|texas|truck,918,English,USA,PG-13,210000000,2014,956,2.35,56000";

        // Act (call the API method)
        ResponseEntity<?> response = new MovieController_predict().predictMovieSuccess(invalidFormatInput);
    }
}