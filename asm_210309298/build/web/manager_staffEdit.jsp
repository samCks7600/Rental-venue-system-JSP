<%-- 
    Document   : manager_staffEdit
    Created on : 2023年4月13日, 上午12:04:17
    Author     : sam
--%>

<%@page import="ict.bean.staffBean"%>
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
        staffBean staffInfo = (staffBean) request.getAttribute("staffInfo");
        String staffId = staffInfo.getStaffId();
        String staffEmail = staffInfo.getEmail();
        String staffname = staffInfo.getUsername();
        String staffPassword = staffInfo.getPassword();
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
            <form onsubmit="return editConfirmation();" action="staffEditController" method="post">
                <div class="mb-3 text-center">
                    <h2>Edit Staff Information</h2>
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">User Id</label>
                    <input type="text" value="<%=staffId%>" name="id"  readonly class="form-control">
                </div>

                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">User Name</label>
                    <input type="text" value="<%=staffname%>"  name="username" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" value="<%=staffEmail%>" name="email" class="form-control">

                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="text" value="<%=staffPassword%>" name="password" class="form-control">
                </div>

                <div class="d-flex justify-content-around">
                    <button type="submit"  class="btn btn-warning">Edit Staff Information</button>
                    <a class="btn btn-secondary" href="manager_staffTableServlet">Go Back Staff Table</a>
                </div>

        </div>

    </body>

    <script >

        <%
            if (request.getAttribute("ErrorMessage") != null) {
                String errorMessage = (String) request.getAttribute("ErrorMessage");
        %>

                window.onload = function() { alert("<%= errorMessage%>"); }
        <%
            }
        %>

        function editConfirmation() {
            const confirmed = confirm("Are you sure you want to edit It?");
            if (confirmed) {
                return true;
            }
            return false;
        }


    </script>

</html>