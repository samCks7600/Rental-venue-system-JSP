<%-- 
    Document   : index
    Created on : 2023年4月10日, 下午10:40:50
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

        <style>
            a {
                text-decoration: none;
            }
            .login-page {
                width: 100%;
                height: 100vh;
                display: inline-block;
                display: flex;
                align-items: center;
            }
            .form-right i {
                font-size: 100px;
            }
        </style>


    </head>
    <body>

        <div class="login-page bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 offset-lg-1">
                        <h3 class="mb-3">Login Now</h3>
                        <div class="bg-white shadow rounded">
                            <div class="row">
                                <div class="col-md-7 pe-0">
                                    <div class="form-left h-100 py-5 px-5">
                                        <form action="LoginController" class="row g-4" method="post" id="memberForm">
                                            <div class="row p-20 mb-3">
                                                <div class="col-4 border rounded-pill p-1"><input type="radio" name="role" id="role" value="member" checked> Member</div>
                                                <div class="col-4 border rounded-pill p-1"><input type="radio" name="role" id="role" value="staff" > Staff</div>
                                                <div class="col-4 border rounded-pill p-1"><input type="radio" name="role" id="role" value="manager" > Manager</div>
                                            </div>
                                        
                                        
                                            <div class="col-12">
                                                <label>Email<span class="text-danger">*</span></label>
                                                <div class="input-group">
                                                    <div class="input-group-text"><i`   1 class="bi bi-person-fill"></i></div>
                                                    <input type="text" class="form-control" placeholder="Enter Email" id="email" name="email">
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <label>Password<span class="text-danger">*</span></label>
                                                <div class="input-group">
                                                    <div class="input-group-text"><i class="bi bi-lock-fill"></i></div>
                                                    <input type="text" class="form-control" placeholder="Enter Password" name="password" id="password">
                                                </div>
                                            </div>



                                            <div class="col-12 border">
                                                <a href="signUp.jsp" class=" text-primary">For Sign Up to be member?</a>
                                            </div>

                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary px-4 float-end mt-4">login</button>
                                            </div>
                                        </form>
                                        
                                        
                                        
                                    </div>
                                </div>
                                <div class="col-md-5 ps-0 d-none d-md-block">
                                    <div class="form-right h-100 bg-primary text-white text-center pt-5">
                                        <i class="bi bi-bootstrap"></i>
                                        <h2 class="fs-1">Welcome Back!!!</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <p class="text-end text-secondary mt-3">Bootstrap 5 Login Page Design</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->

    </body>
</html>
