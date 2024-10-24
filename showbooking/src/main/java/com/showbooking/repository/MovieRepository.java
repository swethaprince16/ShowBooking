package com.showbooking.repository;

import com.showbooking.enums.Genre;
import com.showbooking.models.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

//    @Query("SELECT m FROM Movie m WHERE m.genre = :genre ORDER BY m.rating DESC")
//    List<Movie> findMovieByGenre(@Param("genre")Genre genre);
    @Query(value = "select * from movie where genre = ?1 order by rating desc", nativeQuery = true)
    List<Movie> findMovieByGenre(String genre);

    Movie findByMovieTitle(String title);
}
