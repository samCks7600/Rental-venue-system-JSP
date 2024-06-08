/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.db.AsmDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 *
 * @author user
 */
@WebServlet(name = "UpdateBookingStatusServlet", urlPatterns = {"/UpdateBookingStatusServlet"})
public class UpdateBookingStatusServlet extends HttpServlet {

    private AsmDB db;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        dbUrl += "?autoReconnect=true&useSSL=false";
        db = new AsmDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        int bookingid = Integer.parseInt(request.getParameter("bookingid"));
//        double fee = Integer.parseInt(request.getParameter("fee"));
//        int staffid = Integer.parseInt(request.getParameter("staffid"));
        //format type
        String formatRadio = request.getParameter("formatRadio");
        //guest info

        String[] guestNames = request.getParameterValues("guestName[]");
        String[] guestEmails = request.getParameterValues("guestEmail[]");
 
        String action = request.getParameter("action");

        // update booking table status
        if ("payment".equals(action)) {
            int bookingid = Integer.parseInt(request.getParameter("bookingid"));
            double fee = Integer.parseInt(request.getParameter("fee"));
            int staffid = Integer.parseInt(request.getParameter("staffid"));
            //update status towaiting payment
            db.updateBookingStatus(bookingid, "waitingPayment", fee, staffid);

        } else if ("reject".equals(action)) {
            //rejected record
            int bookingid = Integer.parseInt(request.getParameter("bookingid"));
            double fee = Integer.parseInt(request.getParameter("fee"));
            int staffid = Integer.parseInt(request.getParameter("staffid"));
            db.updateBookingStatus(bookingid, "reject", fee, staffid);
        } else if ("proof".equals(action)) {
            int bookingid = Integer.parseInt(request.getParameter("bookingid"));
            int formatType = Integer.parseInt(request.getParameter("formatRadio"));

            //add guest record
            db.saveGuests(bookingid, guestNames, guestEmails);
            //update booking type
            db.updateBookingType(bookingid, formatType);
            //upload payment
            db.savePaymentProofToDB(bookingid);
            //update status
            db.updatePaymentStatus(bookingid, "paid");
        }else if ("approve".equals(action)){
            int bookingid = Integer.parseInt(request.getParameter("bookingid"));
            //update status
            db.updatePaymentStatus(bookingid, "approve");
        }else if ("checkin".equals(action)){
            int bookingid = Integer.parseInt(request.getParameter("bookingid"));
           
            String checkInTime = request.getParameter("checkInTime");
            //update status
            db.updateCheckIn(bookingid, checkInTime);
        }else if ("checkout".equals(action)){
            int bookingid = Integer.parseInt(request.getParameter("bookingid"));
            String desc = request.getParameter("desc");
            String checkOutTime = request.getParameter("checkOutTime");
            //update status
            db.updateCheckOut(bookingid, checkOutTime);
            db.updateDescOut(bookingid, desc);
        }
        
        String url = "/menu.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
