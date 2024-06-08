<%-- 
    Document   : s_ApprovePaidBooking
    Created on : 2023年4月27日, 下午8:51:56
    Author     : user
--%>

<%@page import="ict.bean.BookingRecordBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="header.jsp" />
    <jsp:useBean id="booking" scope="request" class="ict.bean.BookingRecordBean" />
    <jsp:useBean id="receipt" scope="request" class="ict.bean.ReceiptBean" />


    <body>
        <button id="upload" data-bookingid="${booking.bookingid}" type="button" class="btn btn-primary" data-toggle="modal" data-target="#uploadModal">Approve Booking</button>

        <h1>Booking Details:</h1>
        <table  class="table table-hover mx-auto mt-3" style="width:80%;">
            <tr>
                <td>Booking ID:</td>
                <td><%=booking.getBookingid()%></td>
            </tr>
            <tr>
                <td>Venue ID:</td>
                <td><%=booking.getVenueid()%></td>
            </tr>
            <tr>
                <td>Status:</td>
                <td><%=booking.getStatus()%></td>
            </tr>
            <!-- 其他欄位 -->
        </table>
        <h1>Receipt Details:</h1>
        <table  class="table table-hover mx-auto mt-3" style="width:80%;">
            <tr>
                <td>Receipt ID:</td>
                <td><%=receipt.getReceiptid()%></td>
            </tr>
            <tr>
                <td> File Name:</td>
                <td><%=receipt.getFilename()%></td>
            </tr>
            <tr>
                <td>Uploaded At</td>
                <td><%=receipt.getUploadedAt()%></td>
            </tr>

            <!-- 其他欄位 -->
        </table>
        <table  class="table table-hover mx-auto mt-3" style="width:80%;">
            <thead>
                <tr>
                    <th>Number of Guests</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="guest" items="${guestLists}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td> <!-- loop counter -->
                        <td>${guest.name}</td>
                        <td>${guest.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--modal start-->
        <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h6 class="modal-title" id="uploadModalLabel">Update Booking Status And Sending Email To Guests</h6>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                    </div>
                    <div class="modal-body">
                        <form  enctype="multipart/form-data" method=“get" action="UpdateBookingStatusServlet">
                            <%
                                if (booking.getInvitationType() == 1) {
                                    //casual type
%>
                            <div>Dear [Guest],</div>
                            <div>We cordially invite you to attend our event. This event will be held on <%=booking.getBookingDate()%> at [Location]. We look forward to your visit.</div>
                            <div>Sincerely,</div>
                            
                            <div>salute</div>
                            <div><%= booking.getMemberid()%></div>

                            
                            <%
                                } else {
                            %>
                            <div>Dear [Guest Name]:</div>
                            <div>It's our pleasure to invite you to our [event type] event. This event will be held on [date] at [venue location]. Hope you will be there and have a great time together.</div>
                            <div>Sincerely,</div>
                            <div>salute</div>
                            <div>[name of inviter]</div>
                            <%
                                }
                            %>
                            <input type="hidden" class="form-control" name="bookingid" value="<%= booking.getBookingid()%>" required>
                            <div class="modal-footer">
                                <button type="submit" name="action" value="approve"class="btn btn-primary" id="submitPayment">Submit</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <form method="post" action="approveBooking">
            <input type="hidden" name="bookingId" value="${booking.bookingid}">
            <button type="submit" class="btn btn-primary">Approve</button>
        </form>
    </body>
</html>
