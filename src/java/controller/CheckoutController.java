package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderDAO;
import model.UserDTO;
import product.Cart;
import product.ProductDTO;

@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "OrderDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        Cart cart = (Cart) session.getAttribute("CART");
        try {
            OrderDAO orderDAO = new OrderDAO();
            Map<Integer, Integer> insufficientStock = orderDAO.checkStockAvailability(cart);

            if (insufficientStock.isEmpty()) {
                int orderId = orderDAO.checkout(user.getUserID(), cart);
                if (orderId > 0) {
                    session.removeAttribute("CART");
                    session.setAttribute("SIZE", 0);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Checkout failed. Please try again.");
                }
            } else {
                String errorMessage = "Insufficient stock for some items: ";
                for (Map.Entry<Integer, Integer> entry : insufficientStock.entrySet()) {
                    ProductDTO product = cart.getCart().get(entry.getKey());
                    errorMessage += product.getName() + " (Available: " + entry.getValue() + ")";
                }
                request.setAttribute("ERROR", errorMessage);
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
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
