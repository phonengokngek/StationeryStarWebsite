package model;

import product.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtils;

public class ProductDAO {

    private static final String LIST_PRODUCT_DEFAULT_ADMIN_PAGE = "SELECT productID, quantity, name, image, price FROM Products WHERE status = 1";
    private static final String LIST_PRODUCT_DEFAULT = "SELECT productID, name, image, price FROM Products WHERE status = 1";
    private static final String TOTAL_PRODUCT = "SELECT COUNT(*) FROM Products WHERE status = 1";
    private static final String PAGING_PRODUCT = "SELECT productID, name, image, price FROM Products WHERE status = 1 ORDER BY productID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
    private static final String SEARCH = "SELECT productID, quantity, name, image, price FROM Products WHERE status = 1 AND name LIKE ?";
    private static final String UPDATE = "UPDATE Products SET name = ? , quantity = ? , price = ? WHERE productID = ?";
    private static final String DELETE = "UPDATE Products SET status = 0 WHERE productID = ?";
    private static final String GET_PRODUCT_INFO_BY_ID = "SELECT quantity, name, image, price FROM Products WHERE status = 1 AND productID = ?";
    
    
    public boolean delete(int productID) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, productID);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean update(ProductDTO product) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getName());
                ptm.setInt(2, product.getQuantity());
                ptm.setDouble(3, product.getPrice());
                ptm.setInt(4, product.getProductID());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<ProductDTO> pagingProduct(int index) throws SQLException, ClassNotFoundException, NamingException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGING_PRODUCT);
                ptm.setInt(1, (index - 1) * 12);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    Double price = rs.getDouble("price");
                    listProduct.add(new ProductDTO(productID, name, image, price));
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
        return listProduct;
    }

    public int getTotalProduct() throws ClassNotFoundException, SQLException, NamingException {
        int totalProduct = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOTAL_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    totalProduct = rs.getInt(1);
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
        return totalProduct;
    }

    public List<ProductDTO> getListProduct(String search) throws SQLException, ClassNotFoundException, NamingException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    int quantity = rs.getInt("quantity");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    Double price = rs.getDouble("price");
                    listProduct.add(new ProductDTO(productID, quantity, name, image, price));
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
        return listProduct;
    }

    public List<ProductDTO> getListProductDefault() throws SQLException, ClassNotFoundException, NamingException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_PRODUCT_DEFAULT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    Double price = rs.getDouble("price");
                    listProduct.add(new ProductDTO(productID, name, image, price));
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
        return listProduct;
    }
    
    public List<ProductDTO> getListProductDefaultAdminPage() throws SQLException, ClassNotFoundException, NamingException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_PRODUCT_DEFAULT_ADMIN_PAGE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    int quantity = rs.getInt("quantity");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    Double price = rs.getDouble("price");
                    listProduct.add(new ProductDTO(productID, quantity, name, image, price));
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
        return listProduct;
    }
    
    public ProductDTO getProductByID(int productID) throws SQLException, ClassNotFoundException, NamingException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_INFO_BY_ID);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    Double price = rs.getDouble("price");
                    product = new ProductDTO(productID, quantity, name, image, price);
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
        return product;
    }
}
