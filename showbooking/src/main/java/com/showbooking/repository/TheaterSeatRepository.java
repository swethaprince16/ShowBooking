package com.showbooking.repository;

import com.showbooking.models.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat, Integer> {

}
