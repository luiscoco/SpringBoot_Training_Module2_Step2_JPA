package com.luxoft.flights.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Flight {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String tailNum;
   private Date flightDate;

   @ManyToOne
   private Airport origin;

   @ManyToOne
   private Airport destination;

   @ManyToOne
   private Carrier carrier;

   private int depDelay;
   private int arrDelay;
   private int cancelled;

   public Flight(String tailNum, Date flightDate, Airport origin,
                 Airport destination, Carrier carrier,
                 int depDelay, int arrDelay,
                 int cancelled) {
       this.tailNum = tailNum;
       this.flightDate = flightDate;
       this.origin = origin;
       this.destination = destination;
       this.carrier = carrier;
       this.cancelled = cancelled;
       this.depDelay = depDelay;
       this.arrDelay = arrDelay;
   }

   public Flight() {
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTailNum() {
        return tailNum;
    }

    public void setTailNum(String tailNum) {
        this.tailNum = tailNum;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public int getDepDelay() {
        return depDelay;
    }

    public void setDepDelay(int depDelay) {
        this.depDelay = depDelay;
    }

    public int getArrDelay() {
        return arrDelay;
    }

    public void setArrDelay(int arrDelay) {
        this.arrDelay = arrDelay;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;
        return Objects.equals(tailNum, flight.tailNum) && Objects.equals(flightDate, flight.flightDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tailNum, flightDate);
    }

    @Override
    public String toString() {
        return "Flight {" +
                "tailNum='" + tailNum + '\'' +
                ", flightDate=" + flightDate +
                ", origin=" + origin +
                ", destination=" + destination +
                ", carrier=" + carrier +
                ", depDelay=" + depDelay +
                ", arrDelay=" + arrDelay +
                ", cancelled=" + cancelled +
                '}';
    }
}
