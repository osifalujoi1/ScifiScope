package dev.ife.scifiScope.movie;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping("")
    List<Movie> findAll(){
        return movieRepository.findAll();
    }

    Movie findById(@PathVariable Integer id){
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()){
            throw new MovieNotFoundException();
        }
        return movie.get();
    }

    //post -> create
    //we need a tool to run a post request
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody Movie movie){
        movieRepository.save(movie);
    }

    //put -> update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@Valid @RequestBody Movie movie, @PathVariable Integer id){
        movieRepository.save(movie);
    }

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id){

        //the delete actually takes in an object so we'd
        movieRepository.delete(movieRepository.findById(id).get());
    }

    @GetMapping("/rating/{rating}")
    List<Movie> findAllByRating(@PathVariable String rating){
        return movieRepository.findAllByRating(rating);
    }
}
