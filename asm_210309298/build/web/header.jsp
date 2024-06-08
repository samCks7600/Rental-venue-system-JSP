<%-- 
    Document   : header
    Created on : 2023年4月26日, 下午6:46:39
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">

        <!--        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>-->
        <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css'>
        <title>JSP Page</title>
    </head>
    <%

        String userPosition = (String) request.getSession().getAttribute("userPosition");
        String memberEmail = (String) request.getSession().getAttribute("userEmail");
        String memberName = (String) request.getSession().getAttribute("userName");
        String Id = (String) request.getSession().getAttribute("Id");
        

    %>
    <body>

        <div class="container">
            <div class="row mb-4">
                <div class="col-md-8">

                    <ul class="nav justify-content-end ">
                        <li class="nav-item mr-auto"> 
                            <a class="nav-link" href="javascript:history.back()"><i class="fas fa-arrow-circle-left fa-lg"></i></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Company Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact us</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-1">
                    <ul class="nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-globe"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#">English</a>
                                <a class="dropdown-item" href="#">Türkçe</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="col-md-1">
                    <ul class="nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-coins"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#">Dollar</a>
                                <a class="dropdown-item" href="#">Euro</a>
                                <a class="dropdown-item" href="#">Türk Lirası</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="col-md-2">
                    <ul class="nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-user"></i>
                                <span>HI,<%=memberName%></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#"><span class="badge bg-primary fs-6">Position:</span><%=userPosition%></a>
                                <a class="dropdown-item" href="#"><span class="badge bg-primary fs-6">ID:</span><%=Id%></a>
                                <a class="dropdown-item" href="#"><span class="badge bg-primary fs-6">Email:</span><%=memberEmail%></a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>


        </div>
        <script src='https://code.jquery.com/jquery-3.4.1.slim.min.js'></script>
        <script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js'></script>
        <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.js'></script>
    </body>
</html>
