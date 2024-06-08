/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.BookingBean;
import ict.bean.BookingGuestBean;
import ict.bean.memberBean;
import ict.bean.staffBean;
import ict.bean.venueBean1;
import ict.db.bookingDB;
import ict.db.memberAccountDB;
import ict.db.staffAccountDB;
import ict.db.venueDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sam
 */
@WebServlet(name = "manager_BookingListByVenue_LoadDetails", urlPatterns = {"/manager_BookingListByVenue_LoadDetails"})
public class manager_BookingListByVenue_LoadDetails extends HttpServlet {

    private venueDB venueDb;
    private staffAccountDB staffDb;
    private memberAccountDB memberDb;
    private bookingDB bookingDb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";
        venueDb = new venueDB(dbUrl, dbUser, dbPassword);
        staffDb = new staffAccountDB(dbUrl, dbUser, dbPassword);
        memberDb = new memberAccountDB(dbUrl, dbUser, dbPassword);
        bookingDb = new bookingDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement pStatement = null;

        String bookingId = request.getParameter("bookingId");
        BookingBean booking = bookingDb.getBookingById(bookingId);
        ArrayList<BookingGuestBean> guests = bookingDb.getBookingGuestById(bookingId);
        memberBean member = memberDb.getMemberById(booking.getMemberid());
        venueBean1 venue = venueDb.getVenueById(booking.getVenueid());
        staffBean staff = staffDb.getStaffById(booking.getStaffid());

        request.setAttribute("bookingInfo", booking);
        request.setAttribute("bookingGuestsInfo", guests);
        request.setAttribute("MemberInfo", member);
        request.setAttribute("venueInfo", venue);
        request.setAttribute("staffInfo", staff);

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/manager_BookingListByVenue_Details.jsp");

        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
