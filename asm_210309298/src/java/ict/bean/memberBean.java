/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author sam
 */
public class memberBean {

    private String memberId;
    private String name;
    private String email;
    private String password;
    private String availableBooking;
    
    public memberBean() {
    }

    public memberBean(String memberId ,String email, String name, String password,String availableBooking) {
        this.memberId = memberId ;
        this .email = email ;
        this.name = name;
        this.password = password;
        this.availableBooking = availableBooking;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String MemberId) {
        this.memberId = MemberId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setUsername(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
        public String getAvailableBooking() {
        return availableBooking;
    }
    
    public void setAvailableBooking(String availableBooking) {
        this.availableBooking =  availableBooking;
    }
    
}
