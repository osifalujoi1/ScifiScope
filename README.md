# Web Application using Java and Spring Boot
An application that allows you track unreleased Sci-fi movies through a REST API.

## Description
- This program reads in a list of sci-fi movies from a JSON file to a database. 
- It uses a class named "MovieController" to define a REST API controller that handles CRUD operations (Create, Read, Update, Delete) for movies stored in the database.
- The Controller merely controls the method calls, not the operations.
- A repository class is used instead to perform the CRUD operations. It encapsulates the database access logic, separating it from other parts of the application.
  - There are two repository classes in this project: "JdbcClientMovieRepository" and "MovieRepository".
  - The CRUD methods are written manually in "JdbcClientMovieRepository" class using JdbcClient object.
  - While "MovieRepository" is an interface that used ListCrudRepository methods in spring data jdbc dependency instead of manually writing the operations. Therefore, enhancing readability and simplicity.
- The API folder contains a json that can be imported to Postman for testing purposes.
### Prerequisites
- Java JDK 17 or newer
- Maven (for building the application)
- Spring Boot
- Postman
- Your preferred IDE (e.g., IntelliJ IDEA, Eclipse)

### App Limitations/Improvement
- Built to practice Java, Spring boot 3, and Database connectivity.
- Does not have a pretty front-end yet, just Json.
- More functionalities can be added like updating the list of the movies when the current date is greater than the release date.
