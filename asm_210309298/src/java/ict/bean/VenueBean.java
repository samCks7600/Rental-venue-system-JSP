/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class VenueBean implements Serializable {

    private int venueId;
    private String venueName;
    private String description;
    //miss image !!
    private String venueStatus;
    private String type;
    private String location;
    private int person_in_charge_id;
    private int bookfee;

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenueStatus() {
        return venueStatus;
    }

    public void setVenueStatus(String venueStatus) {
        this.venueStatus = venueStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPerson_in_charge_id() {
        return person_in_charge_id;
    }

    public void setPerson_in_charge_id(int person_in_charge_id) {
        this.person_in_charge_id = person_in_charge_id;
    }

    public int getBookfee() {
        return bookfee;
    }

    public void setBookfee(int bookfee) {
        this.bookfee = bookfee;
    }

    

}
