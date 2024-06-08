<%-- 
    Document   : manager_Analytic_AttendanceRate
    Created on : 2023/4/27, 下午 10:53:37
    Author     : sam
--%>

<%@page import="java.util.StringJoiner"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

    <%
        String memberId
                = String.valueOf(request.getAttribute("memberId"));

        ArrayList<Double> attendanceRate
                = (ArrayList<Double>) request.getAttribute("attendanceRate");

        ArrayList<Double> attendanceCount
                = (ArrayList<Double>) request.getAttribute("attendanceCount");

        String yearAttendanceRate = String.valueOf(request.getAttribute("yearAttendanceRate"));

        Double nYearAttendanceRate = Double.parseDouble(yearAttendanceRate);
        nYearAttendanceRate *= 100;

        String yearBookingCount
                = String.valueOf(request.getAttribute("yearBookingCount"));

        StringJoiner joiner = new StringJoiner(",");
        for (Double d : attendanceRate) {
            joiner.add(String.valueOf(d * 100));
        }
        String stringAttendanceRate = joiner.toString();

        StringJoiner joiner2 = new StringJoiner(",");
        for (Double d : attendanceCount) {
            joiner2.add(String.valueOf(d));
        }
        String stringAttendanceCount = joiner2.toString();

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

        <div class="container border border-1 p-3 shadow text-center">
            <!-- partial:index.partial.html -->
            <canvas id="densityChart" width="600" height="400"></canvas>
            <!-- partial -->
            <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js'></script>
            <script>
                var densityCanvas = document.getElementById("densityChart");

                Chart.defaults.global.defaultFontFamily = "Lato";
                Chart.defaults.global.defaultFontSize = 18;

                var densityData = {
                    label: 'Attendance Rate',
                    data: [<%=stringAttendanceRate%>,<%= nYearAttendanceRate%>],
                    backgroundColor: 'rgba(0, 99, 132, 0.6)',
                    borderColor: 'rgba(0, 99, 132, 1)',
//                    yAxisID: "y-axis-density"
                };

                var gravityData = {
                    label: 'Attendance count',
                    data: [<%=stringAttendanceCount%>,<%= yearBookingCount%>],
                    backgroundColor: 'rgba(99, 132, 0, 0.6)',
                    borderColor: 'rgba(99, 132, 0, 1)',
//                    yAxisID: "y-axis-gravity"
                };

                var planetData = {
                    labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "This Year"],
                    datasets: [densityData, gravityData]
                };

                var chartOptions = {
                    scales: {
                        xAxes: [{
                                barPercentage: 1,
                                categoryPercentage: 0.6
                            }],
                        yAxes: [{
                                display: true,
                                ticks: {
                                    min: 0,
                                    max: 100
                                }
                            }]

                    }
                };

                var barChart = new Chart(densityCanvas, {
                    type: 'bar',
                    data: planetData,
                    options: chartOptions
                });
            </script>
        </div>
    </body>
</html>
