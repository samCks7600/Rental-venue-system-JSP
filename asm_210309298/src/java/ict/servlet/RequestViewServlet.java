/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ict.servlet;

import ict.bean.BookingRecordBean;
import ict.bean.VenueBean;
import ict.db.AsmDB;
import java.io.IOException;
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
@WebServlet(name = "RequestViewServlet", urlPatterns = {"/RequestViewServlet"})
public class RequestViewServlet extends HttpServlet {

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
        // get staffid
        //String StaffId = "123";//change able use session login.
        String StaffId = (String) request.getSession().getAttribute("Id");

// 查询员工负责的场馆ID列表
        ArrayList<Integer> venueIds = db.queryVenueIdsByStaffId(StaffId);

// 查询所有相关的记录
        ArrayList<BookingRecordBean> allRecords = new ArrayList<>();
// 遍历场馆ID列表，查询每一个场馆的相关记录，并将结果添加到总记录列表中
        for (int venueId : venueIds) {
            ArrayList<BookingRecordBean> records = db.queryRecordsByVenueIds(venueId);
            allRecords.addAll(records);
        }
// 将查询结果设置为请求属性，以便在JSP页面中使用
        request.setAttribute("records", allRecords);

// 转发到JSP页面进行渲染
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/listRequest.jsp");
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
