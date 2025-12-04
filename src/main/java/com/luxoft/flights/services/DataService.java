package com.luxoft.flights.services;

import com.luxoft.flights.model.Airport;
import com.luxoft.flights.model.Carrier;
import com.luxoft.flights.model.Flight;
import com.luxoft.flights.model.State;

import java.util.List;
import java.util.Set;

public interface DataService {
    Flight saveFlight(Flight flight);

    Airport saveAirport(Airport airport);

    Carrier saveCarrier(Carrier carrier);

    State saveState(State state);

    List<Flight> getFlights();

    Set<Airport> getAirports();

    Set<Carrier> getCarriers();

    Set<State> getStates();

    List<Flight> getFlightsByOrigin(String airportCode);

    List<Flight> getFlightsByCarrier(String carrierCode);

    List<Flight> getFlightsByState(String stateName);
}
