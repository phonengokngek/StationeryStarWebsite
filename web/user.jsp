<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="product.ProductDTO" %> <%@page import="java.util.List" %> 
<%@page import="model.ProductDAO" %> <%@page import="model.UserDTO" %> 
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Stationery Star Page</title>

        <!-- FONT AWESOME -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
            />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap"
            rel="stylesheet"
            />

        <!-- BOOTSTRAP 5 CSS-->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
            />

        <!-- ANIMATE CSS -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
            />

        <!-- VENOBOX CSS -->
        <link rel="stylesheet" href="./assets/css/lib/venobox.min.css" />

        <!-- OWL CAROUSEL -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
            integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
            integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />

        <!-- USER CSS -->
        <link rel="stylesheet" href="./assets/css/user.css" />
        <link rel="stylesheet" href="./assets/css/responsive.css" />

        <style>
            .product .owl-stage-outer {
                position: relative;
            }

            #product .owl-nav .owl-prev,
            #product .owl-nav .owl-next {
                top: 34%;
                position: absolute;
                display: block;
                background-color: rgba(255, 152, 0, 0.75);
                background-image: linear-gradient(
                    to right,
                    #f5ce62,
                    #e43603,
                    #fa7199,
                    #e85a19
                    );
                background-size: 300% 100%;
                width: 54px;
                height: 76px;
                color: white;
                padding: 10px;
                display: flex;
                justify-content: center;
                align-items: center;
                transition: background-color 0.6s ease;
            }

            #product .owl-nav .owl-prev:hover,
            #product .owl-nav .owl-next:hover {
                animation: gradientAnimation 2s infinite;
            }

            @keyframes gradientAnimation {
                0% {
                    background-position: 0% 0%;
                }

                100% {
                    background-position: 100% 0%;
                }
            }

            #product .owl-nav .owl-prev {
                left: 0;
            }

            #product .owl-nav .owl-next {
                right: 0;
            }

            #product .owl-nav .owl-prev span,
            #product .owl-nav .owl-next span {
                display: inline-block;
                font-size: 4rem;
            }
        </style>
    </head>

    <body>
        <!-- HEADER -->
        <header id="header" class="container-md header">
            <p class="text-end text-white mb-0">
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
                            <li class="nav-item line active">
                                <c:url var="loadUserPageLink" value="MainController">
                                    <c:param name="action" value="Load_User_Page"></c:param>
                                </c:url>
                                <a class="nav-link" aria-current="page" href="${loadUserPageLink}">HOME</a>
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
                            <li class="nav-item line">
                                <c:url var="viewCartLink" value="MainController">
                                    <c:param name="action" value="View_Cart"></c:param>
                                </c:url>
                                <a class="nav-link" href="${viewCartLink}">CART</a>
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
                            <li class="nav-item line active">
                                <c:url var="loadUserPageLink" value="MainController">
                                    <c:param name="action" value="Load_User_Page"></c:param>
                                </c:url>
                                <a class="nav-link" aria-current="page" href="${loadUserPageLink}">HOME</a>
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
                            <li class="nav-item line">
                                <c:url var="viewCartLink" value="MainController">
                                    <c:param name="action" value="View_Cart"></c:param>
                                </c:url>
                                <a class="nav-link" href="${viewCartLink}">CART</a>
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

        <!-- CAROUSEL -->
        <section id="carousel" class="carousel">
            <div id="carouselProduct" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button
                        type="button"
                        data-bs-target="#carouselProduct"
                        data-bs-slide-to="0"
                        class="active"
                        aria-current="true"
                        aria-label="Slide 1"
                        ></button>
                    <button
                        type="button"
                        data-bs-target="#carouselProduct"
                        data-bs-slide-to="1"
                        aria-label="Slide 2"
                        ></button>
                    <button
                        type="button"
                        data-bs-target="#carouselProduct"
                        data-bs-slide-to="2"
                        aria-label="Slide 3"
                        ></button>
                    <button
                        type="button"
                        data-bs-target="#carouselProduct"
                        data-bs-slide-to="3"
                        aria-label="Slide 4"
                        ></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <!-- <img src="./assets/img/base/hero-1.jpg" class="d-block w-100" alt="frist slide"> -->
                        <div class="carousel-overlay"></div>
                        <div class="carousel-caption container">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-9 col-xl-9">
                                <p class="title animate__animated animate__fadeInDown">
                                    VARIETY OF STATIONERY TYPES
                                </p>
                                <h2 class="display-4 animate__animated animate__fadeInDown">
                                    Back To School For Students
                                </h2>
                                <p class="animate__animated animate__fadeInUP">
                                    Many offers and gifts for everyone buy stationery sets soon
                                    !!!
                                </p>
                                <div class="carousel-trailer mt-4">
                                    <span
                                        class="text-white d-inline-block rounded-circle text-center"
                                        >PG</span
                                    >
                                    <button class="text-white ms-2">
                                        <span>
                                            <i class="fa fa-play ms-2"></i>
                                            BUY NOW
                                        </span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <!-- <img src="./assets/img/base/hero-2.jpg" class="d-block w-100" alt="second slide"> -->
                        <div class="carousel-overlay"></div>
                        <div class="carousel-caption container">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-9 col-xl-9">
                                <p class="title animate__animated animate__fadeInDown">
                                    VARIETY OF STATIONERY TYPES
                                </p>
                                <h2 class="display-4 animate__animated animate__fadeInDown">
                                    Back To School For Students
                                </h2>
                                <p class="animate__animated animate__fadeInUP">
                                    Many offers and gifts for everyone buy stationery sets soon
                                    !!!
                                </p>
                                <div class="carousel-trailer mt-4">
                                    <span
                                        class="text-white d-inline-block rounded-circle text-center"
                                        >PG</span
                                    >
                                    <button class="text-white ms-2">
                                        <span>
                                            <i class="fa fa-play ms-2"></i>
                                            BUY NOW
                                        </span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <!-- <img src="./assets/img/base/hero-3.jpg" class="d-block w-100" alt="third slide"> -->
                        <div class="carousel-overlay"></div>
                        <div class="carousel-caption container">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-9 col-xl-9">
                                <p class="title animate__animated animate__fadeInDown">
                                    VARIETY OF STATIONERY TYPES
                                </p>
                                <h2 class="display-4 animate__animated animate__fadeInDown">
                                    Back To School For Students
                                </h2>
                                <p class="animate__animated animate__fadeInUP">
                                    Many offers and gifts for everyone buy stationery sets soon
                                    !!!
                                </p>
                                <div class="carousel-trailer mt-4">
                                    <span
                                        class="text-white d-inline-block rounded-circle text-center"
                                        >PG</span
                                    >
                                    <button class="text-white ms-2">
                                        <span>
                                            <i class="fa fa-play ms-2"></i>
                                            BUY NOW
                                        </span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <!-- <img src="./assets/img/base/hero-4.jpg" class="d-block w-100" alt="third slide"> -->
                        <div class="carousel-overlay"></div>
                        <div class="carousel-caption container">
                            <div class="col-12 col-sm-12 col-md-12 col-lg-9 col-xl-9">
                                <p class="title animate__animated animate__fadeInDown">
                                    VARIETY OF STATIONERY TYPES
                                </p>
                                <h2 class="display-4 animate__animated animate__fadeInDown">
                                    Back To School For Students
                                </h2>
                                <p class="animate__animated animate__fadeInUP">
                                    Many offers and gifts for everyone buy stationery sets soon
                                    !!!
                                </p>
                                <div class="carousel-trailer mt-4">
                                    <span
                                        class="text-white d-inline-block rounded-circle text-center"
                                        >PG</span
                                    >
                                    <button class="text-white ms-2">
                                        <span>
                                            <i class="fa fa-play ms-2"></i>
                                            BUY NOW
                                        </span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- <button class="carousel-control-prev" type="button" data-bs-target="#carouselProduct" data-bs-slide="prev">
                                                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                                        <span class="visually-hidden">Previous</span>
                                                                    </button>
                                                                    <button class="carousel-control-next" type="button" data-bs-target="#carouselProduct" data-bs-slide="next">
                                                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                                        <span class="visually-hidden">Next</span>
                                                                    </button> -->
            </div>
        </section>

        <!-- SHOWING PRODUCT -->
        <section id="product" class="product section">
            <div class="container-md">
                <h2 class="product-title section-title">NEW IN</h2>
                <div class="owl-carousel owl-theme row text-center">
                    <c:if test="${requestScope.LIST_PRODUCT != null}">
                        <c:if test="${not empty requestScope.LIST_PRODUCT}">
                            <c:forEach var="product" items="${requestScope.LIST_PRODUCT}">
                                <div class="item pt-5 px-auto">
                                    <form action="MainController" method="POST">
                                        <div class="product-img">
                                            <img src="${product.image}" class="img-fluid w-100" />
                                            <div class="product-price">
                                                <span>
                                                    <fmt:formatNumber
                                                        value="${product.price}"
                                                        type="number"
                                                        minFractionDigits="3"
                                                        maxFractionDigits="3"
                                                        />VND
                                                </span>
                                            </div>
                                            <div class="product-overlay"></div>
                                            <div class="product-detail">
                                                <div
                                                    class="product-trailer my-video-links"
                                                    data-vbtype="video"
                                                    href=""
                                                    >
                                                    <a
                                                        class="my-video-links"
                                                        data-vbtype="video"
                                                        href=""
                                                        ><i class="fa fa-play d-block"></i
                                                        ></a>
                                                </div>
                                                <span class="product-text">WATCH REVIEW</span>
                                                <div class="product-quantity">
                                                    <button class="decrease-button">--</button>
                                                    <input type="number" name="quantity" min="1" maxlength="4">
                                                    <button class="increase-button">+</button>
                                                </div>
                                                <div class="product-options">
                                                    <div class="product-option">
                                                        <button><i class="bi bi-heart-fill"></i></button>
                                                    </div>
                                                    <div class="product-option">
                                                        <input type="hidden" name="productID" value="${product.productID}">
                                                        <input type="hidden" name="quantity" value="${param.quantity}">
                                                        <input type="hidden" name="indexTab" value="${requestScope.TAB}"> 
                                                        <button type="submit" name="action" value="Info">
                                                            <i class="bi bi-eye-fill"></i>
                                                        </button>
                                                    </div>
                                                    <div class="product-option">                                                               
                                                        <input type="hidden" name="productID" value="${product.productID}">
                                                        <input type="hidden" name="quantity" value="${param.quantity}">
                                                        <input type="hidden" name="indexTab" value="${requestScope.TAB}">                                                          
                                                        <button type="submit" name="action" value="Add">
                                                            <i class="bi bi-cart-fill"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="product-title">
                                            <h4 class="product-name">${product.name}</h4>
                                        </div>
                                    </form>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </section>

        <!-- PRODUCT SHOP -->
        <section id="shop" class="shop section">
            <div class="container-md">
                <h2 class="product-title section-title">MOVIE SHOP</h2>
                <!-- Tab items -->
                <div class="tabs">
                    <c:if test="${requestScope.END_TAB != null}">
                        <c:if test="${not empty requestScope.END_TAB}">
                            <c:forEach var="i" begin="1" end="${requestScope.END_TAB}">
                                <div class="tab-item ${requestScope.TAB == i ? 'active' : ''}">
                                    <c:url var="loadUserPageLink" value="MainController">
                                        <c:param name="action" value="Load_User_Page"></c:param>
                                        <c:param name="indexTab" value="${i}"></c:param>
                                    </c:url>
                                    <a href="${loadUserPageLink}" class="tab-link">
                                        <i class="tab-icon fas fa-ruler"></i> Tab ${i}
                                    </a>
                                </div>
                            </c:forEach>
                        </c:if>
                    </c:if>
                    <div class="line"></div>
                </div>

                <!-- Tab content -->
                <div class="tab-content">
                    <div class="tab-pane active">
                        <div class="tab-wrapper row d-flex text-center" id="tab-wrapper">
                            <c:if test="${requestScope.LIST_PRODUCT_EACH_TAB != null}">
                                <c:if test="${not empty requestScope.LIST_PRODUCT_EACH_TAB}">
                                    <c:forEach var="product" items="${requestScope.LIST_PRODUCT_EACH_TAB}">                                        
                                        <div class="col-ct-2 col-ct-3 col-ct-4 col-2 col-md-2 pt-5 px-auto">
                                            <form action="MainController" method="POST">
                                                <div class="product-img">
                                                    <img src="${product.image}" class="img-fluid w-100" />
                                                    <div class="product-price">
                                                        <span>
                                                            <fmt:formatNumber
                                                                value="${product.price}"
                                                                type="number"
                                                                minFractionDigits="3"
                                                                maxFractionDigits="3"
                                                                />VND
                                                        </span>
                                                    </div>
                                                    <div class="product-overlay"></div>
                                                    <div class="product-detail">
                                                        <div
                                                            class="product-trailer my-video-links"
                                                            data-vbtype="video"
                                                            href=""
                                                            >
                                                            <a
                                                                class="my-video-links"
                                                                data-vbtype="video"
                                                                href=""
                                                                ><i class="fa fa-play d-block"></i
                                                                ></a>
                                                        </div>
                                                        <span class="product-text">WATCH REVIEW</span>
                                                        <div class="product-quantity">
                                                            <button type="button" class="decrease-button">--</button>
                                                            <input type="number" name="quantity" min="1" maxlength="4">
                                                            <button type="button" class="increase-button">+</button>
                                                        </div>
                                                        <div class="product-options">
                                                            <div class="product-option">
                                                                <button>
                                                                    <i class="bi bi-heart-fill"></i>
                                                                </button>
                                                            </div>
                                                            <div class="product-option">      
                                                                <input type="hidden" name="productID" value="${product.productID}">
                                                                <input type="hidden" name="quantity" value="${param.quantity}">
                                                                <input type="hidden" name="indexTab" value="${requestScope.TAB}"> 
                                                                <button type="submit" name="action" value="Info">
                                                                    <i class="bi bi-eye-fill"></i>
                                                                </button>
                                                            </div>
                                                            <div class="product-option">                                                           
                                                                <input type="hidden" name="productID" value="${product.productID}">
                                                                <input type="hidden" name="quantity" value="${param.quantity}">
                                                                <input type="hidden" name="indexTab" value="${requestScope.TAB}">                                                          
                                                                <button type="submit" name="action" value="Add">
                                                                    <i class="bi bi-cart-fill"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="product-title">
                                                    <h4 class="product-name">${product.name}</h4>
                                                </div>
                                            </form>
                                        </div>                                     
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- CONTACT -->
        <section id="contact" class="contact section">
            <div class="container-md">
                <h2 class="contact-title section-title">CONTACT</h2>
                <div class="contact-content text-center">
                    <p>Need help ? Contact our support team on</p>
                    <p><a href="tel:0961051014">096 105 1014</a></p>
                </div>
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

        <!-- JQUERY CDN -->
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>

        <!-- BOOTSTRAP 5 JS -->
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"
        ></script>

        <!-- VENOBOX JS -->
        <script src="./assets/js/lib/venobox.min.js"></script>

        <script>
            new VenoBox({
                selector: ".my-video-links",
            });
        </script>

        <!-- OWL CAROUSEL -->
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
            integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>

        <script>
            $(".owl-carousel").owlCarousel({
                stagePadding: 50,
                loop: true,
                margin: 10,
                nav: true,
                dots: false,
                autoplay: true,
                autoplayTimeout: 2000,
                responsive: {
                    0: {
                        items: 3,
                    },
                    600: {
                        items: 3,
                    },
                    700: {
                        items: 4,
                    },
                    1000: {
                        items: 6,
                    },
                },
            });
        </script>

        <script src="./assets/js/user.js"></script>
    </body>
</html>
