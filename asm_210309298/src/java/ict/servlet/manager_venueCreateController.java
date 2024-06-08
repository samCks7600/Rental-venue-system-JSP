package ict.servlet;

import ict.db.venueDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "manager_venueCreateController", urlPatterns = {"/manager_venueCreateController"})
public class manager_venueCreateController extends HttpServlet {
    
    private venueDB venueDb;
   
    @Override 
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";
        venueDb = new venueDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String venueName = request.getParameter("venueName");
        String description = request.getParameter("description");
        String venueState = request.getParameter("venueState");
        String type = request.getParameter("type");
        String location = request.getParameter("location");
        String staffInCharge = request.getParameter("staffInCharge");
        String bookingFee = request.getParameter("bookingFee");
        
        
       boolean isSucess = venueDb.addvenueInfo(description,venueName,venueState,type,location,staffInCharge,bookingFee);
                
        if (!isSucess) {
            
            request.setAttribute("ErrorMessage", "Servlet: manager_venueCreateController error ");
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manage_venueTable");
            rd.forward(request, response);

        } else {

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manage_venueTable");
            rd.forward(request, response);
        }
                        
                        
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
