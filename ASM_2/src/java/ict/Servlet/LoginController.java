package ict.Servlet;

import ict.Servlet.DBConnectionManager;
import ict.db.managerAccountDB;
import ict.db.memberAccountDB;
import ict.db.staffAccountDB;
import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private memberAccountDB memberDb;
    private staffAccountDB staffDb;
    private managerAccountDB managerDb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";

        memberDb = new memberAccountDB(dbUrl, dbUser, dbPassword);
        staffDb = new staffAccountDB(dbUrl, dbUser, dbPassword);
        managerDb = new managerAccountDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (role.equals("member")) {
            boolean validUser = memberDb.isValidUser(email, password);

            if (validUser) {

                String SetName = memberDb.getUsernameByEmail(email);
                String SetMemberId  = memberDb.getMemberIdByEmail(email);
                HttpSession session = request.getSession();

                session.setAttribute("userPosition", "member");
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", SetName);
                session.setAttribute("memberId", SetMemberId);
                
                out.println("ok");
                out.println(SetName);
                out.println(email);
                response.sendRedirect("memberHome.jsp");

            } else {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid username or password. Please try again.');");
                out.println("location='index.jsp';");
                out.println("</script>");

            }

        }

        if (role.equals("staff")) {

            boolean validStaff = staffDb.isValidUser(email, password);

            if (validStaff) {
                String SetName = staffDb.getUsernameByEmail(email);
                String SetStaffId = staffDb.getStaffIdByEmail(email);
                
                HttpSession session = request.getSession();

                
                session.setAttribute("userPosition", "staff");
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", SetName);
                session.setAttribute("staffId", SetStaffId);
                

                out.println("ok");
                out.println(SetName);
                out.println(email);
            

                response.sendRedirect("staffHome.jsp");
            } else {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid username or password. Please try again.');");
                out.println("location='index.jsp';");
                out.println("</script>");
            }

        }

        if (role.equals("manager")) {

            boolean validManager= managerDb.isValidUser(email, password);

            if (validManager) {

                String SetName = managerDb.getUsernameByEmail(email);
                String SetManagerId  = managerDb.getManagerIdByEmail(email);
                HttpSession session = request.getSession();

                session.setAttribute("userPosition", "manager");
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", SetName);
                session.setAttribute("managerId", SetManagerId);
                response.sendRedirect("managerHome.jsp");

            } else {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid username or password. Please try again.');");
                out.println("location='index.jsp';");
                out.println("</script>");

            }

        }
    }
}
