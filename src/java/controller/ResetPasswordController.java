package controller;

import constant.ResetService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TokenForgetDAO;
import model.TokenForgetPasswordDTO;
import model.UserDAO;
import model.UserDTO;

@WebServlet(name = "ResetPasswordController", urlPatterns = {"/ResetPasswordController"})
public class ResetPasswordController extends HttpServlet {

    private static final String ERROR = "requestPassword.jsp";
    private static final String RESET_PASSWORD_PAGE = "resetPassword.jsp";
    private static final String LOGIN_PAGE = "login.html";

    private TokenForgetDAO daoToken = new TokenForgetDAO();
    private UserDAO dao = new UserDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            handleGetRequest(request, response);
        } else if ("POST".equalsIgnoreCase(method)) {
            handlePostRequest(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String token = request.getParameter("token");
            if (token != null) {
                TokenForgetPasswordDTO tokenForgetPassword = daoToken.getTokenPassword(token);
                ResetService service = new ResetService();

                if (tokenForgetPassword == null) {
                    request.setAttribute("ERROR", "Token invalid");
                    request.getRequestDispatcher(ERROR).forward(request, response);
                    return;
                }
                if (tokenForgetPassword.isIsUsed()) {
                    request.setAttribute("ERROR", "Token is used");
                    request.getRequestDispatcher(ERROR).forward(request, response);
                    return;
                }
                if (service.isExpireTime(tokenForgetPassword.getExpiryTime())) {
                    request.setAttribute("ERROR", "Token is expiry time");
                    request.getRequestDispatcher(ERROR).forward(request, response);
                    return;
                }

                UserDTO user = dao.getEmailByUserID(tokenForgetPassword.getUserID());
                request.setAttribute("EMAIL", user.getEmail());
                request.getSession().setAttribute("TOKEN", tokenForgetPassword.getToken());
                request.getRequestDispatcher(RESET_PASSWORD_PAGE).forward(request, response);
            } else {
                request.getRequestDispatcher(ERROR).forward(request, response);
            }
        } catch (Exception e) {
            log("Error at ResetPasswordController GET: " + e.toString());
            request.setAttribute("ERROR", "An error occurred");
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
    }

    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (!password.equals(confirmPassword)) {
                request.setAttribute("ERROR", "Confirm password must be the same as password");
                request.setAttribute("EMAIL", email);
                request.getRequestDispatcher(RESET_PASSWORD_PAGE).forward(request, response);
                return;
            }

            String tokenStr = (String) request.getSession().getAttribute("TOKEN");
            TokenForgetPasswordDTO tokenForgetPassword = daoToken.getTokenPassword(tokenStr);
            ResetService service = new ResetService();

            if (tokenForgetPassword == null || tokenForgetPassword.isIsUsed() || service.isExpireTime(tokenForgetPassword.getExpiryTime())) {
                request.setAttribute("ERROR", "Token is invalid, used, or expired");
                request.getRequestDispatcher(ERROR).forward(request, response);
                return;
            }

            boolean passwordUpdated = dao.updatePassword(email, password);
            if (passwordUpdated) {
                tokenForgetPassword.setIsUsed(true);
                boolean tokenUpdated = daoToken.updateStatus(tokenForgetPassword);
                if (tokenUpdated) {
                    request.getSession().removeAttribute("TOKEN");
                    request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                } else {
                    request.setAttribute("ERROR", "Password updated but failed to update token status. Please contact support.");
                    request.getRequestDispatcher(RESET_PASSWORD_PAGE).forward(request, response);
                }
            } else {
                request.setAttribute("ERROR", "Failed to update password");
                request.getRequestDispatcher(RESET_PASSWORD_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            log("Error at ResetPasswordController POST: " + e.toString());
            request.setAttribute("ERROR", "An error occurred");
            request.getRequestDispatcher(RESET_PASSWORD_PAGE).forward(request, response);
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
