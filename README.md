# movie-recommendation-api
Movie recommendation  API

a simple application that provides movie suggestions for a user based on their movie genre preference

## Requirements

- `Java 17+`
- `Maven 4+`
- `Spring boot 2.6.3`

**Other packages used**

- `lombok`  
- `springdoc openapi ui`

## Installation (Build and run the app using maven)

1. Clone the repository: `https://github.com/vinayvishwkarma/movie-recommendation-api`

2. Make sure `maven` & `jdk/jre 17+` is installed in your running machine

3. Right click on project and Run-> Maven install  Or Using cmd.

   `$projectlocation> mvn clean install`

4. RUN the app

   `$projectLocation> java -jar target/movie-recommendation-api-0.0.1-SNAPSHOT.jar`

5. Alternatively, you can run the app without packaging it using -

   `mvn spring-boot:run`


### REST API sample request
Use the following GET URI
http://localhost:8080/v1/recommendations/movies/HORROR

[
    {
        "id": 1,
        "name": "The Conjuring",
        "platform": "HBO MAX",
        "genre": "HORROR",
        "rating": 7.5,
        "info": "this movie is horror"
    },
    {
        "id": 2,
        "name": "The Grudge",
        "platform": "Disney plus",
        "genre": "HORROR",
        "rating": 4.3,
        "info": "this movie is horror"
    }
]

Please not that persistent layer is not added yet.. we are fetching the data from movies.json file for testing our api
### Open-api documentation

The API is documented using springdoc-openapi and can be found at
`http://localhost:8080/swagger-ui/index.html`

