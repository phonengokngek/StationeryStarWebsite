
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@page import="model.OrderDetailDTO"%> 

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Invoice Page</title>
        <link
            rel="stylesheet"
            href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            />
    </head>
    <body>
        <!-- Invoice 1 - Bootstrap Brain Component -->
        <section class="py-3 py-md-5">
            <div class="container">
                <div class="row justify-content-center" style="margin-left: -30%; margin-right: -35%;">
                    <div class="col-12 col-lg-9 col-xl-8 col-xxl-7">
                        <div class="row gy-3 mb-3">
                            <div class="col-6">
                                <h2 class="text-uppercase text-endx m-0">Invoice</h2>
                            </div>
                            <div class="col-6">
                                <a class="d-block text-end" href="#!">
                                    <img
                                        src="./assets/img/base/logo.svg"
                                        class="img-fluid"
                                        alt="StationeryStar Logo"
                                        width="135"
                                        height="44"
                                        />
                                </a>
                            </div>
                            <div class="col-12">
                                <h4>From</h4>
                                <address>
                                    <strong>StationeryStar</strong><br />
                                    Phone: (+84) 777 888 999<br />
                                    Email: thephongse01@gmail.com
                                </address>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-12 col-sm-6 col-md-8">
                                <h4>Bill To</h4>
                                <address>
                                    <strong>${sessionScope.LOGIN_USER.fullName}</strong><br />
                                    Phone: ${sessionScope.LOGIN_USER.phone}<br />
                                    Email: ${sessionScope.LOGIN_USER.email}
                                </address>
                            </div>
                            <div class="col-12 col-sm-6 col-md-4">
                                <h4 class="row">
                                    <span class="col-6 text-sm-end">OrderID</span>
                                    <span class="col-6 text-sm-end"></span>
                                </h4>
                                <div class="row">
                                    <span class="col-6 text-sm-end">UserID</span>
                                    <span class="col-6 text-sm-end">${sessionScope.LOGIN_USER.userID}</span>
                                    <span class="col-6 text-sm-end">Order Date</span>
                                    <span class="col-6 text-sm-end">
                                        <jsp:useBean id="now" class="java.util.Date" />
                                        <fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-12">
                                <div class="table-responsive">
                                    <c:if test="${requestScope.ORDER_DETAIL != null}">
                                        <c:if test="${not empty requestScope.ORDER_DETAIL}">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th scope="col" class="text-uppercase">No</th>
                                                        <th scope="col" class="text-uppercase">Order ID</th>
                                                        <th scope="col" class="text-uppercase">Product ID</th>
                                                        <th scope="col" class="text-uppercase">Name</th>
                                                        <th scope="col" class="text-uppercase">Quantity</th>
                                                        <th scope="col" class="text-uppercase">Price</th>
                                                        <th scope="col" class="text-uppercase">Total</th>
                                                        <th scope="col" class="text-uppercase">Date</th>
                                                    </tr>
                                                </thead>
                                                <tbody class="table-group-divider">
                                                    <c:forEach var="productDetail" varStatus="counter" items="${requestScope.ORDER_DETAIL}">
                                                        <tr>
                                                            <th class="text-center">${counter.count}</th>
                                                            <th class="text-center">${productDetail.orderID}</th>
                                                            <th class="text-center">${productDetail.productID}</th>
                                                            <td>${productDetail.name}</td>
                                                            <th class="text-center">${productDetail.quantity}</th>
                                                            <td class="text-center">
                                                                <span>
                                                                    <fmt:formatNumber
                                                                        value="${productDetail.price}"
                                                                        type="number"
                                                                        minFractionDigits="3"
                                                                        maxFractionDigits="3"
                                                                        />VND
                                                                </span>
                                                            </td>
                                                            <td class="text-center">
                                                                <span>
                                                                    <fmt:formatNumber
                                                                        value="${productDetail.total}"
                                                                        type="number"
                                                                        minFractionDigits="3"
                                                                        maxFractionDigits="3"
                                                                        />VND
                                                                </span>
                                                            </td>
                                                            <td class="text-center"> 
                                                                <fmt:formatDate value="${productDetail.date}" pattern="dd/MM/yyyy" />
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 text-end">
                                <button type="submit" class="btn btn-dark mb-3">
                                    <c:url var="loadUserPageLink" value="MainController">
                                        <c:param name="action" value="Load_User_Page"></c:param>
                                    </c:url>
                                    <a href="${loadUserPageLink}" class="text-decoration-none text-white">Go to Shop</a>
                                </button>
                                <button type="submit" class="btn btn-primary mb-3">
                                    Download Invoice
                                </button>
                                <button type="submit" class="btn btn-danger mb-3">
                                    Submit Payment
                                </button>                               
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
