package dev.ife.scifiScope.movie;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Repository
public class JdbcClientMovieRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcClientMovieRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientMovieRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Movie> findAll(){
        return jdbcClient.sql("select * from movie")
                .query(Movie.class)
                .list();
    }

    public Optional<Movie> findById(Integer id){
        return jdbcClient.sql("SELECT id,title,release_date,prod_company,review,rating,url FROM Movie WHERE id = :id")
                .param("id", id)
                .query(Movie.class)//map to a run
                .optional();//return as optional
    }

    //update() is used when you're either creating, changing, or deleting something
    public void create (Movie movie) {
        var updated = jdbcClient.sql("INSERT INTO Movie(id,title,release_date,prod_company,review,rating,url) values(?,?,?,?,?,?,?)")
                .params(List.of(movie.id(),movie.title(),movie.releaseDate(),movie.prodCompany(),movie.review(),movie.rating(),movie.url()))
                .update();
        Assert.state(updated == 1, "Failed to create movie " + movie.title());
    }

    public void update(Movie movie, Integer id){
        var updated = jdbcClient.sql("update movie set title = ?, release_date = ?, prod_company = ?, review = ?, rating = ?, url = ? where id = ?")
                .params(List.of(movie.title(),movie.releaseDate(),movie.prodCompany(),movie.review(),movie.rating(),movie.url(), id))
                .update();

        Assert.state(updated == 1, "Failed to update movie " + movie.title());
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from movie where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete movie " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from movie").query().listOfRows().size();
    }

    public void saveAll(List<Movie> movies) {
        movies.stream().forEach(this::create);
    }

    public List<Movie> findByTitle(String title) {
        return jdbcClient.sql("select * from movie where title = :title")
                .param("title", title)
                .query(Movie.class)
                .list();
    }
}
