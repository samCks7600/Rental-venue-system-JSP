/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.Bean.guestList;

/**
 *
 * @author sam
 */
public class guest {

    private String name;
    private int phoneNum;

    public guest() {
    }

    public guest(String name, int phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }
    
    public void setName(String name){
        this.name = name ;
    }
    
    public void setPhoneNum(int phoneNum){
        this.phoneNum = phoneNum;
    }
    
    public String listGuest(){
        return "";   
    }

}
