package ict.bean;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sam
 */
public class BookingBean implements Serializable {

    private String bookingid;
    private String venueid;
    private String memberid;
    private String invitationType;
    private String status;
    private String fee;
    private String invoice;
    private String staffid;
    private String desc;
    private String timeType;
    private String bookingDate;
    private String checkInOut;

    public BookingBean() {
    }

    public BookingBean(
            String bookingid,
            String venueid,
            String memberid,
            String invitationType,
            String status,
            String fee,
            String invoice,
            String staffid,
            String desc,
            String timeType,
            String bookingDate,
            String checkInOut) {

        this.bookingid = bookingid;
        this.venueid = venueid;
        this.memberid = memberid;
        this.invitationType = invitationType;
        this.status = status;
        this.fee = fee;
        this.invoice = invoice;
        this.staffid = staffid;
        this.desc = desc;
        this.timeType = timeType;
        this.bookingDate = bookingDate;
        this.checkInOut = checkInOut;

    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(String invitationType) {
        this.invitationType = invitationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
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
        if (null == timeType) {
            return null;
        } else {
            switch (timeType) {
                case "1":
                    return "9:00 - 15:00";
                case "2":
                    return "15:00 - 21:00";
                default:
                    return "9:00 - 21:00";
            }
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

    public String getCheckInOut() {

        return checkInOut;

    }

    public String getResultCheckInOut() {
        if (checkInOut == null) {
            return "ever checkIn";
        } else {
            return checkInOut;
        }

    }

    public void setCheckInOut(String checkInOut) {
        this.checkInOut = checkInOut;
    }

}
