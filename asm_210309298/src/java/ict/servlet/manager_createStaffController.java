/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.Bean.staffBean;
import ict.db.staffAccountDB;
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
@WebServlet(name = "manager_createStaffController", urlPatterns = {"/manager_createStaffController"})
public class manager_createStaffController extends HttpServlet {

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

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isSucess = staffDb.addStaffInfo(email, username, password);

        if (isSucess) {

            request.setAttribute("CreateMessage", "the staff information was created .");
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manager_staffTableServlet");
            rd.forward(request, response);

        } else {

            request.setAttribute("ErrorMessage", "try again , The Email was already exist : " + email);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manager_staffCreate.jsp");
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
