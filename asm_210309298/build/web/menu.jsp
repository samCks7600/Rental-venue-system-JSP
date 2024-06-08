<%-- 
    Document   : menu
    Created on : 2023年4月27日, 上午2:50:44
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css'>
    </head>
    <jsp:include page="header.jsp" />
    <body>

        <h1>Member</h1>

    <li>  <a href="VenueViewServlet" >list Venue </a></li>
    <li>  <a href="m_ViewBookingServlet" >My Booking Record </a></li>

    <h1>Staff</h1>
    <li>  <a href="RequestViewServlet" >view Request</a></li>
    <li> <a href="s_ViewPaidRequest" >Check Payment</a></li>
    <li> <a href="CheckInOut" >Check In/Out</a></li>

    <h1>Manager</h1>
    <form  action="manager_staffTableServlet" class="row g-4 p-2" method="post">
        <button type="submit" class="btn btn-secondary">User account management</button>
    </form>
    <form  action="manage_venueTable" class="row g-4 p-2" method="post">
        <button type="submit" class="btn btn-secondary">Venue Management</button>
    </form>
    <form  action="manager_AnalyticAndReport.jsp" class="row g-4 p-2" method="post">
        <button type="submit" class="btn btn-secondary">Analytic & Report</button>
    </form>
</body>
</html>
