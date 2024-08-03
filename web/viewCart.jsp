<%-- 
    Document   : viewCart
    Created on : Jul 2, 2024, 6:03:18 PM
    Author     : phoneee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@page import="product.ProductDTO"%> 
<%@page import="java.util.List"%> 
<%@page import="product.Cart"%>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>View Cart Page</title>

        <!-- FONT AWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet" />

        <!-- BOOTSTRAP 5 CSS-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />

        <!-- ANIMATE CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

        <!-- VIEWCART CSS -->
        <link rel="stylesheet" href="./assets/css/viewCart.css" />

    </head>
    <body>
        <!-- HEADER -->
        <header id="header-viewCart" class="container-fluid header-viewCart">
            <div class="container-md">
                <p class="text-end text-white mb-0 pb-0">
                    <span class="mx-3 border-right pr-2 font-weight-bold"><i class="fa fa-phone"></i> (+84) 777 888 999</span>
                    <c:url var="logoutLink" value="MainController">
                        <c:param name="action" value="Logout"></c:param>
                    </c:url>
                    <a href="${logoutLink}"><i class="text-white bi-box-arrow-right"></i></a>
                </p>
                <nav class="navbar navbar-expand-md">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">
                            <img src="./assets/img/base/logo.svg" alt="StationeryStar Logo" />
                        </a>
                        <button
                            class="navbar-toggler"
                            type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#productNavbar"
                            aria-controls="productNavbar"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                            >
                            <span
                                class="navbar-toggler-icon d-flex justify-content-center align-items-center"
                                >
                                <i class="fa fa-bars"></i>
                            </span>
                        </button>
                        <div class="collapse navbar-collapse" id="productNavbar">
                            <ul class="navbar-nav ms-auto">
                                <li class="nav-item line">
                                    <c:url var="loadUserPageLink" value="MainController">
                                        <c:param name="action" value="Load_User_Page"></c:param>
                                    </c:url>
                                    <a class="nav-link" href="${loadUserPageLink}">HOME</a>
                                </li>
                                <li class="nav-item line">
                                    <c:url var="orderDetailLink" value="MainController">
                                        <c:param name="action" value="Order_Detail"></c:param>
                                    </c:url>
                                    <a class="nav-link" href="${orderDetailLink}">INVOICE</a>
                                </li>
                                <li class="nav-item line">
                                    <a class="nav-link" href="#">PRODUCT INFO</a>
                                </li>
                                <li class="nav-item line active">
                                    <c:url var="viewCartLink" value="MainController">
                                        <c:param name="action" value="View_Cart"></c:param>
                                    </c:url>
                                    <a class="nav-link" aria-current="page" href="${viewCartLink}">CART</a>
                                </li>
                                <li class="nav-item line">
                                    <a class="nav-link" href="#">CONTACT</a>
                                </li>
                                <li class="nav-item line">
                                    <a class="nav-link" href="#">
                                        <i class="bi bi-bell-fill"></i>
                                        <span class="notification-icon">${sessionScope.SIZE}</span>
                                    </a>
                                    <div class="notification-menu">
                                        <c:if test="${requestScope.ERROR != null}">
                                            <div class="row notification-error">
                                                <div class="product-error">
                                                    <span>${requestScope.ERROR}</span>
                                                </div>                                           
                                            </div>
                                        </c:if>  
                                        <c:if test="${sessionScope.CART != null}">
                                            <c:if test="${not empty sessionScope.CART}">
                                                <c:forEach var="item" items="${sessionScope.CART.cart}">
                                                    <div class="row notification-item">
                                                        <div class="col-4 product-image">
                                                            <img src="${item.value.image}" alt="image">
                                                        </div>
                                                        <div class="col-8 product-info">
                                                            <span>You have successfully added ${item.value.name} with quantity ${item.value.quantity}</span>
                                                        </div>
                                                    </div> 
                                                </c:forEach>
                                            </c:if>
                                        </c:if>                                 
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </header>

        <header id="header-fixed" class="header-fixed">
            <nav class="navbar navbar-expand-md container">
                <div class="container-fluid px-0">
                    <a class="navbar-brand" href="#">
                        <img src="./assets/img/base/logo.svg" alt="StationeryStar Logo" />
                    </a>
                    <button
                        class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#productNavbar-fixed"
                        aria-controls="productNavbar-fixed"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                        >
                        <span
                            class="navbar-toggler-icon d-flex justify-content-center align-items-center"
                            >
                            <i class="fa fa-bars"></i>
                        </span>
                    </button>
                    <div class="collapse navbar-collapse" id="productNavbar-fixed">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item line">
                                <c:url var="loadUserPageLink" value="MainController">
                                    <c:param name="action" value="Load_User_Page"></c:param>
                                </c:url>
                                <a class="nav-link" href="${loadUserPageLink}">HOME</a>
                            </li>
                            <li class="nav-item line">
                                <c:url var="orderDetailLink" value="MainController">
                                    <c:param name="action" value="Order_Detail"></c:param>
                                </c:url>
                                <a class="nav-link" href="${orderDetailLink}">INVOICE</a>
                            </li>
                            <li class="nav-item line">
                                <a class="nav-link" href="#">PRODUCT INFO</a>
                            </li>
                            <li class="nav-item line active">
                                <c:url var="viewCartLink" value="MainController">
                                    <c:param name="action" value="View_Cart"></c:param>
                                </c:url>
                                <a class="nav-link" aria-current="page" href="${viewCartLink}">CART</a>
                            </li>
                            <li class="nav-item line">
                                <a class="nav-link" href="#">CONTACT</a>
                            </li>
                            <li class="nav-item line">
                                <a class="nav-link" href="#">
                                    <i class="bi bi-bell-fill"></i>
                                    <span class="notification-icon">${sessionScope.SIZE}</span>
                                </a>
                                <div class="notification-menu">
                                    <c:if test="${requestScope.ERROR != null}">
                                        <div class="row notification-error">
                                            <div class="product-error">
                                                <span>${requestScope.ERROR}</span>
                                            </div>                                           
                                        </div>
                                    </c:if>  
                                    <c:if test="${sessionScope.CART != null}">
                                        <c:if test="${not empty sessionScope.CART}">
                                            <c:forEach var="item" items="${sessionScope.CART.cart}">
                                                <div class="row notification-item">
                                                    <div class="col-4 product-image">
                                                        <img src="${item.value.image}" alt="image">
                                                    </div>
                                                    <div class="col-8 product-info">
                                                        <span>You have successfully added ${item.value.name} with quantity ${item.value.quantity}</span>
                                                    </div>
                                                </div> 
                                            </c:forEach>
                                        </c:if>
                                    </c:if>                                 
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <!-- VIEW CART -->
        <section id="view-cart" class="view-cart section">
            <div class="container-md">
                <h2 class="product-title section-title">VIEW CART</h2>
                <div id="products-content" class="content table-container">             
                    <c:if test="${sessionScope.CART != null}">
                        <c:if test="${not empty sessionScope.CART}">
                            <table class="manage-product-table">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Product ID</th>
                                        <th>Product Name</th>
                                        <th>Image</th>                                        
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                        <th>Edit</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" varStatus="counter" items="${sessionScope.CART.cart}">
                                    <form action="MainController" method="POST">
                                        <tr>
                                            <td data-label="No">${counter.count}</td>
                                            <td data-label="Product ID">
                                                <input type="text" name="productID" value="${product.value.productID}" class="product-id" readonly=""/>
                                            </td>
                                            <td data-label="Name">
                                                <input type="text" name="name" value="${product.value.name}" class="name" required="" maxlength="50" />
                                            </td>
                                            <td data-label="Image">
                                                <img src="${product.value.image}" class="product-img" readonly="" alt="Product Image" width="74%" height="74%" />
                                            </td>
                                            <td data-label="Price">                                  
                                                <span><fmt:formatNumber value="${product.value.price}" type="number" minFractionDigits="3" maxFractionDigits="3"/>VND</span>
                                            </td>
                                            <td data-label="Quantity">
                                                <input type="number" min="1" name="quantity" value="${product.value.quantity}" required="" class="quantity" maxlength="3" />
                                            </td>      
                                            <td data-label="Total">
                                                <span><fmt:formatNumber value="${product.value.price * product.value.quantity}" type="number" minFractionDigits="3" maxFractionDigits="3"/>VND</span>               
                                            </td>
                                            <td data-label="Edit">
                                                <input type="submit" name="action" value="Edit" class="btn-update" />
                                                <input type="hidden" name="productID" value="${product.value.productID}" />
                                                <input type="hidden" name="searchProduct" value="${param.searchProduct}" />
                                                <input type="hidden" name="activeTab" value="products-content" />
                                            </td>
                                            <td data-label="Remove">
                                                <c:url var="removeLink" value="MainController">
                                                    <c:param name="action" value="Remove"></c:param>
                                                    <c:param name="searchProduct" value="${param.searchProduct}"></c:param>
                                                    <c:param name="productID" value="${product.value.productID}"></c:param>
                                                    <c:param name="activeTab" value="products-content"></c:param>
                                                </c:url>
                                                <a href="${removeLink}" class="btn-delete">Remove</a>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>                 
                            </table>

                            <c:set var="total" value="0" scope="page" />
                            <c:if test="${sessionScope.CART != null}">
                                <c:if test="${not empty sessionScope.CART}">
                                    <c:forEach var="product" items="${sessionScope.CART.cart.values()}">
                                        <c:set var="subtotal" value="${product.price * product.quantity}" />
                                        <c:set var="total" value="${total + subtotal}" />
                                    </c:forEach>
                                </c:if>
                            </c:if>
                            <fmt:formatNumber var="totalFormated" value="${total}" type="number" minFractionDigits="3" maxFractionDigits="3"/>
                            <h2 class="text-end">Total: <c:out value="${totalFormated}" />VND</h2>

                            <form action="MainController" method="POST" class="text-end">
                                <button type="submit" name="action" value="Checkout" class="btn-checkout">Checkout</button>
                            </form> 

                        </c:if>
                    </c:if>

                </div>
                <h3 class="text-center">${requestScope.ERROR}</h3>
            </div>
        </section>

        <!-- FOOTER -->
        <footer id="footer" class="footer">
            <div class="container">
                <div class="row section px-auto">
                    <div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3 py-3">
                        <h3>GET IN TOUCH</h3>
                        <ul class="ps-0">
                            <li><a href="#">FAQs</a></li>
                            <li><a href="#">Give us feedback</a></li>
                            <li><a href="#">Contact us</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3 py-3">
                        <h3>ABOUT MOVIE STAR</h3>
                        <ul class="ps-0">
                            <li><a href="#">About us</a></li>
                            <li><a href="#">Find us</a></li>
                            <li><a href="#">Schedule</a></li>
                            <li><a href="#">Product</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3 py-3">
                        <h3>LEGAL STUFF</h3>
                        <ul class="ps-0">
                            <li><a href="#">Term & Conditions</a></li>
                            <li><a href="#">Privacy policy</a></li>
                            <li><a href="#">Cookie policy</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-sm-6 col-md-3 col-lg-3 col-xl-3 py-3">
                        <h3>CONNECT WITH US</h3>
                        <ul class="ps-0">
                            <li>
                                <a href="#"><i class="fab fa-facebook"></i>Facebbook</a>
                            </li>
                            <li>
                                <a href="#"><i class="fab fa-twitter"></i>Twitter</a>
                            </li>
                            <li>
                                <a href="#"><i class="fab fa-google-plus-g"></i>Google +</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="footer__copyright">
                    <p>2024 $ Stationery Star</p>
                </div>
            </div>
        </footer>

        <!-- LIBRARIES -->

        <!-- BOOTSTRAP 5 JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

        <script src="./assets/js/user.js"></script>
    </body>
</html>

