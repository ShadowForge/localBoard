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
    private int zipCode;
    private Date time;
    private float cost;
    private int popularity;

    EventModel(int id, String title, String image, String description) {this.id = id; this.title = title; this.image= image;
    this.description = description;}
    EventModel(){}

    public int getId() {return id;}

    public String getTitle() {return title;}

    public String getDescription() {return description;}

    public String getImage() {return image;}

    public String getLink() {return link;}

    public String getAddress() {return address;}

    public int getZipCode() { return zipCode;}

    public Date getTime() {return time;}

    public float getCost() {return cost;}

    public int getPopularity() {return popularity;}



}
