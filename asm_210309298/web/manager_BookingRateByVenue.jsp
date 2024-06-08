<%-- 
    Document   : manager_BookingRateByVenue
    Created on : 2023/4/27, 下午 06:20:47
    Author     : sam
--%>

<%@page import="java.util.StringJoiner"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/css/datepicker.min.css" rel="stylesheet">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.min.js"></script>


    <%

        ArrayList<Double> results = (ArrayList<Double>) request.getAttribute("results");
        String year = (String) request.getAttribute("selectedYear");
        String venueId = String.valueOf( request.getAttribute("venueId"));
        String  yearResults = String.valueOf(request.getAttribute("yearResults")); 
        
        StringJoiner joiner = new StringJoiner(",");

        for (Double d : results) {
            joiner.add(String.valueOf(d * 100));
        }

        String stringResult = joiner.toString();


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
            <form  action="manager_loadBookingRateByVenue" method="post">
                <h2>Chose the year that your want to see:<%=year%></h2>
                <div class="col-5 mt-3 m-auto">
                    <input type="text" placeholder="click here to select year" class="form-control" name="selectedYear" id="datepicker" required />
                    <input type="hidden" name="venueId" value="<%=venueId%>">
                    
                    <input type="submit" class="btn btn-primary btn-lg"></button>
                </div>

            </form>


            <canvas id="myChart" width="50%" height="30"></canvas>


            <div class="d-flex justify-content-around">
                <a class="btn btn-primary btn-lg btn-block" href="manager_loadAnalytic_BookingListRate">Go Back Vanue List</a>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <script>
                const ctx = document.getElementById("myChart").getContext("2d");
                const myChart = new Chart(ctx, {
                    type: "bar",
                    data: {
                        labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "This Year"],

                        datasets: [
                            {
                                label: "# Rate of venue Booking",
                                data: [<%=stringResult%>,<%=yearResults %>],

                                backgroundColor: [
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 159, 64, 1)",
                                ],
                                borderColor: [
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 99, 132, 0.2)",
                                    "rgba(255, 159, 64, 1)",
                                ],
                                borderWidth: 1,

                            },
                        ],
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true,
                                max: 100
                            },
                        },
                    },
                });
            </script>
        </div>

    </body>

    <script>
        $(document).ready(function () {
            $("#datepicker").datepicker({
                format: "yyyy",
                viewMode: "years",
                minViewMode: "years",
                autoclose: true
            });
        })


    </script>

</html>