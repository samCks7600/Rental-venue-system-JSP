/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

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
@WebServlet(name = "manager_Analytic_loadAttendanceRate", urlPatterns = {"/manager_Analytic_loadAttendanceRate"})
public class manager_Analytic_loadAttendanceRate extends HttpServlet {

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
        String memberId = "";
        memberId = request.getParameter("memberId");

        if (request.getParameter("selectedYear") == null) {

            Year thisYear = Year.now();
            year = String.valueOf(thisYear);

        } else {
            year = request.getParameter("selectedYear");
        }

        ArrayList<Double> attendanceRate = reportdb.AttendanceRate(year, memberId);
        ArrayList<Double> attendanceCount = reportdb.AttendanceCounts(year, memberId);
        double yearAttendanceRate = reportdb.YearAttendanceRate(year, memberId);
        int yearBookingCount = reportdb.YearBookingCount(year, memberId);

        request.setAttribute("memberId", memberId);
        request.setAttribute("attendanceRate", attendanceRate);
        request.setAttribute("attendanceCount", attendanceCount);
        request.setAttribute("yearAttendanceRate", yearAttendanceRate);
        request.setAttribute("yearBookingCount", yearBookingCount);

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/manager_Analytic_AttendanceRate.jsp");
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
