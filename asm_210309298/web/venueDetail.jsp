<%-- 
    Document   : venueDetail
    Created on : 2023年4月14日, 上午12:54:19
    Author     : user
--%>

<%@page import="ict.db.AsmDB"%>
<%@page import="ict.bean.VenueBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String title = "Cart";

    AsmDB db = new AsmDB();
    ArrayList<Integer> cart = (ArrayList) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList();
    }
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css'>
    </head>
    <body>
        <jsp:useBean id="v" scope="request" class="ict.bean.VenueBean" />
        <%
            String venueId = Integer.toString(v.getVenueId());
            String venueName = v.getVenueName();
            String desc = v.getDescription();
            String venueStatus = v.getVenueStatus();
            String type = v.getType();
            String location = v.getLocation();
            int person_in_charge_id = v.getPerson_in_charge_id();
            int bookfee = v.getBookfee();
        %>

        <jsp:include page="header.jsp" />
        <!-- partial:index.partial.html -->
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-12 mb-4">
                            <img alt="" class="img-thumbnail p-0 border-0" src="https://images.unsplash.com/photo-1568702846914-96b305d2aaeb?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80" />
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <h1><%=venueName%></h1>

                            <ul class="list-group list-group-flush mb-4">
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Venue Type: <a href="#"><%=type%></a></li>
                                <p class="m-0 h2">$<%=bookfee%> </p>
                            </ul>
                            <form method=“get" action="AddRecordServlet">
                                <div>
                                    <input  type="date" name="date"  class="form-control" id="fromBookingDate" placeholder="dd/MM/yyyy" required/>

                                    <select name="time" class="form-control" >
                                        <option value="1">9:00-15:00</option>
                                        <option value="2">15:00 - 21:00</option>
                                        <option value="3">9:00 - 21:00</option>
                                    </select>
                                </div>
                                <input type="hidden" name="venueid" value="<%=venueId%>"/>
                                <button type="submit">Submit</button>
                            </form>


                            </td

                        </div>
                    </div>
                </div>

            </div>
            <div class="row"></div>
            <div class="row"></div>
            <div class="row"></div>
            <div class="row"></div>
            <div class="row"></div>
            <div class="row"></div>
            <div class="row"></div>
            <div class="row"></div>


            <div class="col-md-12">
                <ul class="nav justify-content-center">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Privacy Policy</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Terms & Conditions</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Site Map</a>
                    </li>
                </ul>
            </div>
            
        </div>
    </div>

    <!-- partial -->
<!--    <script src='https://code.jquery.com/jquery-3.4.1.slim.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'></script>
    <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.js'></script>-->

</body>
</html>

