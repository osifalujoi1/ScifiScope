package dev.ife.scifiScope.movie;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Movie(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime releaseDate,
        String prodCompany,
        Integer review,
        String rating,
        String url,
        //version variable to track if it is an existing table or a new one
        @Version
        Integer version
) {
}
