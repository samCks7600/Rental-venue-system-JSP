<%-- 
    Document   : signUp.jsp
    Created on : 2023年4月11日, 上午03:34:12
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
                                    <form action="MemberSignUpServlet" class="row g-4" method="post" id="MemberSignUpForm" onsubmit="return validateForm()">

                                        <div class="col-12">
                                            <label>UserName<span class="text-danger">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-text">
                                                    <i` 1 class="bi bi-person-fill"></i>
                                                </div>
                                                <input type="text" class="form-control" placeholder="Enter Username" id="userName" name="userName">
                                            </div>
                                        </div>


                                        <div class="col-12">
                                            <label>Email<span class="text-danger">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-text">
                                                    <i` 1 class="bi bi-person-fill"></i>
                                                </div>
                                                <input type="email" class="form-control" placeholder="Enter Email" id="email" name="email">
                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label>Password<span class="text-danger">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-text"><i class="bi bi-lock-fill"></i></div>
                                                <input type="password" class="form-control" placeholder="Enter Password" name="password" id="password">

                                            </div>
                                        </div>

                                        <div class="col-12">
                                            <label>Comfirm Password<span class="text-danger">*</span></label>
                                            <div class="input-group">
                                                <div class="input-group-text"><i class="bi bi-lock-fill"></i></div>
                                                <input type="password" class="form-control" placeholder="Enter Password again" name="comfirmPassword" id="comfirmPassword">
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="text-danger" id="error-message"></div>
                                        </div>

                                        <div class="row">

                                            <div class="col">
                                            <a href="index.jsp" class="btn btn-secondary px-4 mt-4" >Back</a>
                                                <button type="submit" class="btn btn-primary px-4 float-end mt-4">Sign Up</button></div>
                                        </div>
                                    </form>



                                </div>
                            </div>
                            <div class="col-md-5 ps-0 d-none d-md-block">
                                <div class="form-right h-100 bg-primary text-white text-center pt-5">
                                    <i class=" bi-bootstrap"></i>
                                    <h2 class="fs-1">Welcome Back!!!</h2>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <script>
        function validateForm() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("comfirmPassword").value;
            const errorMessage = document.getElementById("error-message");
            if (password !== confirmPassword) {
                errorMessage.innerHTML = "Passwords do not match. Please try again.";
                return false;
            }
            return true;
        }
    </script>


</body>

</html>