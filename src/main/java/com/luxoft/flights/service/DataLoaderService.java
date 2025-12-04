package com.luxoft.flights.service;

import com.luxoft.flights.model.Airport;
import com.luxoft.flights.model.Carrier;
import com.luxoft.flights.model.Flight;
import com.luxoft.flights.model.State;
import com.luxoft.flights.repositories.AirportRepository;
import com.luxoft.flights.repositories.CarrierRepository;
import com.luxoft.flights.repositories.FlightRepository;
import com.luxoft.flights.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class DataLoaderService {

    private final AirportRepository airportRepository;
    private final CarrierRepository carrierRepository;
    private final FlightRepository flightRepository;
    private final StateRepository stateRepository;

    @Autowired
    public DataLoaderService(AirportRepository airportRepository, CarrierRepository carrierRepository, FlightRepository flightRepository, StateRepository stateRepository) {
        this.airportRepository = airportRepository;
        this.carrierRepository = carrierRepository;
        this.flightRepository = flightRepository;
        this.stateRepository = stateRepository;
    }

    @PostConstruct
    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/flights.csv")))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");

                // States
                State originState = saveState(values[5]);
                State destState = saveState(values[10]);

                // Carriers
                Carrier carrier = saveCarrier(values[13], values[14]);

                // Airports
                Airport originAirport = saveAirport(values[3], values[4], originState);
                Airport destAirport = saveAirport(values[8], values[9], destState);

                // Flight
                saveFlight(values, originAirport, destAirport, carrier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Flight saveFlight(String[] values, Airport originAirport, Airport destAirport, Carrier carrier) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date flightDate = formatter.parse(values[0]);

        Optional<Flight> existingFlight = flightRepository.findByTailNumAndFlightDate(values[12], flightDate);
        if (existingFlight.isPresent()) {
            return existingFlight.get();
        }

        Flight flight = new Flight();
        flight.setFlightDate(flightDate);
        flight.setOrigin(originAirport);
        flight.setDestination(destAirport);
        flight.setCarrier(carrier);
        flight.setFlightNum(values[11]);
        flight.setTailNum(values[12]);
        flight.setDepartureTime(values[1]);
        flight.setArrivalTime(values[2]);
        flight.setDistance(Integer.parseInt(values[15]));

        return flightRepository.save(flight);
    }

    private Airport saveAirport(String airportCode, String airportName, State state) {
        Optional<Airport> existingAirport = airportRepository.findByAirportCode(airportCode);
        if (existingAirport.isPresent()) {
            return existingAirport.get();
        }
        Airport airport = new Airport();
        airport.setAirportCode(airportCode);
        airport.setAirportName(airportName);
        airport.setState(state);
        return airportRepository.save(airport);
    }

    private Carrier saveCarrier(String carrierCode, String carrierName) {
        Optional<Carrier> existingCarrier = carrierRepository.findById(carrierCode);
        if (existingCarrier.isPresent()) {
            return existingCarrier.get();
        }
        Carrier carrier = new Carrier();
        carrier.setCarrierCode(carrierCode);
        carrier.setCarrierName(carrierName);
        return carrierRepository.save(carrier);
    }

    private State saveState(String stateName) {
        Optional<State> existingState = stateRepository.findById(stateName);
        if (existingState.isPresent()) {
            return existingState.get();
        }
        State state = new State();
        state.setStateName(stateName);
        return stateRepository.save(state);
    }
}
