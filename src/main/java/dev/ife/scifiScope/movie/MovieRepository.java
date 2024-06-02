package dev.ife.scifiScope.movie;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

//Adding spring data jdbc dependency gives you access to List CRUD repository instead of
//creating all of your update, delete, and create methods manually using JdbcClient
//It takes a parameter of the object type and id type
//After stating Integer as the type of the ID. Identify it in Movie.java's declaration of Id using @Id annotation
public interface MovieRepository extends ListCrudRepository<Movie,Integer> {
    @Query
    List<Movie> findAllByRating(String rating);
}
