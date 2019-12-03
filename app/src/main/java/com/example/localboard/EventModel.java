package com.example.localboard;

import java.io.Serializable;
import java.sql.Date;

public class EventModel implements Serializable {
    private int id;
    private String title;
    private String description;
    private String image;
    private String link;
    private String address;
    private int zip;
    private String time;
    private float price;
    private int popularity;

    EventModel(int id, String title, String image, String description) {this.id = id; this.title = title; this.image= image;
    this.description = description;}
    EventModel(){}
    EventModel(String description, String link) {
        this.description = description;
        this.link = link;
    }
    public int getId() {return id;}

    public String getTitle() {return title;}

    public String getDescription() {return description;}

    public String getImage() {return image;}

    public String getLink() {return link;}

    public String getAddress() {return address;}

    public int getZipCode() { return zip;}

    public String getTime() {return time;}

    public float getCost() {return price;}

    public int getPopularity() {return popularity;}



}
