
<%@page import="model.UserError"%>
<html>
    <head>
        <title>Create Account Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <!-- Registration 13 - Bootstrap Brain Component -->
        <section class="bg-light py-3 py-md-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                        <div class="card border border-light-subtle rounded-3 shadow-sm">
                            <div class="card-body p-3 p-md-4 p-xl-5">
                                <div class="text-center mb-3">
                                    <a href="#!">
                                        <img src="./assets/img/base/logo.svg" alt="StationeryStar Logo" width="220" height="100" style="border-radius: 5px">
                                    </a>
                                </div>
                                <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Enter your details to register</h2>
                                <form action="MainController" method="POST">                                    
                                    <div class="row gy-2 overflow-hidden">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="text" class="form-control" name="fullName" id="fulltName" placeholder="Full Name" required>
                                                <label for="fullName" class="form-label">Full Name</label>
                                            </div>
                                            <span class="text-danger">${requestScope.USER_ERROR.fullNameError}</span>
                                        </div>       
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                                                <label for="email" class="form-label">Email</label>
                                            </div>
                                            <span class="text-danger">${requestScope.USER_ERROR.emailError}</span>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" required>
                                                <label for="password" class="form-label">Password</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" value="" placeholder="Password" required>
                                                <label for="confirmPassword" class="form-label">Confirm Password</label>
                                            </div>
                                            <span class="text-danger">${requestScope.USER_ERROR.confirmError}</span>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="tel" class="form-control" name="phone" id="phone" placeholder="Enter your phone number" required>
                                                <label for="phone" class="form-label">Phone Number</label>
                                            </div>
                                            <span class="text-danger">${requestScope.USER_ERROR.phoneError}</span>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" value="" name="iAgree" id="iAgree" required>
                                                <label class="form-check-label text-secondary" for="iAgree">
                                                    I agree to the <a href="#!" class="link-primary text-decoration-none" style="color: #ec7532 !important">terms and conditions</a>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid my-3">
                                                <button class="btn btn-dark btn-lg" type="submit" name="action" value="Create">Submit</button>
                                            </div>
                                            <div class="d-grid my-3">
                                                <button class="btn btn-danger btn-lg" type="reset" value="Reset">Reset</button>
                                            </div>
                                            <span class="text-danger">${requestScope.USER_ERROR.error}</span>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
