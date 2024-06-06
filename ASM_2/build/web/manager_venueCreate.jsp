<%-- 
    Document   : manager_venueCreate
    Created on : 2023年4月24日, 上午03:20:14
    Author     : sam
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.Bean.staffBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>How To Create</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>


    <%

        ArrayList<staffBean> staffList = (ArrayList<staffBean>) request.getAttribute("staffList");

    %>




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
            <form action="manager_venueCreateController" method="post">
                <div class="mb-3 text-center">
                    <h2>Create Venue Information</h2>
                </div>

                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Venue Name</label>
                    <input type="text" name="venueName" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Description</label>
                    <input type="text" name="description" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Venue State</label>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" value="able" name="venueState" checked>
                        <label class="form-check-label">able</label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" value="disable" name="venueState">
                        <label class="form-check-label">disable</label>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Type</label>
                    <input type="text" name="type" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Location</label>
                    <input type="text" name="location" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Staff In Charge</label>
                    <select class="form-select" name="staffInCharge" required>
                        <option selected disabled value="">choose a staff</option> 
                        <%                            
                            for (staffBean staff : staffList) {
                        %>
                        <option value="<%=staff.getStaffId()  %>" >ID : <%=staff.getStaffId()  %>  ,Name:<%=staff.getUsername()  %>    </option>
                        <%                            
                            }
                        %>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Booking Fee</label>
                    <input type="number" min="0" name="bookingFee" class="form-control" required>
                </div>





                <div class="d-flex justify-content-around">
                    <button type="submit" class="btn btn-warning">Create Venue Information</button>
                    <a class="btn btn-secondary" href="manage_venueTable">Go Back Venue Table</a>
                </div>
            </form>
        </div>

    </body>

    <script>
    </script>

</html>