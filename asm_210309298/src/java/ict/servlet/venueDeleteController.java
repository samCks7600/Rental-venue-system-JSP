/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author sam
 */
@WebServlet(name = "venueDeleteController", urlPatterns = {"/venueDeleteController"})
public class venueDeleteController extends HttpServlet {

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

        String venueId = request.getParameter("id");
        boolean isSucess = venueDb.delVenueInfo(venueId);

        if (isSucess) {

            request.setAttribute("deleteMessage", "you already deleted venueId :" + venueId);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manage_venueTable");
            rd.forward(request, response);

        }else{
            PrintWriter out = response.getWriter();
            out.println("venueDeleteController : deleteError");
            
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

}