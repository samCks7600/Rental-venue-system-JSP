<%-- 
    Document   : listVenues
    Created on : 2023年4月13日, 下午3:48:48
    Author     : user
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.VenueBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--        bs  -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="header.jsp" />
    <body>

        <table class="table table-hover mx-auto mt-3" style="width:80%;">

            <thead>
            <h1>Venue</h1>
            <tr>
                <th scope="col">VenueName</th>
                <th scope="col">Description</th>
                <th scope="col">Type</th>
                <th scope="col">Location</th>
                <th scope="col">Bookfee</th>

            </tr>
        </thead>

        <tbody>
            <% ArrayList<VenueBean> venues = (ArrayList<VenueBean>) request.getAttribute("venues");
                for (int i = 0; i < venues.size(); i++) {
                    VenueBean v = venues.get(i);
                    


            %>

            <tr class="table-primary">
                <td><%= v.getVenueName()%></td>
                <td><%= v.getDescription()%></td>
                <td><%= v.getType()%></td>
                <td><%= v.getLocation()%></td>
                <td><%= v.getBookfee()%></td>
                <td><%= v.getVenueStatus()%></td>
                <td><a href="${pageContext.request.contextPath}/VenueDetailServlet?id=<%= v.getVenueId()%>" class="btn btn-primary btn-sm">Detail</a></td> 
            </tr>
                 
            <%}%>   
        </tbody>

    </table>


</body>

</html>
