<%-- 
    Document   : manager_venueManage
    Created on : 2023年4月14日, 上午04:07:54
    Author     : sam
--%>

<%@page import="ict.Bean.venueBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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

            <div class="row text-center p-3">
                <form action="manager_loadVenueCreate" method="post"> 

                    <th scope="col"><input type="submit" value="Create venue Option" class="btn btn-success"></th>
                </form>
            </div>



            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">vanueName</th>
                        <th scope="col">venueStatus</th>
                        <th scope="col">Type</th>
                        <th scope="col">Location</th>
                        <th scope="col">HandleByStaffId</th>
                        <th scope="col">BookingFee</th>
                        <th scope="col">Edit & View</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        ArrayList<venueBean> venues = (ArrayList<venueBean>) request.getAttribute("venueList");
                        for (venueBean venue : venues) {
                    %>
                    <tr>
                        <td><%=venue.getVenueId()%></td>
                        <td><%=venue.getVenueName()%></td>
                        <td><%=venue.getVenueStatus()%></td>
                        <td><%=venue.getType()%></td>
                        <td><%=venue.getLocation()%></td>
                        <td><%=venue.getPersonInChargeId()%></td>
                        <td><%=venue.getBookFee()%></td>
                        <td>
                            <form method='post' action='manager_loadVenueEditPage'>
                                <input class='btn btn-danger' name='id' type='hidden' value='<%=venue.getVenueId()%>'>
                                <input class='btn btn-warning' type='submit' value='Edit'>
                            </form>
                        </td>
                        <td>
                            <form onsubmit='return  deleteConfirmation();' method='post' action='venueDeleteController'>
                                <input class='btn btn-danger' name='id' type='hidden' value='<%=venue.getVenueId()%>'>
                                <input class='btn btn-danger' type='submit' value='Delete'>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>

                    <!--                                        <tr>
                                            <th scope="row">2</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                    <form>
                                        <input type="hidden" name="staffId" value="1">
                                        <th scope="col"><input type="submit" value=" edit " class="btn btn-warning"></button>
                                        </th>
                                    </form>
                                    <form onsubmit="return deleteConfirmation()"> 
                                        <input type="hidden" name="staffId" value="1">
                                        <th scope="col"><input type="submit" value="delete" class="btn btn-danger"></th>
                                    </form>
                                    </tr>-->

                </tbody>
            </table>
        </div>

    </body>

    <script>
        <%
            if (request.getAttribute("deleteMessage") != null) {
                String deleteMessage = (String) request.getAttribute("deleteMessage");
        %>
        window.addEventListener('load', function () {
            alert(<%=deleteMessage%>);
        });
        <%
            }
        %>

        <%
            if (request.getAttribute("EditMessage") != null) {
                String editMessage = (String) request.getAttribute("EditMessage");
        %>
        window.addEventListener('load', function () {
            alert(<%=editMessage%>);
        });
        <%
            }
        %>

        function deleteConfirmation() {
            const confirmed = confirm("Are you sure you want to delete?");
            if (confirmed) {
                return true;
            }

            // If the user clicked "Cancel" (no), don't submit the form
            return false;
        }
    </script>

</html>