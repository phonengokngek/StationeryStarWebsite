package controller;

import constant.GoogleLogin;
import constant.FacebookLogin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDAO;
import model.UserDTO;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String USER_PAGE = "LoadUserPageController";
    private static final String ADMIN_PAGE = "SearchController";
    private static final String ERROR = "login.jsp";
    private static final String US = "US";
    private static final String AD = "AD";
    private static final String GOOGLE = "Google";
    private static final String FACEBOOK = "Facebook";
    private static final String ERROR_MESSAGE = "YOUR ROLE IS NOT SUPPORTED";
    private static final String INCORRECT_MESSAGE = "INCORRECT USERID OR PASSWORD";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            String error = request.getParameter("error");
            String state = request.getParameter("state");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = null;

            if (state != null) {
                // External login
                if (error != null) {
                    request.setAttribute("ERROR", ERROR_MESSAGE);
                    return;
                }

                String accessToken;
                String externalID;
                if (GOOGLE.equals(state)) {
                    GoogleLogin gg = new GoogleLogin();
                    accessToken = gg.getToken(code);
//                    GoogleAccount acc = gg.getUserInfo(accessToken);
//                    externalID = acc.getId();
                    externalID = gg.getUserInfo(accessToken).getId();
                } else if (FACEBOOK.equals(state)) {
                    FacebookLogin fb = new FacebookLogin();
                    accessToken = fb.getToken(code);
//                    FacebookAccount acc = fb.getUserInfo(accessToken);
//                    externalID = acc.getId();
                    externalID = fb.getUserInfo(accessToken).getId();
                } else {
                    throw new IllegalArgumentException("Invalid state");
                }
                loginUser = dao.checkExternalLogin(externalID);
            } else {
                // Default login
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                loginUser = dao.checkLogin(email, password);
            }

            if (loginUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                String roleID = loginUser.getRoleID();
                if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                } else if (US.equals(roleID)) {
                    url = USER_PAGE;
                } else {
                    request.setAttribute("ERROR", ERROR_MESSAGE);
                }
            } else {
                request.setAttribute("ERROR", INCORRECT_MESSAGE);
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
            request.setAttribute("ERROR", ERROR_MESSAGE);
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
