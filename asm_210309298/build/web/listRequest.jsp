<%-- 
    Document   : listRequest
    Created on : 2023年4月26日, 下午5:50:29
    Author     : user
--%>

<%@page import="ict.bean.BookingRecordBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--        bs  -->

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="header.jsp" />
    <body>

        <table class="table table-hover mx-auto mt-3" style="width:80%;">

            <thead>
            <h1>Record</h1>
            <tr>
                <th scope="col">Bookingid</th>
                <th scope="col">Venueid</th>
                <th scope="col">Memberid</th>
                <th scope="col">Status</th>
                <th scope="col">TimeType</th>
                <th scope="col">BookingDate</th>
                <th>Detail</th>
            </tr>
        </thead>

        <tbody>

            <%

                ArrayList<BookingRecordBean> records = (ArrayList<BookingRecordBean>) request.getAttribute("records");
                for (int i = 0; i < records.size(); i++) {
                    BookingRecordBean v = records.get(i);

                    // check status = "approve"
                    if (v.getStatus().equals("request")) {

            %>

            <tr class="table-primary">
                <td><%= v.getBookingid()%></td>
                <td><%= v.getVenueid()%></td>
                <td><%= v.getMemberid()%></td>
                <td><%= v.getStatus()%></td>
                <td><%= v.getTimeType()%></td>
                <td><%= v.getBookingDate()%></td>
                <td><a href="${pageContext.request.contextPath}/RequestDetailServlet?id=<%= v.getBookingid()%>&venueid=<%= v.getVenueid()%>" class="btn btn-primary btn-sm">Detail</a></td> 
            </tr>

            <%}
                }%> 


    </table>


</body>
</html>
