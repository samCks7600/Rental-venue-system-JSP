<%-- 
    Document   : manager_StaffList
    Created on : 2023年4月12日, 下午08:10:56
    Author     : sam
--%>

<%@page import="com.sun.xml.rpc.processor.modeler.j2ee.xml.string"%>
<%@page import="ict.bean.staffBean"%>
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
                <form action="manager_staffCreate.jsp" method="post"> 
 
                    <th scope="col"><input type="submit" value="Create Staff Account" class="btn btn-success"></th>
                </form>
            </div>



            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Password</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>

                    </tr>
                </thead>
                <tbody>

                    <%
                        ArrayList<staffBean> staffs = (ArrayList<staffBean>) request.getAttribute("staffList");
                        for (staffBean staff : staffs) {
                            out.println("<tr>");
                            out.println("<td>" + staff.getStaffId() + "</td>");
                            out.println("<td>" + staff.getUsername() + "</td>");
                            out.println("<td>" + staff.getEmail() + "</td>");
                            out.println("<td>" + staff.getPassword() + "</td>");

                            out.println("<td><form method='get' action='manager_StaffEditPage'>");
                            out.println("<input class='btn btn-danger' name='id' type='hidden' value='" + staff.getStaffId() + "'>");
                            out.println("<input class='btn btn-warning' type='submit' value='Edit'>");
                            out.println("</form></td>");

                            out.println("<td><form onsubmit='return  deleteConfirmation();' method='get' action='StaffDeleteController'>");
//                            out.println("<td><a class='btn btn-danger' href='StaffDeleteController?id=" + staff.getStaffId() + " '>Delete</a></td>");s
                            out.println("<input class='btn btn-danger' name='id' type='hidden' value='" + staff.getStaffId() + "'>");
                            out.println("<input class='btn btn-danger' type='submit' value='Delete'>");
                            out.println("</form></td></tr>");
                            
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