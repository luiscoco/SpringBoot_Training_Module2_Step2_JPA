# Flights Application

This is a Spring Boot application that manages flights data.

## Features

- In-memory database for flights, airports, carriers, and states.
- REST API to retrieve flights data.
- Data is loaded from a CSV file at startup.

## How to run the application

1. Clone the repository.
2. Make sure you have Java 17 and Maven installed.
3. Run the application using the following command:

```bash
./mvnw spring-boot:run
```

## API Endpoints

- `GET /flights`: Returns all flights.

## Database

The application uses an in-memory H2 database. The database is initialized at startup with data from the `flights.csv` file.

The following entities are used:

- `Flight`: Represents a flight.
- `Airport`: Represents an airport.
- `Carrier`: Represents a carrier.
- `State`: Represents a state.

## Code Snippets

Here is an example of how to define a repository using Spring Data JPA:

```java
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
```
