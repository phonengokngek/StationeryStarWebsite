package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InfoController", urlPatterns = {"/InfoController"})
public class InfoController extends HttpServlet {

    private static final String ERROR = "LoadInfoPageController";
    private static final String SUCCESS = "LoadInfoPageController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productIDStr = request.getParameter("productID");
            String quantityStr = request.getParameter("quantity");
            String indexTab = request.getParameter("indexTab");

            if (productIDStr != null && !productIDStr.isEmpty()) {
                int productID = Integer.parseInt(productIDStr);

                if (quantityStr == null || quantityStr.isEmpty()) {
                    quantityStr = "1";
                }
                
                int quantity = Integer.parseInt(quantityStr);

                if (indexTab == null || indexTab.isEmpty()) {
                    indexTab = "1";
                }

                url = SUCCESS + "?productID=" + productID + "&quantity=" + quantity + "&indexTab=" + indexTab;
            } else {
                request.setAttribute("ERROR", "Invalid product ID");
            }
        } catch (NumberFormatException e) {
            log("Error at InfoController: " + e.toString());
            request.setAttribute("ERROR", "Invalid product ID or quantity format");
        } catch (Exception e) {
            log("Error at InfoController: " + e.toString());
            request.setAttribute("ERROR", "An error occurred while processing your request");
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
