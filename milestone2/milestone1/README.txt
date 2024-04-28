. PLEASE BUILD IMAGE WITH DOCKERFILE
2. RUN THE MADE IMAGE
3. INSIDE THE DOCKER CONTAINER, RUN run.sh

If 'File read successfully' command come out, then it's ready to test the implemented APIs.

BE CAREFUL, THE PORT NUMBER IS SET AS 8888


----------Part2----------
There are pre-existing employees in our mongodb named 'employee'
And these are example commands for testing APIs.

GET:
curl -X GET http://localhost:8888/employees
-(Showing all employees)
curl -X GET http://localhost:8888/employees/{employeeId}
(Get an employee with ID value)

POST:
curl -X POST http://localhost:8888/employees -H "Content-Type: application/json" -d '{"name":"James","role":"player"}'
(Add a new employee)

PUT:
curl -X PUT http://localhost:8888/employees/{employeeId} -H "Content-Type: application/json" -d '{"name":"Jane Doe","role":"Senior Developer"}'
(Update an Employee)

DELETE:
curl -X DELETE http://localhost:8888/employees/{employeeId}
(Delete an employee)




----------Part3----------
Tese are example commands for testing APIs

<<< Movie >>>
Movie Class example:
{
  "_id": "1",
  "title": "Toy Story (1995)",
  "genres": "Animation|Children's|Comedy",
  "_class": "com.example.Movie"
}


GET:
curl -X GET http://localhost:8888/movies
(List of all movies)
curl -X GET http://localhost:8888/movies/22
(Get a movie with ID value)

POST:
curl -X POST http://localhost:8888/movies -H "Content-Type: application/json" -d '{"id":532,"title":"New Movie","genres":"Action"}'
(Add a new movie)

PUT:
curl -X PUT http://localhost:8888/movies/10 -H "Content-Type: application/json" -d '{"title":"Updated Movie Title","genres":"Drama"}'
(Update a movie)




<<< User >>>
User Class example:
{
  "_id": "1",
  "gender": "F",
  "age": 1,
  "occupation": "10",
  "zipCode": "48067",
  "_class": "com.example.User"
}


GET:
curl -X GET http://localhost:8888/users
(List of all users)
curl -X GET http://localhost:8888/users/6
(Get a movie with ID value)

POST:
curl -X POST http://localhost:8888/users -H "Content-Type: application/json" -d '{"id":"1000000","gender":"M","age":35,"occupation":4,"zipCode":"12345"}'
(Add a new user)

PUT:
curl -X PUT http://localhost:8888/users/3 -H "Content-Type: application/json" -d '{"gender":"F","age":35,"occupation":15, "zipCode":"54321"}'
(Update a user)




<<< Rating >>>
Rating Class example:
{
  "_id": {
    "$oid": "660feeea7d067f75db0b9e1b"
  },
  "userId": "1",
  "movieId": "1193",
  "rating": 5,
  "timestamp": {
    "$numberLong": "978300760"
  },
  "_class": "com.example.Rating"
}


GET:
curl -X GET http://localhost:8888/ratings/{score}
curl -X GET http://localhost:8888/ratings/4
(List of all movies with greater rating score than specific score in url)

POST:
curl -X POST http://localhost:8888/ratings -H "Content-Type: application/json" -d '{"userId":"user123","movieId":"movie456","rating":5,"timestamp":1596240000}'
(Add a new rating)

PUT:
curl -X PUT http://localhost:8888/ratings/{ratingId} -H "Content-Type: application/json" -d '{"userId":"user123","movieId":"movie789","rating":4,"timestamp":1596240001}'
curl -X PUT http://localhost:8888/ratings/4 -H "Content-Type: application/json" -d '{"userId":"user123","movieId":"movie789","rating":4,"timestamp":1596240001}'
(Update a rating)















