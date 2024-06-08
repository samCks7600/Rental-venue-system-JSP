<%-- 
    Document   : recordDetail
    Created on : 2023年4月26日, 下午6:41:05
    Author     : user
--%>

<%@page import="ict.db.AsmDB"%>
<%@page import="ict.bean.BookingRecordBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css'>
    </head>
    <body>

        <jsp:useBean id="v" scope="request" class="ict.bean.BookingRecordBean" />
        <jsp:useBean id="v1" scope="request" class="ict.bean.VenueBean" />
        <%

            boolean requestValid = (Boolean) request.getAttribute("requestValid");
            String Staffid = (String) request.getSession().getAttribute("Id");
            
            ArrayList<BookingRecordBean> records = (ArrayList<BookingRecordBean>) request.getAttribute("records");
            for (int i = 0; i < records.size(); i++) {
                BookingRecordBean rs = records.get(i);

            }

        %>
        <jsp:include page="header.jsp" />
        
        <div class="container">
            <div class="row mb-5">

                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <h1>Booking ID:<%=v.getBookingid()%></h1> 
                            <h3>Status: <span class="badge rounded-pill bg-primary"><%=v.getStatus()%></span></h3>
                            <ul class="list-group list-group-flush mb-4">
                                <br>
                                <h3>Venue Detail</h3>
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Venue Name :<a href="#"> <%=v1.getVenueName()%> </a></li>
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Venue Type :<a href="#"> <%=v1.getType()%> </a></li>
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Location :<a href="#"> <%=v1.getLocation()%>  </a></li>
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Booking Fee : $<a href="#"><%=v1.getBookfee()%> </a></li>
                            </ul>

                            <ul class="list-group list-group-flush mb-4">
                                <br>
                                <h3>Booking Request Detail</h3>
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Time :<a href="#"> <%=v.getTime()%> </a></li>
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Date:<a href="#"> <%=v.getBookingDate()%> </a></li>
                                <li class="list-group-item pl-0 pr-0 pt-2 pb-2">Member ID :<a href="#"> <%=v.getMemberid()%>  </a></li>

                            </ul>
                            <form method=“get" action="UpdateBookingStatusServlet">
                                <h3>Allow to Book:<%=requestValid%></h3>

                                <input type="hidden" name="staffid" value="<%=Staffid%>"/>
                                <input type="hidden" name="bookingid" value="<%=v.getBookingid()%>"/>
                                <input type="hidden" name="fee" value="<%=v1.getBookfee()%>"/>

                                <%
                                    if (!requestValid) {
                                        // handle invalid request
                                %><button type="submit" disabled>Approve</button><%
                                } else {
                                    // handle valid request
                                %><button type="submit" name="action" value="payment">Approve</button><%
                                    }


                                %>
                                <!--                                <button type="submit">Approve</button>-->
                                <button type="submit" name="action" value="reject">Reject</button>
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


            <jsp:include page="footer.jsp" />

        </div>
    </div>
</body>
</html>
