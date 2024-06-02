package dev.ife.scifiScope.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MovieJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(MovieJsonDataLoader.class);
    private final JdbcClientMovieRepository movieRepository;
    private final ObjectMapper objectMapper;
    public MovieJsonDataLoader(JdbcClientMovieRepository movieRepository, ObjectMapper objectMapper){
        this.movieRepository = movieRepository;
        this.objectMapper = objectMapper;
    }

    //loading data from a JSON to a database
    @Override
    public void run(String... args) throws Exception {
        if(movieRepository.count() == 0){
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/movies.json")) { //read from file
                Movies allMovies = objectMapper.readValue(inputStream, Movies.class);
                log.info("Reading {} movies from JSON data and saving to a database.", allMovies.movies().size());
                movieRepository.saveAll(allMovies.movies()); //put off to database
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else{
            log.info("Not loading Movies from JSON data because the collection contains data.");
        }
    }
}
