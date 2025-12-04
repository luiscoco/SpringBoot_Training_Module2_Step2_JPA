package com.luxoft.flights.model;

import jakarta.persistence.*;

@Entity
public class Airport {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int airportID;

   private String airportCode;

   @ManyToOne
   @JoinColumn(name = "state_name")
   private State state;

   public Airport(int airportID, String airportCode, State state) {
       this.airportID = airportID;
       this.airportCode = airportCode;
       this.state = state;
   }

   public Airport() {
   }

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;
        return airportID == airport.airportID;
    }

    @Override
    public int hashCode() {
        return airportID;
    }

    @Override
    public String toString() {
        return airportCode + " (" + state + ")";
    }
}
