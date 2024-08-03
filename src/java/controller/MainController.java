package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String WELCOME = "login.html";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    
        private static final String VIEW_CART = "View_Cart";
    private static final String VIEW_CART_PAGE = "viewCart.jsp";
    
    private static final String CREATE_PAGE = "Create_Page";
    private static final String CREATE_PAGE_CONTROLLER = "create.html";

    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";

    private static final String REQUEST_PASSWORD_PAGE = "Request_Password_Page";
    private static final String REQUEST_PASSWORD_PAGE_CONTROLLER = "requestPassword.html";

    private static final String REQUEST_PASSWORD = "Request_Password";
    private static final String REQUEST_PASSWORD_CONTROLLER = "RequestPasswordController";

    private static final String RESET_PASSWORD = "Reset_Password";
    private static final String RESET_PASSWORD_CONTROLLER = "ResetPasswordController";

    private static final String LOAD_USER_PAGE = "Load_User_Page";
    private static final String LOAD_USER_PAGE_CONTROLLER = "LoadUserPageController";

    private static final String LOAD_SHOP_PAGE = "Load_Shop_Page";
    private static final String LOAD_SHOP_PAGE_CONTROLLER = "LoadShopPageController";

    //    USER SECTION
    private static final String INFO = "Info";
    private static final String INFO_CONTROLLER = "InfoController";

    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";

    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";

    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckoutController";

    private static final String ORDER_DETAIL = "Order_Detail";
    private static final String ORDER_DETAIL_CONTROLLER = "OrderDetailController";

    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";

    //    ADMIN SECTION
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";

    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";

    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (CREATE_PAGE.equals(action)) {
                url = CREATE_PAGE_CONTROLLER;
            } else if (CREATE.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (REQUEST_PASSWORD_PAGE.equals(action)) {
                url = REQUEST_PASSWORD_PAGE_CONTROLLER;
            } else if (REQUEST_PASSWORD.equals(action)) {
                url = REQUEST_PASSWORD_CONTROLLER;
            } else if (RESET_PASSWORD.equals(action)) {
                url = RESET_PASSWORD_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (VIEW_CART.equals(action)) {
                url = VIEW_CART_PAGE;
            } else if (LOAD_USER_PAGE.equals(action)) {
                url = LOAD_USER_PAGE_CONTROLLER;
            } else if (LOAD_SHOP_PAGE.equals(action)) {
                url = LOAD_SHOP_PAGE_CONTROLLER;
            } else if (EDIT.equals(action)) {
                url = EDIT_CONTROLLER;
            } else if (REMOVE.equals(action)) {
                url = REMOVE_CONTROLLER;
            } else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            } else if (ORDER_DETAIL.equals(action)) {
                url = ORDER_DETAIL_CONTROLLER;
            } else if (INFO.equals(action)) {
                url = INFO_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
