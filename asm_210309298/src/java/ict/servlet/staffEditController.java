/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.staffBean;
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
@WebServlet(name = "staffEditController", urlPatterns = {"/staffEditController"})
public class staffEditController extends HttpServlet {

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

        String id = request.getParameter("id");
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        staffBean staff =  staffDb.getStaffById(id);
        
        boolean isSucess =false ;
        if(email.equals(staff.getEmail())){
            isSucess = true;
        }else{
            isSucess = staffDb.ableEmailSignUp(email);
        }
        

        if (isSucess) {

            staffDb.EditStaffInfo(id, name, email, password);
            request.setAttribute("EditMessage", "the staff information was edited , staff id : " + id);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manager_staffTableServlet");
            rd.forward(request, response);

        } else {

            staffBean staffInfo = staffDb.getStaffById(id);
            request.setAttribute("staffInfo", staffInfo);
            request.setAttribute("ErrorMessage", "try again , The Email was already exist : " + email);
            
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/manager_StaffEditPage");
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
