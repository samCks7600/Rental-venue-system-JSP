<%-- 
    Document   : listGuests
    Created on : 2023年4月13日, 下午4:22:20
    Author     : user
--%>

<%@page import="ict.bean.GuestBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<GuestBean> guests = (ArrayList<GuestBean>) request.getAttribute("guests");
            out.println("<h1>Guest</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>GuestId</th><th>name</th><th>Email</th><th>Approved_by</th>");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < guests.size(); i++) {
                GuestBean v = guests.get(i);
                out.println("<tr>");

                out.println("<td>" + v.getId() + "</td>");
                out.println("<td>" + v.getName() + "</td>");
                out.println("<td>" + v.getEmail() + "</td>");
                out.println("<td>" + v.getApproved_by() + "</td>");
                //detail

                
                out.println("</tr>");

            }
            out.println("</table>");
        %>  
    </body>
</html>
