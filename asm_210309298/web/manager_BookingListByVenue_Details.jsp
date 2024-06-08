<%-- 
    Document   : manager_BookingListByVenue_Details
    Created on : 2023/4/27, 下午 12:41:09
    Author     : sam
--%>

<%@page import="ict.bean.staffBean"%>
<%@page import="ict.bean.venueBean1"%>
<%@page import="ict.bean.memberBean"%>
<%@page import="ict.bean.BookingGuestBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.BookingBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%

        BookingBean booking
                = (BookingBean) request.getAttribute("bookingInfo");

        ArrayList<BookingGuestBean> guests
                = (ArrayList<BookingGuestBean>) request.getAttribute("bookingGuestsInfo");

        memberBean member
                = (memberBean) request.getAttribute("MemberInfo");

        venueBean1 venue
                = (venueBean1) request.getAttribute("venueInfo");

        staffBean staff
                = (staffBean) request.getAttribute("staffInfo");

    %>

    <head>
        <title>How To Create</title>
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

            <div class="mb-3 text-center">
                <h2>View Booking Record by venue</h2>
            </div>

            <div class="row">
                <div class="col m-2">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Booking ID</label>
                        <input type="text" value="<%=booking.getBookingid()%>" readonly class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">member Name</label>
                        <input type="text" value="<%=member.getName()%>" class="form-control" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">member Email</label>
                        <input type="email" value="<%=member.getEmail()%>" class="form-control" readonly>

                    </div>

                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">venue ID</label>
                        <input type="text" value="<%=venue.getVenueId()%>" class="form-control" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">venue Name</label>
                        <input type="text" value="<%=venue.getVenueName()%>" class="form-control" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">description</label>
                        <input type="text" value="<%=booking.getDesc()%>" class="form-control" readonly>
                    </div>
                </div>

                <div class="col m-2">
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">invitation Type</label>
                        <input type="text" value="<%=booking.getTime()%>" class="form-control" readonly>
                    </div>



                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Status</label>
                        <input type="text" value="<%=booking.getStatus()%>" class="form-control" readonly>
                    </div>


                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Fee</label>
                        <input type="text" value="<%=booking.getFee()%>" class="form-control" readonly>
                    </div>


                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Handle By Staff</label>
                        <input type="text" value="Name :<%=staff.getUsername()%> , ID : <%=staff.getStaffId()%> , Email : <%=staff.getEmail()%>" class="form-control" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">booking Date</label>
                        <input type="text" value="<%=booking.getBookingDate()%>" class="form-control" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Check In or out</label>
                        <input type="text" value="<%=booking.getCheckInOut()%>" class="form-control" readonly>
                    </div>

                </div>
            </div>

            <h5 class="text-center">Guest List</h5>




            <div class="row justify-content-center">
                <div class="col-8">
                    <table class="table">
                        <thead class="table-dark">
                            <tr>
                                <th>email</th>
                                <th>guest Name</th>

                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (BookingGuestBean guest : guests) {
                            %>
                            <tr>
                                <td><%=guest.getEmail()%></th>
                                <td><%=guest.getGuestName()%></th>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                    <%
                        if (guests == null) {
                    %>

                    have not guests found !
                    <%
                        }
                    %>
                </div>
            </div>

            <div class="d-flex justify-content-around">
                <form  method='post' action='manager_loadBookingListByVenue'>
                    <input class='btn btn-danger' name='venueId' type='hidden' value='<%=venue.getVenueId()%>'>
                    <input type="submit" name="submit" class='btn btn-warning'  value="Go Back BookingList Table"   >
                </form>
            </div>

        </div>

    </body>


</html>
