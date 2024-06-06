package ict.Servlet;

import ict.db.managerAccountDB;
import ict.db.memberAccountDB;
import ict.db.staffAccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sam
 */
@WebServlet(name = "memberSignup", urlPatterns = {"/MemberSignUpServlet"})
public class memberSignupController extends HttpServlet {

    private memberAccountDB memberDb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";
        memberDb = new memberAccountDB(dbUrl, dbUser, dbPassword);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

//        boolean ableEmail = memberDb.ableEmailSignUp(email);
        boolean isSucess = memberDb.addUserInfo(email, name, password);
        
        if (isSucess) {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('account was created!');");
            out.println("location='index.jsp';");
            out.println("</script>");

        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Email is already in use. Please try again.');");
            out.println("location='signUp.jsp';");
            out.println("</script>");
            return;
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
