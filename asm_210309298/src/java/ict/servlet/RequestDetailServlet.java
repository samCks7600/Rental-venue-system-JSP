/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.BookingRecordBean;
import ict.bean.VenueBean;
import ict.db.AsmDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "RequestDetailServlet", urlPatterns = {"/RequestDetailServlet"})
public class RequestDetailServlet extends HttpServlet {

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
        String id = request.getParameter("id");
        String venueid = request.getParameter("venueid");

        
        id = request.getParameter("id");
        

        BookingRecordBean record = db.queryRecordByID(Integer.parseInt(id));
        ArrayList<BookingRecordBean> records = db.queryRequest();
        VenueBean venue = db.queryVenueByID(Integer.parseInt(venueid));

        request.setAttribute("v", record);
        request.setAttribute("records", records);
        request.setAttribute("v1", venue);
        //return vaild/invaild request.
        BookingRecordBean v = (BookingRecordBean) request.getAttribute("v");
        String selectedDate = v.getBookingDate();
        String selectedTime = v.getTimeType();
        String selectedVenue = Integer.toString(v.getVenueid());
        boolean requestValid = db.isRequestValid(selectedDate, selectedTime, selectedVenue);
        request.setAttribute("requestValid", requestValid);
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/recordDetail.jsp");
        rd.forward(request, response);
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
