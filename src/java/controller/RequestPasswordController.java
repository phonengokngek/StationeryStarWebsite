package controller;

import constant.ResetService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TokenForgetDAO;
import model.TokenForgetPasswordDTO;
import model.UserDAO;
import model.UserDTO;

@WebServlet(name = "RequestPasswordController", urlPatterns = {"/RequestPasswordController"})
public class RequestPasswordController extends HttpServlet {

    private static final String ERROR = "requestPassword.jsp";
    private static final String SUCCESS = "requestPassword.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String email = request.getParameter("email");
            UserDAO dao = new UserDAO();
            UserDTO user = dao.getUserInfoByEmail(email);

            if (user == null) {
                request.setAttribute("ERROR", "Email doesn't exist");
                request.getRequestDispatcher(ERROR).forward(request, response);
            }

            ResetService service = new ResetService();
            String token = service.generateToken();

            String linkReset = "http://localhost:8080/StationeryStarWebsite/ResetPasswordController?token=" + token;

            TokenForgetPasswordDTO newTokenForget = new TokenForgetPasswordDTO(
                    user.getUserID(), false, token, service.expireDateTime());

            //send link to this email
            TokenForgetDAO daoToken = new TokenForgetDAO();
            boolean isInsert = daoToken.insertTokenForget(newTokenForget);
            if (!isInsert) {
                request.setAttribute("ERROR", "Have error in server");
                request.getRequestDispatcher(ERROR).forward(request, response);
            }
            boolean isSend = service.sendEmail(email, linkReset, user.getFullName());
            if (!isSend) {
                request.setAttribute("ERROR", "Can not send request");
                request.getRequestDispatcher(ERROR).forward(request, response);
            }
            request.setAttribute("SUCCESS", "Send request successfully");
            request.getRequestDispatcher(SUCCESS).forward(request, response);
        } catch (Exception e) {
            log("Error at RequestPasswordController: " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RequestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RequestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
