/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.staffAccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
@WebServlet(name = "StaffDeleteController", urlPatterns = {"/StaffDeleteController"})
public class StaffDeleteController extends HttpServlet {

    private staffAccountDB staffDb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";
        staffDb = new staffAccountDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String staffId = request.getParameter("id");
        boolean isSucess = staffDb.delStaffInfo(staffId);

        if (isSucess) {

            request.setAttribute("deleteMessage", "you already deleted staffId :" + staffId);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manager_staffTableServlet");
            rd.forward(request, response);

        }else{
            PrintWriter out = response.getWriter();
            out.println("deleteError");
            
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
