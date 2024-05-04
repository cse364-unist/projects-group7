# CSE364-unist group 7

This repository contains the code for a feature that allows users to merge the faces of two individuals.

## Description

The project implements a feature that enables users to merge the faces of two individuals in an image. It utilizes computer vision techniques to detect and swap the faces, resulting in a combined image.

## Changes from Proposal

There were no significant changes from the initial proposal.

## REST APIs Implemented

### Merge Faces API

- **Description:** This API merges the faces of two individuals in an image.
- **HTTP Method:** POST
- **Endpoint:** `/features/upload`
- **Request Parameters:**
  - `file1`: The image file of the first individual.
  - `file2`: The image file of the second individual.
- **Response:** 
  - `image`: Base64 encoded string of the resulting image.
  - `message`: A confirmation message indicating the successful face swap.

#### Example Curl Commands

##### POST API Request

curl -X POST -F "file1=@images/elon_musk.jpg" -F "file2=@images/mark.jpg" "http://localhost:8888/features/upload"

Note: Replace http://localhost:8888 with the appropriate server address

##### Expected Output:

{
  "image": "Base64_encoded_image",
  "message": "Faces swapped successfully."
}

This command uploads two image files (elon_musk.jpg and mark.jpg) and swaps the faces of the individuals. The response contains the merged image in base64 format along with a success message.
### Movie Recommendation System

This repository contains the code for a movie recommendation system that recommends movies based on seasons, genres, and rankings.

#### Description

The system implements various features for recommending movies to users. It provides recommendations based on seasons, genres, and top rankings. Additionally, it offers random movie recommendations.

#### Changes from Proposal

We made changes to the implementation algorithm for the seasonal recommendation feature. Originally, we planned to recommend movies for each season based on the time people reviewed them. However, due to the lack of consistent data on review times, we decided to recommend movies for each season based on the release time of the movie.

#### REST APIs Implemented

##### Get Top10 Recommendation List of Season with Genre

- **Description:** Retrieves the top 10 recommendation list of movies for a specific season and genre.
- **HTTP Method:** GET
- **Endpoint:** `/movies/recommend/top10`
- **Query Parameters:**
  - `season`: The season for which recommendations are requested.
  - `genre`: The genre of movies to consider.
- **Response:** List of top 10 recommended movie objects.

##### Get Top 10 Recommendation List of Season

- **Description:** Retrieves the top 10 recommendation list of movies for a specific season.
- **HTTP Method:** GET
- **Endpoint:** `/movies/recommend/top10`
- **Query Parameters:**
  - `season`: The season for which recommendations are requested.
- **Response:** List of top 10 recommended movie objects.


#### Example Curl Commands

##### Get All Movie List


###### 1.Get Top 10 Recommendation List of Season with Genre
curl -X GET "http://localhost:8080/movies/recommend/top10?season=summer&genre=Comedy"
###### Expected Output:
[
  {
    "title": "Recommended Movie 1",
    "genre": "Comedy",
    ...
  },
  ...
]
###### 2.Get Top 10 Recommendation List of Season
curl -X GET "http://localhost:8080/movies/recommend/top10?season=summer"
###### Expected Output:
[
  {
    "title": "Random Movie 1",
    "genre": "Drama",
    ...
  },
  ...
]

### Movie Prediction API

This API provides movie prediction functionality based on input movie factors.

#### Features

##### Movie Prediction

- **Description**: Predicts the success level of a movie based on provided factors.
- **REST API**:
  - Endpoint: `/features/movie_predict`
  - Method: POST
  - Request Body: 
    - `factor`: A comma-separated string containing movie factors
  - Response: Returns one of the following strings: `'FLOP'`, `'AVG'`, `'HIT'` indicating the predicted success level of the movie.

#### Example Usage

##### Movie Prediction API Example

###### Example:
curl -X POST -F "factor=James Cameron,178,0,855,Joel David Moore,1000,760505847,Action|Adventure|Fantasy|Sci-Fi,CCH Pounder,886204,Wes Studi,0,avatar|future|marine|native|paraplegic,3054,English,USA,PG-13,237000000,2009,936,1.78,33000" "http://localhost:8080/features/movie_predict"

###### Expected Output:
['HIT']
