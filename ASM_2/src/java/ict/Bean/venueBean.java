/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.Bean;

/**
 *
 * @author sam
 */
public class venueBean {

    private String venueId;
    private String description;
    private String venueName;
    private String venueStatus;
    private String type;
    private String location;
    private String personInChargeId;
    private String bookFee;

    public venueBean() {

    }

    public venueBean(String venueId, String description, String venueName, String venueStatus,
            String type, String location, String personInChargeId, String bookFee) {
        this.venueId = venueId;
        this.description = description;
        this.venueName = venueName;
        this.venueStatus = venueStatus;
        this.type = type;
        this.location = location;
        this.personInChargeId = personInChargeId;
        this.bookFee = bookFee;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getDescription() {
        return description;
    } 

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
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

    public String getPersonInChargeId() {
        return personInChargeId;
    }

    public void setPersonInChargeId(String personInChargeId) {
        this.personInChargeId = personInChargeId;
    }

    public String getBookFee() {
        return bookFee;
    }

    public void setBookFee(String bookFee) {
        this.bookFee = bookFee;
    }

}
