package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDAO;
import model.UserDTO;
import model.UserError;

@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean checkValidation = true;
        UserError userError = new UserError();
        UserDAO dao = new UserDAO();
        try {
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String confirmPassword = request.getParameter("confirmPassword");
            String phone = request.getParameter("phone");
        
            if (fullName.length() < 5 || fullName.length() > 20) {
                userError.setFullNameError("Full name in range [5, 20]");
                checkValidation = false;
            }
            if (email.length() < 10 || email.length() > 30) {
                userError.setEmailError("Email in range [10, 30]");
                checkValidation = false;
            }
            if (!confirmPassword.equals(password)) {
                userError.setConfirmError("2 passwords are not same");
                checkValidation = false;
            }
            if (phone.length() != 10) {
                userError.setPhoneError("Phone number must have 10 numbers");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDTO user = new UserDTO(fullName, password, email, phone);
                boolean checkInsert = dao.insert(user);
                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    userError.setError("USER_ERROR");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
            if (e.getMessage().contains("Email has existed")) {
                userError.setEmailError("Duplicate email");
                request.setAttribute("USER_ERROR", userError);
                checkValidation = false;
            } else if (e.getMessage().contains("Phone has existed")) {
                userError.setPhoneError("Duplicate phone");
                request.setAttribute("USER_ERROR", userError);
                checkValidation = false;
            } else if (e.getMessage().contains("Both Email and Phone have existed")) {
                userError.setError("Both Email and Phone have existed");
                request.setAttribute("USER_ERROR", userError);
                checkValidation = false;
            }
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
