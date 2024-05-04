please start the image with commend docker run -p 8080:8080 -it --name usa test /bin/bash

# Recommendation of movie by season & genre
Projcect Name
Recommedation of movie by season & genre

Description of feature
We implement the feature that recommed the movies corresponding with seasons and genres. 
And we recommend the movies with top 10 ranking movies and randomly recommend movie.

Change: we change the implementation algorithm for seasoning recommendation feature
- Originally, we were going to recommend movies for each season by dividing the seasons 
 based on the time people reviewed them. However, we realized that it was impossible to 
 divide the seasons based on the time reviewd because there was a lack of data on the Internet 
 including review time, and when we checked the time they reviewed from the MovieLens' data, the
 time they reviewed for same movie appeared differently. Therefore, since movies are usually 
 released during the season when they are expected to be successful, we decided to recommend 
 movies for each season by dividing the seasons based on the release time of the movie.

List of Rest API get all movies list feature

-1 get random recommendation list of season with genre 

-2 get random recommendation list of season  

-3 get top10 recommendation list of season with genre

-4 get top 10 recommendation list of season

curl command


get random recommendation list of season -> below is the example
-1 curl -X GET "http://localhost:8080/movies/recommend?season=summer"

get top10 recommendation list of season with genre -> below is the example
-2 curl -X GET "http://localhost:8080/movies/recommend?season=summer&genre=Comedy"

get top10 recommendation list of season with genre -> below is the example
-3 curl -X GET "http://localhost:8080/movies/recommend/top10?season=summer&genre=Comedy"

get top 10 recommendation list of season -> below is the example
-4 curl -X GET "http://localhost:8080/movies/recommend/top10?season=summer"



ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

# Project Name

This repository contains the code for a feature that allows users to merge the faces of two individuals.

Description
The project implements a feature that enables users to merge the faces of two individuals in an image. It utilizes computer vision techniques to detect and swap the faces, resulting in a combined image.

Changes from Proposal
There were no significant changes from the initial proposal.

REST APIs Implemented
Merge Faces API
Description: This API merges the faces of user face with the movie character in movie poster(Both images should be provided by user).
HTTP Method: POST
Endpoint: /features/upload
Request Parameters:
file1: The image file(movie poster)
file2: The image file of user face.
Response:
message: A confirmation message indicating the successful face swap.
image: Base64 encoded string of the resulting image.
Example Curl Commands
POST API Request
curl -X POST -F "file1=@images/elon_musk.jpg" -F "file2=@images/mark.jpg" "http://localhost:8080/features/upload"

Note: Replace http://localhost:8080 with the appropriate server address

Expected Output:
{ "message": "Faces swapped successfully.", "image": "Base64_encoded_image" }

This command uploads two image files (elon_musk.jpg and mark.jpg) and swaps the faces of the individuals. The response contains the merged image in base64 format along with a success message.

POST API Wrong Request
curl -X POST -F "file1=@images/elon_musk.jpg" -F "file2=@images/emptyfile.jpg" "http://localhost:8080/features/upload"

Note: When one of image files is empty

Expected Output:
{ "error":"Please select two files to upload." }

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
# Movie Prediction API

This API provides movie prediction functionality based on input movie factors.

## Features

### Movie Prediction
- **Description**: Predicts the success level of a movie based on provided factors.
- **REST API**:
  - Endpoint: `/features/movie_predict`
  - Method: POST
  - Request Body: 
    - `factor`: A comma-separated string containing movie factors
  - Response: Returns one of the following strings: `'FLOP'`, `'AVG'`, `'HIT'` indicating the predicted success level of the movie.

## Example Usage

### Movie Prediction API Examples

#### Example 1:
curl -X POST -F "factor=James Cameron,178,0,855,Joel David Moore,1000,760505847,Action|Adventure|Fantasy|Sci-Fi,CCH Pounder,886204,Wes Studi,0,avatar|future|marine|native|paraplegic,3054,English,USA,PG-13,237000000,2009,936,1.78,33000" "http://localhost:8080/features/movie_predict"

##### Expected Output:
HIT

#### Example 2:
curl -X POST -F "factor=Lawrence Guterman,94,6,227,Traylor Howard,490,17010646,Comedy|Family|Fantasy,Jamie Kennedy,40751,Ben Stein,0,baby|cartoon on tv|cartoonist|dog|mask,239,English,USA,PG,84000000,2005,294,1.85,881" "http://localhost:8080/features/movie_predict"

##### Expected Output:
AVG

#### Example 3:
curl -X POST -F "factor=Jon M. Chu,115,209,41,Sean Kingston,569,73000942,Documentary|Music,Usher Raymond,74351,Boys II Men,1,boyhood friend|manager|plasma tv|prodigy|star,233,English,USA,G,13000000,2011,69,1.85,62000" "http://localhost:8080/features/movie_predict"


##### Expected Output:
FLOP
