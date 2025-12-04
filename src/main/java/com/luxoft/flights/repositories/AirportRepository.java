package com.luxoft.flights.repositories;

import com.luxoft.flights.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
   Optional<Airport> findByAirportCode(String airportCode);
}
