/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.Servlet;

import ict.Bean.staffBean;
import ict.Bean.venueBean;
import ict.db.staffAccountDB;
import ict.db.venueDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "manager_loadvenueEditPage", urlPatterns = {"/manager_loadVenueEditPage"})
public class manager_loadVenueEditPage extends HttpServlet {

    private venueDB venueDb;
    private staffAccountDB staffDb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";
        venueDb = new venueDB(dbUrl, dbUser, dbPassword);
        staffDb = new staffAccountDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String venueId = request.getParameter("id");
        venueBean venueInfo = venueDb.getVenueById(venueId);

        request.setAttribute("venueInfo", venueInfo);

        ArrayList<staffBean> staffs = staffDb.getAllStaff();
        
        request.setAttribute("staffList", staffs);

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/manager_venueEdit.jsp");

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
