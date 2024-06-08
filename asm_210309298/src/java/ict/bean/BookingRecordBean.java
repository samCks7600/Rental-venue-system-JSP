/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author user
 */
public class BookingRecordBean implements Serializable {

    private int bookingid;
    private int venueid;
    private int memberid;
    private int invitationType;
    private String status;
    private int fee;
    private String invoice;
    private int staffid;
    private String desc;
    private String timeType;
    private String bookingDate;
    private VenueBean venue;
    private String checkInTime;
    private String checkOutTime;

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
//    public String getStatusType() {
//        LocalDate d = LocalDate.parse(due_date);
//        LocalDate n = LocalDate.now();
//        
//        if(n.isAfter(d)){
//            if(return_date==null){
//            return "is overdue";}
//        }
//
//        if(status==0){
//        return "on lease";
//        }else if(status==1){
//        return "completed ";
//        }
//        return "";
//    }
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public int getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(int invitationType) {
        this.invitationType = invitationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimeType() {
        return timeType;
    }

    public String getTime() {
        if (timeType == "1") {
            return "9:00 - 15:00";
        } else if (timeType == "2") {
            return "15:00 - 21:00";
        } else {
            return "9:00 - 21:00";
        }
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public VenueBean getVenue() {
        return venue;
    }

    public void setVenue(VenueBean venue) {
        this.venue = venue;
    }
}
