package com.showbooking.repository;

import com.showbooking.dto.ShowDto;
import com.showbooking.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query(value = "select * from shows s, movie m, theater t where s.theater_theatre_id = t.theatre_id and s.movie_id = m.id and t.city=? and m.movie_title=?", nativeQuery = true)
    List<Show> findByCityAndMovie(String city, String movie);

    @Query(value = "select * from shows s, theater t where s.theater_theatre_id = t.theatre_id and t.city=?", nativeQuery = true)
    List<Show> findByCity(String city);

    @Query(value = "select * from shows s, theater t where s.theater_theatre_id = t.theatre_id and t.city=? and t.theatre_name=?", nativeQuery = true)

    List<Show> findByCityAndTheater(String city, String theaterName);
}
