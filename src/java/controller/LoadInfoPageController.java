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

@WebServlet(name = "LoadInfoPageController", urlPatterns = {"/LoadInfoPageController"})
public class LoadInfoPageController extends HttpServlet {

    private static final String ERROR = "productInfo.jsp";
    private static final String SUCCESS = "productInfo.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productIDStr = request.getParameter("productID");
            String quantityStr = request.getParameter("quantity");
            String indexTabStr = request.getParameter("indexTab");

            if (productIDStr != null && !productIDStr.isEmpty()) {
                int productID = Integer.parseInt(productIDStr);
                int quantity = Integer.parseInt(quantityStr);

                ProductDAO productDAO = new ProductDAO();
                ProductDTO productInfo = productDAO.getProductByID(productID);

                if (productInfo != null) {
                    productInfo.setQuantity(quantity);
                    request.setAttribute("PRODUCT_INFO", productInfo);

                    if (indexTabStr == null || indexTabStr.isEmpty()) {
                        indexTabStr = "1";
                    }
                    int index = Integer.parseInt(indexTabStr);
                    int count = productDAO.getTotalProduct();
                    int endTab = count / 12;
                    if (count % 12 != 0) {
                        endTab++;
                    }

                    List<ProductDTO> listProductEachTab = productDAO.pagingProduct(index);
                    request.setAttribute("LIST_PRODUCT_EACH_TAB", listProductEachTab);
                    request.setAttribute("END_TAB", endTab);
                    request.setAttribute("TAB", index);

                    url = SUCCESS + "?productID=" + productIDStr + "&quantity=" + quantityStr + "&indexTab=" + indexTabStr;
                } else {
                    request.setAttribute("ERROR", "Product not found");
                }
            } else {
                request.setAttribute("ERROR", "Invalid product ID");
            }
        } catch (NumberFormatException e) {
            log("Error at LoadInfoPageController: " + e.toString());
            request.setAttribute("ERROR", "Invalid product ID, quantity, or page number format");
        } catch (Exception e) {
            log("Error at LoadInfoPageController: " + e.toString());
            request.setAttribute("ERROR", "An error occurred while loading product information");
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
