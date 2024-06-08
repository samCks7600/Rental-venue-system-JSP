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
@WebServlet(name = "StaffEditPage", urlPatterns = {"/manager_StaffEditPage"})
public class manager_StaffEditPage extends HttpServlet {

    private staffAccountDB staffDb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";
        staffDb = new staffAccountDB(dbUrl, dbUser, dbPassword);
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ErrorMessage = (String) request.getAttribute("ErrorMessage");
        
        String staffId = request.getParameter("id");
        staffBean staffInfo = staffDb.getStaffById(staffId);
   
        request.setAttribute("staffInfo",staffInfo);
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/manager_staffEdit.jsp");
        
        rd.forward(request, response);
    }
}
