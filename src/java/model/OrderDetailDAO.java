package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtils;

public class OrderDetailDAO {

    private static final String GET_ORDER_DETAIL_BY_USERID = "SELECT \n"
            + "    OrderDetail.orderID, \n"
            + "    OrderDetail.productID, \n"
            + "    OrderDetail.price, \n"
            + "    OrderDetail.quantity,\n"
            + "    OrderDetail.status,\n"
            + "    [Order].total,\n"
            + "    [Order].date,\n"
            + "    Products.name\n"
            + "FROM \n"
            + "    OrderDetail\n"
            + "INNER JOIN \n"
            + "    [Order] ON OrderDetail.orderID = [Order].orderID\n"
            + "INNER JOIN \n"
            + "    Products ON OrderDetail.productID = Products.productID\n"
            + "WHERE \n"
            + "    [Order].userID = ?\n"
            + "    AND [Order].status = 1\n"
            + "    AND OrderDetail.status = 1\n"
            + "    AND Products.status = 1\n"
            + "    AND CAST([Order].date AS DATE) = (\n"
            + "        SELECT MAX(CAST(date AS DATE))\n"
            + "        FROM [Order]\n"
            + "        WHERE userID = ?\n"
            + "    )\n"
            + "ORDER BY \n"
            + "    [Order].date DESC, OrderDetail.orderID";

    public List<OrderDetailDTO> getOrderDetailByUserID(int userID) throws SQLException, NamingException, ClassNotFoundException {
        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_DETAIL_BY_USERID);
                ptm.setInt(1, userID);
                ptm.setInt(2, userID);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    int productID = rs.getInt("productID");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    double total = rs.getDouble("total");
                    Timestamp timestamp = rs.getTimestamp("date");
                    Date date = new Date(timestamp.getTime());
                    String name = rs.getString("name");
                    orderDetails.add(new OrderDetailDTO(orderID, productID, quantity, price, total, date, name));
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

        return orderDetails;
    }

}
