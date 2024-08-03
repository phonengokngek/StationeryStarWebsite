package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import product.Cart;
import product.ProductDTO;
import utils.DBUtils;

public class OrderDAO {

    private static final String INSERT_ORDER = "INSERT INTO [Order] (userID, total, date, status) VALUES (?, ?, ?, ?)";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO OrderDetail (orderID, productID, price, quantity, status) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE Products SET quantity = quantity - ? WHERE productID = ?";
    private static final String CHECK_QUANTITY = "SELECT productID, quantity FROM Products WHERE productID = ?";

    public Map<Integer, Integer> checkStockAvailability(Cart cart) throws SQLException, ClassNotFoundException, NamingException {
        Map<Integer, Integer> insufficientStock = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_QUANTITY);
                for (Map.Entry<Integer, ProductDTO> entry : cart.getCart().entrySet()) {
                    int productID = entry.getKey();
                    int requestedQuantity = entry.getValue().getQuantity();
                    ptm.setInt(1, productID);
                    rs = ptm.executeQuery();
                    if (rs.next()) {
                        int stockQuantity = rs.getInt("quantity");
                        if (requestedQuantity > stockQuantity) {
                            insufficientStock.put(productID, stockQuantity);
                        }
                    } else {
                        // Product not found in database
                        insufficientStock.put(productID, 0);
                    }
                    rs.close();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return insufficientStock;
    }

    public int checkout(int userID, Cart cart) throws SQLException, NamingException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptmOrder = null;
        PreparedStatement ptmOrderDetail = null;
        PreparedStatement ptmProduct = null;
        ResultSet rs = null;
        int orderId = -1;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);

                // Create new order
                ptmOrder = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                ptmOrder.setInt(1, userID);
                ptmOrder.setDouble(2, cart.getTotal());
                ptmOrder.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ptmOrder.setBoolean(4, true);
                ptmOrder.executeUpdate();

                rs = ptmOrder.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }

                // Add order details and update product quantities
                ptmOrderDetail = conn.prepareStatement(INSERT_ORDER_DETAIL);
                ptmProduct = conn.prepareStatement(UPDATE_PRODUCT);

                for (ProductDTO product : cart.getCart().values()) {
                    // Add order detail
                    ptmOrderDetail.setInt(1, orderId);
                    ptmOrderDetail.setInt(2, product.getProductID());
                    ptmOrderDetail.setDouble(3, product.getPrice());
                    ptmOrderDetail.setInt(4, product.getQuantity());
                    ptmOrderDetail.setBoolean(5, true);
                    ptmOrderDetail.addBatch();

                    // Update product quantity
                    ptmProduct.setInt(1, product.getQuantity());
                    ptmProduct.setInt(2, product.getProductID());
                    ptmProduct.addBatch();
                }

                ptmOrderDetail.executeBatch();
                ptmProduct.executeBatch();

                conn.commit();
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptmOrder != null) {
                ptmOrder.close();
            }
            if (ptmOrderDetail != null) {
                ptmOrderDetail.close();
            }
            if (ptmProduct != null) {
                ptmProduct.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        return orderId;
    }
}
