package com.luxoft.flights.repositories;

import com.luxoft.flights.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
   Optional<Flight> findByTailNumAndFlightDate(String tailNum, Date flightDate);
}
