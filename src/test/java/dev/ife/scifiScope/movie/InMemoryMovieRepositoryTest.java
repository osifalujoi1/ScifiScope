package dev.ife.scifiScope.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMovieRepositoryTest {
    InMemoryMovieRepository repository;
    @BeforeEach
    void setUp() {
        repository = new InMemoryMovieRepository();
        repository.create(new Movie(
                1,
                "Galactic Pioneers",
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0),
                "Universal Spectra Productions",
                85,
                "PG-13",
                "https://www.galacticpioneersmovie.com",
                2
        ));
        repository.create(new Movie(
                2,
                "Galactic Pioneers",
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0),
                "Universal Spectra Productions",
                85,
                "PG-13",
                "https://www.galacticpioneersmovie.com",
                2
        ));
    }

    @Test
    void shouldFindAllMovies(){
        List<Movie> movies = repository.findAll();
        assertEquals(2, movies.size(), "Should have returned 2 movies");
    }

}