/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.BookingBean;
import ict.bean.BookingGuestBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sam
 */
public class bookingDB {

    private String dburl = "";
    private String dbuser = "";
    private String dbpassword = "";

    public bookingDB(String dburl, String dbuser, String dbpassword) {
        this.dburl = dburl;
        this.dbuser = dbuser;
        this.dbpassword = dbpassword;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(dburl, dbuser, dbpassword);
    }

    public BookingBean getBookingById(String bookingId) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        BookingBean booking = null;

        String venueId = "";
        String memberId = "";
        String invitationType = "";
        String status = "";
        String fee = "";
        String invoice = "";
        String staffId = "";
        String desc = "";
        String timeType = "";
        String bookingDate = "";
        String checkInOut = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM booking WHERE bookingId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, bookingId);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {

                venueId = rs.getString("venueId");
                memberId = rs.getString("memberId");
                invitationType = rs.getString("invitationType");
                status = rs.getString("status");
                fee = rs.getString("fee");
                invoice = (rs.getString("invoice")!=null) ? rs.getString("invoice") : "null";
                staffId = rs.getString("staffId");
                checkInOut = rs.getString("checkInOut");
                desc = rs.getString("description");
                timeType = rs.getString("TimeType");
                bookingDate = (rs.getString("bookingDate")!=null) ? rs.getString("bookingDate") : "null";
                

                booking = new BookingBean(
                        bookingId,
                        venueId,
                        memberId,
                        invitationType,
                        status,
                        fee,
                        invoice,
                        staffId,
                        desc,
                        timeType,
                        bookingDate,
                        checkInOut
                );

            } else {
                return null;
            }

            pStatement.close();
            connection.close();

        } catch (SQLException ex) {

            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return booking;
    }

    public ArrayList<BookingGuestBean> getBookingGuestById(String BookingId) throws IOException {

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<BookingGuestBean> guests = new ArrayList<>();

        try {
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM booking_guest Where bookingId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, BookingId);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("bookingId");
                String guestName = rs.getString("guestName");
                String email = rs.getString("email");

                guests.add(new BookingGuestBean(id, guestName, email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;

    }

    public ArrayList<BookingBean> getBookingsByVenueId(String venueId) throws IOException {

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<BookingBean> bookings = new ArrayList<>();

        String bookingId = "";
        String memberId = "";
        String invitationType = "";
        String status = "";
        String fee = "";
        String invoice = "";
        String staffId = "";
        String desc = "";
        String timeType = "";
        String bookingDate = "";
        String checkInOut = "";

        try {
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM booking Where venueId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, venueId);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                bookingId = rs.getString("bookingId");
                memberId = rs.getString("memberId");
                invitationType = rs.getString("invitationType");
                status = rs.getString("status");
                fee = rs.getString("fee");
                invoice = rs.getString("invoice");
                staffId = rs.getString("staffId");
                desc = rs.getString("description");
                timeType = rs.getString("TimeType");
                bookingDate = rs.getString("bookingDate");
                checkInOut =rs.getString("checkInOut") ;

                bookings.add(
                        new BookingBean(
                                bookingId,
                                venueId,
                                memberId,
                                invitationType,
                                status,
                                fee,
                                invoice,
                                staffId,
                                desc,
                                timeType,
                                bookingDate,
                                checkInOut
                        )
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  bookings;

    }

    public BookingBean getBookingByVenueId(String venueId) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        BookingBean booking = null;

        String bookingId = "";
        String memberId = "";
        String invitationType = "";
        String status = "";
        String fee = "";
        String invoice = "";
        String staffId = "";
        String desc = "";
        String timeType = "";
        String bookingDate = "";
        String checkInOut = "";

        try {

            connection = getConnection();
            String preQueryStatement
                    = "SELECT * FROM booking WHERE venueId = ?";
            pStatement = connection.prepareStatement(preQueryStatement);
            pStatement.setString(1, venueId);
            ResultSet rs = null;
            rs = pStatement.executeQuery();
            if (rs.next()) {

                bookingId = rs.getString("bookingId");
                memberId = rs.getString("memberId");
                invitationType = rs.getString("invitationType");
                status = rs.getString("status");
                fee = rs.getString("fee");
                invoice = rs.getString("invoice");
                staffId = rs.getString("staffId");
                desc = rs.getString("description");
                timeType = rs.getString("TimeType");
                bookingDate = rs.getString("bookingDate");
                checkInOut = rs.getString("checkInOut");

                booking = new BookingBean(
                        bookingId,
                        venueId,
                        memberId,
                        invitationType,
                        status,
                        fee,
                        invoice,
                        staffId,
                        desc,
                        timeType,
                        bookingDate,
                        checkInOut
                );

            } else {
                return null;
            }

            pStatement.close();
            connection.close();

        } catch (SQLException ex) {

            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return booking;
    }

}
