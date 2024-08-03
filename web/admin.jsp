<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@page import="product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Admin Page</title>
        <!-- FONT AWESOME -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet" />

        <!-- BOOTSTRAP 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" /> 

        <!-- ADMIN CSS -->
        <link rel="stylesheet" href="./assets/css/admin.css" />
    </head>

    <body>
        <div class="wrapper">
            <aside id="sidebar">
                <div class="d-flex">
                    <button class="toggle-btn" type="button">
                        <i class="bi bi-grid"></i>
                    </button>
                    <div class="sidebar-logo">
                        <a href="#"><img src="./assets/img/base/logo.svg" alt="" /></a>
                    </div>
                </div>
                <ul class="sidebar-nav">
                    <li class="sidebar-item">
                        <a href="MainController?action=Search&activeTab=users-content" class="sidebar-link sidebar-custom__link" data-target="users-content">
                            <i class="bi bi-person"></i>
                            <span>Users Management</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="MainController?action=Search&activeTab=products-content" class="sidebar-link sidebar-custom__link" data-target="products-content">
                            <i class="fas fa-ruler"></i>
                            <span>Products Management</span>
                        </a>
                    </li>
                    <!-- Other sidebar items -->
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link sidebar-custom__link collapsed has-dropdown" data-bs-toggle="collapse" data-bs-target="#auth" aria-expanded="false" aria-controls="auth">
                            <i class="bi bi-shield-check"></i>
                            <span>Auth</span>
                        </a>
                        <ul id="auth" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link sidebar-custom__link" data-target="login-content">Login</a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link sidebar-custom__link" data-target="register-content">Register</a>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link sidebar-custom__link collapsed has-dropdown" data-bs-toggle="collapse" data-bs-target="#multi" aria-expanded="false" aria-controls="multi">
                            <i class="bi bi-layout-text-window-reverse"></i>
                            <span>Multi Level</span>
                        </a>
                        <ul id="multi" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link sidebar-custom__link collapsed" data-bs-toggle="collapse" data-bs-target="#multi-two" aria-expanded="false" aria-controls="multi-two"> Two Links </a>
                                <ul id="multi-two" class="sidebar-dropdown list-unstyled collapse">
                                    <li class="sidebar-item">
                                        <a href="#" class="sidebar-link sidebar-custom__link" data-target="link1-content">Link 1</a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="#" class="sidebar-link sidebar-custom__link" data-target="link2-content">Link 2</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link sidebar-custom__link" data-target="notification-content">
                            <i class="bi bi-chat-square-text"></i>
                            <span>Notification</span>
                        </a>
                    </li>
                    <li class="sidebar-item">
                        <a href="#" class="sidebar-link sidebar-custom__link" data-target="setting-content">
                            <i class="bi bi-gear"></i>
                            <span>Setting</span>
                        </a>
                    </li>          
                </ul>

                <div class="sidebar-item">
                    <c:url var="logoutLink" value="MainController">
                        <c:param name="action" value="Logout"></c:param>
                    </c:url>
                    <a href="${logoutLink}" class="sidebar-link">
                        <i class="bi bi-box-arrow-left"></i>
                        <span>Logout</span>
                    </a>
                </div>
            </aside>
            <div class="main p-3">
                <div id="users-content" class="content table-container ${requestScope.ACTIVE_TAB eq 'users-content' ? '' : 'd-none'}">
                    <h2>Users Management</h2>
                    <form action="MainController" method="POST" class="search-bar">
                        <input type="hidden" name="activeTab" value="users-content">
                        <input type="text" name="search" placeholder="Search" value="${param.search}">
                        <button type="submit" name="action" value="Search">Search</button>
                    </form>
                    <c:if test="${requestScope.LIST_USER != null}">
                        <c:if test="${not empty requestScope.LIST_USER}">
                            <table class="manage-account-table">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>User ID</th>
                                        <th>Full Name</th>
                                        <th>Role ID</th>
                                        <th>Password</th>
                                        <th>Email</th>
                                        <th>Phone</th>
                                        <th>External ID</th>
                                        <th>External Method</th>                                      
                                        <th>Update</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                                    <form action="MainController" method="POST">
                                        <tr>
                                            <td data-label="No">${counter.count}</td>                                      
                                            <td data-label="User ID">
                                                <input type="text" name="userID" value="${user.userID}" class="user-id" readonly=""/>
                                            </td>
                                            <td data-label="Full Name">
                                                <input type="text" name="fullName" value="${user.fullName}" required="" class="full-name" maxlength="12" />
                                            </td>
                                            <td data-label="Role ID">
                                                <input type="text" name="roleID" value="${user.roleID}" required="" class="role-id" maxlength="2" />
                                            </td>
                                            <td data-label="Password">
                                                ${user.password}
                                            </td>
                                            <td data-label="Email">
                                                ${user.email}
                                            </td>
                                            <td data-label="Phone">
                                                ${user.phone}
                                            </td>
                                            <td data-label="External ID">
                                                ${user.externalID}
                                            </td>
                                            <td data-label="External Method">
                                                ${user.externalMethod}
                                            </td>
                                            <td data-label="Update">
                                                <input type="submit" name="action" value="Update" class="btn-update" />
                                                <input type="hidden" name="userID" value="${user.userID}" />
                                                <input type="hidden" name="search" value="${param.search}" />
                                                <input type="hidden" name="activeTab" value="users-content" />
                                            </td>
                                            <td data-label="Delete">
                                                <c:url var="deleteUserLink" value="MainController">
                                                    <c:param name="action" value="Delete"></c:param>
                                                    <c:param name="userID" value="${user.userID}"></c:param>
                                                    <c:param name="search" value="${param.search}"></c:param>
                                                    <c:param name="activeTab" value="users-content"></c:param>
                                                </c:url>
                                                <a href="${deleteUserLink}" class="btn-delete">Delete</a>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                            <h3>${requestScope.DELETE_ERROR}</h3>
                        </c:if>
                    </c:if>                 
                </div>            

                <div id="products-content" class="content table-container ${requestScope.ACTIVE_TAB eq 'products-content' ? '' : 'd-none'}">
                    <h2>Products Management</h2>
                    <form action="MainController" method="POST" class="search-bar">
                        <input type="hidden" name="activeTab" value="products-content">
                        <input type="text" name="searchProduct" placeholder="Search" value="${param.searchProduct}">
                        <button type="submit" name="action" value="Search">Search</button>
                    </form>
                    <c:if test="${requestScope.LIST_PRODUCT != null}">
                        <c:if test="${not empty requestScope.LIST_PRODUCT}">
                            <table class="manage-product-table">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Product ID</th>
                                        <th>Product Name</th>
                                        <th>Image</th>                                        
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Update</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody>            
                                    <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                                    <form action="MainController" method="POST">
                                        <tr>
                                            <td data-label="No">${counter.count}</td>
                                            <td data-label="Product ID">
                                                <input type="text" name="productID" value="${product.productID}" class="product-id" readonly=""/>
                                            </td>
                                            <td data-label="Product Name">
                                                <input type="text" name="name" value="${product.name}" class="product-name" required="" maxlength="50" />
                                            </td>
                                            <td data-label="Image">
                                                <img src="${product.image}" class="product-img" readonly="" alt="Product Image" width="74%" height="74%" />
                                            </td>
                                            <td data-label="Price">
                                                <fmt:formatNumber value="${product.price}" type="number" minFractionDigits="3" maxFractionDigits="3" var="formattedPrice" />
                                                <input type="number" name="price" value="${formattedPrice}" required="" class="price" maxlength="12" /><span>VND</span>
                                            </td>
                                            <td data-label="Quantity">
                                                <input type="number" min="1000" name="quantity" value="${product.quantity}" required="" class="quantity" maxlength="3" />
                                            </td>                                           
                                            <td data-label="Update">              
                                                <input type="hidden" name="productID" value="${product.productID}" />
                                                <input type="hidden" name="searchProduct" value="${param.searchProduct}" />
                                                <input type="hidden" name="activeTab" value="products-content" />
                                                <button type="submit" name="action" value="Update" class="btn-update">Update</button>
                                            </td>
                                            <td data-label="Delete">
                                                <c:url var="deleteProductLink" value="MainController">
                                                    <c:param name="action" value="Delete"></c:param>
                                                    <c:param name="searchProduct" value="${param.searchProduct}"></c:param>
                                                    <c:param name="productID" value="${product.productID}"></c:param>
                                                    <c:param name="activeTab" value="products-content"></c:param>
                                                </c:url>
                                                <a href="${deleteProductLink}" class="btn-delete">Delete</a>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </c:if>                 
                    <h3>${requestScope.ERROR}</h3>
                </div>

                <!-- Other content sections -->
                <div id="login-content" class="content d-none">
                    <h2>Login</h2>
                    <p>Login content goes here...</p>
                </div>
                <div id="register-content" class="content d-none">
                    <h2>Register</h2>
                    <p>Register content goes here...</p>
                </div>
                <div id="link1-content" class="content d-none">
                    <h2>Link 1</h2>
                    <p>Link 1 content goes here...</p>
                </div>
                <div id="link2-content" class="content d-none">
                    <h2>Link 2</h2>
                    <p>Link 2 content goes here...</p>
                </div>
                <div id="notification-content" class="content d-none">
                    <h2>Notification</h2>
                    <p>Notification content goes here...</p>
                </div>
                <div id="setting-content" class="content d-none">
                    <h2>Setting</h2>
                    <p>Setting content goes here...</p>
                </div>
            </div>
        </div>

        <!-- BOOTSTRAP 5 JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        
        <script src="./assets/js/admin.js"></script>
    </body>
</html>
