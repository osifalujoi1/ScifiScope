package dev.ife.scifiScope.movie;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public class InMemoryMovieRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMovieRepository.class);
    private final List<Movie> movies = new ArrayList<>();

    public List<Movie> findAll() {
        return movies;
    }

    public Optional<Movie> findById(Integer id) {
        return Optional.ofNullable(movies.stream()
                .filter(run -> run.id() == id)
                .findFirst()
                .orElseThrow(MovieNotFoundException::new));
    }

    public void create(Movie movie) {
        Movie newMovie = new Movie(
                movie.id(),
                movie.title(),
                movie.releaseDate(),
                movie.prodCompany(),
                movie.review(),
                movie.rating(),
                movie.url(),
                movie.version());

        movies.add(newMovie);
    }

    public void update(Movie newMovie, Integer id) {
        Optional<Movie> existingMovie = findById(id);
        if(existingMovie.isPresent()) {
            var r = existingMovie.get();
            log.info("Updating Existing Movie: " + existingMovie.get());
            movies.set(movies.indexOf(r),newMovie);
        }
    }

    public void delete(Integer id) {
        log.info("Deleting Movie: " + id);
        movies.removeIf(movie -> movie.id().equals(id));
    }

    public int count() {
        return movies.size();
    }

    public void saveAll(List<Movie> movies) {
        movies.stream().forEach(movie -> create(movie));
    }

    public List<Movie> findByRating(String rating) {
        return movies.stream()
                .filter(run -> Objects.equals(run.rating(), rating))
                .toList();
    }


    @PostConstruct
    private void init() {
        movies.add(new Movie(1,
                "Galactic Pioneers",
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0),
                "Universal Spectra Productions",
                85,
                "PG-13",
                "https://www.galacticpioneersmovie.com",
                1));

        movies.add(new Movie(2,
                "Galactic Pioneers",
                LocalDateTime.of(2024, Month.DECEMBER, 15, 0, 0),
                "Universal Spectra Productions",
                85,
                "PG-13",
                "https://www.galacticpioneersmovie.com",
                1));
    }


}
