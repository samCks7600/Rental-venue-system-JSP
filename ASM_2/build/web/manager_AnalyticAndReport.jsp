<%-- 
    Document   : manager_AnalyticAndReport
    Created on : 2023年4月27日, 上午02:39:53
    Author     : sam
--%>

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

    <div class="container border border-1 p-3">
        <div class="row p-5">
            <div class="col text-center">
                <form action="manager_loadAnalytic_BookingListRate" class="row g-4 p-2" method="post">
                    <button type="submit" class="btn btn-secondary">View Booking list & Rate By Venue</button>
                </form>


                <form action="" class="row g-4 p-2" method="post">
                    <button type="submit" class="btn btn-secondary">View Booking Attendance rate By Member</button>
                </form>

                <form action="" class="row g-4 p-2" method="post">
                    <button type="submit" class="btn btn-secondary">Income of Venue Booking</button>
                </form>
            </div>
        </div>
    </div>

</body>


</html>