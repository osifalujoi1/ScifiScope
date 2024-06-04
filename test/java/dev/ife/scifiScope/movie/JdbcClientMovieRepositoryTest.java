package dev.ife.scifiScope.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcClientMovieRepository.class)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class JdbcClientMovieRepositoryTest {
    JdbcClientMovieRepository repository;
    @BeforeEach
    void setUp() {
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
    void shouldFindAllRuns() {
        List<Movie> movies = repository.findAll();
        assertEquals(2, movies.size());
    }

    @Test
    void shouldFindRunWithValidId() {
        var movie = repository.findById(1).get();
        assertEquals("Monday Morning Run", movie.title());
        assertEquals(85, movie.rating());
    }

    @Test
    void shouldNotFindRunWithInvalidId() {
        var movie = repository.findById(3);
        assertTrue(movie.isEmpty());
    }

    @Test
    void shouldCreateNewRun() {
        repository.create(new Movie(3,
                "Galactic",
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0),
                "Universal Spectra Productions",
                100,
                "PG-13",
                "https://www.galacticpioneersmovie.com",
                null));
        List<Movie> runs = repository.findAll();
        assertEquals(3, runs.size());
    }

    @Test
    void shouldUpdateRun() {
        repository.update(new Movie(
                2,
                "Galactic Pioneers",
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0),
                "Universal Spectra Productions",
                90,
                "PG-13",
                "https://www.galacticpioneersmovie.com",
                null), 2);
        var movie = repository.findById(1).get();
        assertEquals("Galactic Pioneers", movie.title());
        assertEquals(85, movie.review());
        assertEquals("PG-13", movie.rating());
    }

    @Test
    void shouldDeleteRun() {
        repository.delete(1);
        List<Movie> movies = repository.findAll();
        assertEquals(1, movies.size());
    }

}