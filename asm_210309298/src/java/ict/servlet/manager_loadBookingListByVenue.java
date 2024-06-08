package ict.servlet;

import ict.bean.BookingBean;
import ict.bean.venueBean1;
import ict.db.bookingDB;
import ict.db.memberAccountDB;
import ict.db.staffAccountDB;
import ict.db.venueDB;
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
@WebServlet(name = "manager_loadBookingListByVenue", urlPatterns = {"/manager_loadBookingListByVenue"})
public class manager_loadBookingListByVenue extends HttpServlet {

    private venueDB venueDb;
    private bookingDB bookingDb;

    @Override
    public void init() {
        String dbUser = "root";;
        String dbPassword = "";
        String dbUrl = "jdbc:mysql://localhost:3306/epl_db";

        venueDb = new venueDB(dbUrl, dbUser, dbPassword);
        bookingDb = new bookingDB(dbUrl, dbUser, dbPassword);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String venueId = request.getParameter("venueId");
        venueBean1 venueInfo = venueDb.getVenueById(venueId);
        ArrayList<BookingBean> bookingList = bookingDb.getBookingsByVenueId(venueId);

        request.setAttribute("venueInfo", venueInfo);
        request.setAttribute("bookingList", bookingList);

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/manager_BookingListByVenue.jsp");

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
