package com.example.spaced;

public class TicketSliderData {

    private String Departure_planet, SPACED_ID, Arrival_planet, Departure_country, Arrival_planet_area, Departure_time, Arrival_time,
            Departure_date, Arrival_date;

    public TicketSliderData(String departure_planet, String SPACED_ID, String arrival_planet, String departure_country, String arrival_planet_area, String departure_time, String arrival_time, String departure_date, String arrival_date) {
        Departure_planet = departure_planet;
        this.SPACED_ID = SPACED_ID;
        Arrival_planet = arrival_planet;
        Departure_country = departure_country;
        Arrival_planet_area = arrival_planet_area;
        Departure_time = departure_time;
        Arrival_time = arrival_time;
        Departure_date = departure_date;
        Arrival_date = arrival_date;
    }

    public String getDeparture_planet() {
        return Departure_planet;
    }

    public void setDeparture_planet(String departure_planet) {
        Departure_planet = departure_planet;
    }

    public String getSPACED_ID() {
        return SPACED_ID;
    }

    public void setSPACED_ID(String SPACED_ID) {
        this.SPACED_ID = SPACED_ID;
    }

    public String getArrival_planet() {
        return Arrival_planet;
    }

    public void setArrival_planet(String arrival_planet) {
        Arrival_planet = arrival_planet;
    }

    public String getDeparture_country() {
        return Departure_country;
    }

    public void setDeparture_country(String departure_country) {
        Departure_country = departure_country;
    }

    public String getArrival_planet_area() {
        return Arrival_planet_area;
    }

    public void setArrival_planet_area(String arrival_planet_area) {
        Arrival_planet_area = arrival_planet_area;
    }

    public String getDeparture_time() {
        return Departure_time;
    }

    public void setDeparture_time(String departure_time) {
        Departure_time = departure_time;
    }

    public String getArrival_time() {
        return Arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        Arrival_time = arrival_time;
    }

    public String getDeparture_date() {
        return Departure_date;
    }

    public void setDeparture_date(String departure_date) {
        Departure_date = departure_date;
    }

    public String getArrival_date() {
        return Arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        Arrival_date = arrival_date;
    }
}
