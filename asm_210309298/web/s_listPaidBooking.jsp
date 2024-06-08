<%-- 
    Document   : s_listPaidBooking
    Created on : 2023年4月27日, 下午8:07:17
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <table class="table table-hover mx-auto mt-3" style="width:80%;" id="bookingTable">
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Venue ID</th>
                    <th>Order Processing Staff</th>
                    <th>Booking date</th>
                    <th>Booking Time</th>
                    <th>Status</th>
                    <th>Fee</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td>${booking.bookingid}</td>
                        <td>${booking.venue.venueName}</td>
                        <td>${booking.staffid}</td>
                        <td>${booking.bookingDate}</td>
                        <td>${booking.getTime()}</td>
                        <td>${booking.status}</td>
                        <td>${booking.fee}</td>
                        <td>
                            <c:if test="${booking.status == 'paid'}">
                                <form method="get" action="s_ViewPaidBookingDetail">
                                    <input type="hidden" name="bookingId" value="${booking.bookingid}">
                                    <button type="submit">Approve</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
