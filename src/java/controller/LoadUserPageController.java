package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductDAO;
import product.ProductDTO;

@WebServlet(name = "LoadUserPageController", urlPatterns = {"/LoadUserPageController"})
public class LoadUserPageController extends HttpServlet {

    private static final String ERROR = "user.jsp";
    private static final String SUCCESS = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //SHOWING PRODUCT SECTION
            ProductDAO daoProduct = new ProductDAO();
            List<ProductDTO> listProduct = daoProduct.getListProductDefault();
            request.setAttribute("LIST_PRODUCT", listProduct);

            //PRODUCT SHOP SECTION
            String indexTabStr = request.getParameter("indexTab");
            if (indexTabStr == null || indexTabStr.isEmpty()) {
                indexTabStr = "1";
            }
            int index = Integer.parseInt(indexTabStr);
            int count = daoProduct.getTotalProduct();
            int endTab = count / 12;
            if (count % 12 != 0) {
                endTab++;
            }
            List<ProductDTO> listProductEachTab = daoProduct.pagingProduct(index);
            request.setAttribute("LIST_PRODUCT_EACH_TAB", listProductEachTab);
            request.setAttribute("END_TAB", endTab);
            request.setAttribute("TAB", index);
            url = SUCCESS + "?indexTab" + indexTabStr;
        } catch (Exception e) {
            log("Error at LoadController: " + e.toString());
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
