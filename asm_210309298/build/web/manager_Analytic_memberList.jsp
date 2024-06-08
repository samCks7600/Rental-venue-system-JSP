<%-- 
    Document   : manager_Analytic_memberList
    Created on : 2023/4/27, 下午 09:13:55
    Author     : sam
--%>


<%@page import="ict.bean.memberBean"%>
<%@page import="java.util.ArrayList"%>
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
                <h1>Analytic memberList</h1>
            </div>



            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Password</th>
                        <th scope="col">availableBooking</th>
                        <th scope="col">View Attendance</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                        ArrayList<memberBean> members = (ArrayList<memberBean>) request.getAttribute("memberList");
                        for (memberBean member : members) {
                    %>
                    <tr>
                        <td><%=member.getMemberId()%></td>
                        <td><%=member.getName()%></td>
                        <td><%=member.getEmail()%></td>
                        <td><%=member.getPassword()%></td>
                        <td><%=member.getAvailableBooking()%></td>

                        <td>
                            <form method='post' action='manager_Analytic_loadAttendanceRate'>
                                <input class='btn btn-danger' name='memberId' type='hidden' value='<%=member.getMemberId()%>'>
                                <input class='btn btn-warning' type='submit' value='View Rate'>
                            </form>
                        </td>


                        <%
                            }
                        %>


                </tbody>
            </table>
        </div>

    </body>



</html>
