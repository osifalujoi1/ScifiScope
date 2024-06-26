package dev.ife.scifiScope.movie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(){
        super("Movie not found");
    }
}
