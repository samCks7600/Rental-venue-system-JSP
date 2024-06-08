<%-- 
    Document   : cart
    Created on : 2023年4月23日, 下午7:49:21
    Author     : user
--%>

<%@page import="ict.bean.VenueBean"%>
<%@page import="ict.db.AsmDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String title = "Cart";
    AsmDB db = new AsmDB();
    ArrayList<Integer> cart = (ArrayList) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList();
    }
    String message = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<head>
    <!--        bs  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<html>
    <div class="py-2">
        <div class="container">
            <div class="row">

                <div class="col-md-9">
                    <h1> <%=title%> </h1>

                    <ivpet:Alert message="<%=message%>" context="success" />

                    <form method=“get" action="addToCart">
                        <table class="table mt-2">
                            <tr>
                                <th>ID</th>
                                <th>Item</th>
                                <th>Status</th>
                                <th>Date</th>
<!--                                <th>Time</th>
                                <th>Price</th>-->
                            </tr>
                            <% for (Integer id : cart) {
                                    VenueBean vb = db.queryVenueByID(id);
                            %>
                            <tr>
                                <td><%=vb.getVenueId()%></td>
                                <td><%=vb.getVenueName()%></td>
                                <td><%=vb.getVenueStatus()%></td>
                                
                                
                                <td>
                                    <select name="time">
                                        <option value="1">9:00-15:00</option>
                                        <option value="2">15:00 - 21:00</option>
                                        <option value="3">9:00 - 21:00</option>
                                    </select>
                                </td>
                                
                                <td><%=vb.getBookfee()%></td>
                                <td></td>

                            </tr>
                            <% }%>

                        </table>
                        <!--                        Select Your Invitation Type:
                                                <input type="radio" name="invitation" value="type1">
                                                <input type="radio" name="invitation" value="type2">-->

                        <br>

                        <a href="${pageContext.request.contextPath}/addToCart?action=clear" class="btn btn-default" onclick="confirm('Are you sure?')">
                            Cancel selection
                        </a>

                        <% if (cart != null && cart.size() > 0) { %>
                        <input type="hidden" name="action" value="submit"/>

                        <button type="submit">Submit</button>
                        <!--                        <a href="/addToCart?action=submit" class="btn btn-success ml-2">
                                                    Submit
                                                </a>-->
                        <% } else { %>
                        <button class="btn btn-primary" disabled>Submit</button>
                        <% }%>

                        

                    </form>
                </div>
            </div>
        </div>
    </div>
</html>
