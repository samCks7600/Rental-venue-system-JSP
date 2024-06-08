<%-- 
    Document   : manager_BookingListByVenue
    Created on : 2023/4/27, 上午 08:48:01
    Author     : sam
--%>

<%@page import="ict.bean.BookingBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.venueBean1"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    
    <%
     venueBean1 venueInfo = (venueBean1)request.getAttribute("venueInfo");
    
    %>

    <head>
        <title>manager_venueManage</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <style>
        #navBar {
            background-color: rgb(0, 43, 46);
        }
    </style>

    <nav id="navBar" class="navbar navbar-expand-lg navbar-light">

        <div class="container ">
            <a class="navbar-brand text-light" href="#">Logo</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link text-light" aria-current="page" href="#Booking.jsp">Booking</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light" href="#">PayForBooking</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link text-light" href="#" tabindex="-1" aria-disabled="true">History</a>
                    </li>
                </ul>
                <a class="nav-link text-light">member , UserName </a>
                <form class="d-flex">
                    <button class="btn btn-outline-light" type="submit">Logout</button>
                </form>
            </div>
        </div>
    </nav>

    <body>

        <div class="container border border-1 p-3 shadow">

            <h1 class="text-center"> Venue Name : <%=venueInfo.getVenueName()%> </h1>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">MemberId</th>
                        <th scope="col">InvitationType</th>
                        <th scope="col">Status</th>
                        <th scope="col">Fee</th>
                        <th scope="col">HoldByStaffId</th>
                        <th scope="col">TimeType</th>
                        <th scope="col">BookingDate</th>
                        <th scope="col">Check-In/Out</th>
                        <th scope="col">Details</th>
                        
                    </tr>
                </thead>
                <tbody>

                    <%
                        ArrayList<BookingBean> bookings = (ArrayList<BookingBean>) request.getAttribute("bookingList");
                        for (BookingBean booking : bookings) {
                    %>
                    <tr>
                        <td><%=booking.getBookingid() %></td>
                        <td><%=booking.getMemberid() %></td>
                        <td><%=booking.getInvitationType() %></td>
                        <td><%=booking.getStatus() %></td>
                        <td><%=booking.getFee() %></td>
                        <td><%=booking.getStaffid() %></td>
                        <td><%=booking.getTime() %></td>
                        <td><%=booking.getTime() %></td>
                        <td><%=booking.getResultCheckInOut() %></td>
                        <td>
                            <form  method='post' action='manager_BookingListByVenue_LoadDetails'>
                                <input class='btn btn-danger' name='bookingId' type='hidden' value='<%=booking.getBookingid() %>'>
                                <input class='btn btn-warning' type='submit' value='View Details'>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

    </body>

    <script>

    </script>

</html>

