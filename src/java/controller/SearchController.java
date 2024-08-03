package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductDAO;
import product.ProductDTO;
import model.UserDAO;
import model.UserDTO;

@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private static final String ERROR = "admin.jsp";
    private static final String SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("search");
            String searchProduct = request.getParameter("searchProduct");
            String activeTab = request.getParameter("activeTab");

            if (activeTab != null) {
                HttpSession session = request.getSession();
                session.setAttribute("ACTIVE_TAB", activeTab);
            } else {
                HttpSession session = request.getSession();
                activeTab = (String) session.getAttribute("ACTIVE_TAB");
                if (activeTab == null) {
                    activeTab = "users-content";
                }
            }
            request.setAttribute("ACTIVE_TAB", activeTab);

            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = null;
            if (search != null && !search.trim().isEmpty()) {
                listUser = dao.getListUser(search);
            } else {
                listUser = dao.getListUserDefault();
            }
            if (!listUser.isEmpty()) {
                request.setAttribute("LIST_USER", listUser);
            }

            ProductDAO daoProduct = new ProductDAO();
            List<ProductDTO> listProduct = null;
            if (searchProduct != null && !searchProduct.trim().isEmpty()) {
                listProduct = daoProduct.getListProduct(searchProduct);
            } else {
                listProduct = daoProduct.getListProductDefaultAdminPage();
            }
            if (!listProduct.isEmpty()) {
                request.setAttribute("LIST_PRODUCT", listProduct);
            }

            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
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
