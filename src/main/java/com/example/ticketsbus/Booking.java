package com.example.ticketsbus;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Booking {
    private final StringProperty name;
    private final StringProperty phone;
    private final StringProperty source;
    private final StringProperty destination;
    private final StringProperty service;
    private final StringProperty date;
    private final StringProperty seats;
    private final StringProperty amount;

    public Booking(String name, String phone, String source, String destination, String service, String date, String seats, String amount){
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.source = new SimpleStringProperty(source);
        this.destination = new SimpleStringProperty(destination);
        this.service = new SimpleStringProperty(service);
        this.date = new SimpleStringProperty(date);
        this.seats = new SimpleStringProperty(seats);
        this.amount = new SimpleStringProperty(amount);
    }
    public Booking build(){
        return this;
    }
    public StringProperty getName(){return name;}
    public StringProperty getPhone(){return phone;}
    public StringProperty getSource(){return source;}
    public StringProperty getDestination(){return destination;}
    public StringProperty getService(){return service;}
    public StringProperty getDate(){return date;}
    public StringProperty getSeats(){return seats;}
    public StringProperty getAmount(){return amount;}
}
