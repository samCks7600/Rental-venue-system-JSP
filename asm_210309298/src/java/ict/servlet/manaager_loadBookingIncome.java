/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.Servlet;

import ict.db.reportDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
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
@WebServlet(name = "manaager_loadBookingIncome", urlPatterns = {"/manaager_loadBookingIncome"})
public class manaager_loadBookingIncome extends HttpServlet {

    private reportDB reportdb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";
        reportdb = new reportDB(dbUrl, dbUser, dbPassword);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String year = "";
        String venueId = "";
        venueId = request.getParameter("venueId");

        if (request.getParameter("selectedYear") == null) {

            Year thisYear = Year.now();
            year = String.valueOf(thisYear);

        } else {
            year = request.getParameter("selectedYear");

        }

       double yearIncomeResult = reportdb.yearIncomeResults(year, venueId);
        ArrayList<Double> incomeResults = reportdb.IncomeResults(year, venueId);
        request.setAttribute("venueId", venueId);
        request.setAttribute("yearIncomeResult", yearIncomeResult);
        request.setAttribute("incomeResult", incomeResults);
        request.setAttribute("selectedYear", String.valueOf(year));

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/manaager_BookingIncome.jsp");

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
