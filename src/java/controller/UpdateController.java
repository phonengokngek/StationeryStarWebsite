package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductDAO;
import model.UserDAO;
import model.UserDTO;
import product.ProductDTO;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");

            String productID = request.getParameter("productID");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            
            if (userID != null) {
                int userIDStr = Integer.parseInt(userID);
                UserDAO dao = new UserDAO();
                UserDTO user = new UserDTO(userIDStr, fullName, roleID);
                boolean checkUpdate = dao.update(user);
                HttpSession session = request.getSession();
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                if (checkUpdate) {
                    if (loginUser.getUserID() == userIDStr) {
                        loginUser.setFullName(fullName);
                        loginUser.setRoleID(roleID);
                        session.setAttribute("LOGIN_USER", loginUser);
                    }
                    url = SUCCESS;
                }
            } else if (productID != null) {
                int productIDStr = Integer.parseInt(productID);
                int quantityStr = Integer.parseInt(quantity);
                double priceStr = Double.parseDouble(price);
                ProductDAO dao = new ProductDAO();
                ProductDTO product= new ProductDTO(productIDStr, quantityStr, name, priceStr);
                dao.update(product);
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
