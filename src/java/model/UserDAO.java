package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtils;

public class UserDAO {

    private static final String LOGIN = "SELECT userID, fullName, roleID, email, phone FROM Users WHERE status = 1 AND email = ? AND password = ?";
    private static final String EXTERNAL_LOGIN = "SELECT userID, fullName, roleID, email, phone FROM Users WHERE status = 1 AND externalID = ?";
    private static final String DELETE = "UPDATE Users SET status = 0 WHERE userID = ?";
    private static final String UPDATE = "UPDATE Users SET fullName = ? , roleID = ? WHERE userID = ?";
    private static final String INSERT = "INSERT INTO Users (fullName, password, email, phone) VALUES (?, ?, ?, ?)";
    private static final String SEARCH = "SELECT userID, fullName, roleID, password, email, phone, externalID, externalMethod FROM Users WHERE status = 1 AND fullName LIKE ?";
    private static final String LIST_USER_DEFAULT = "SELECT userID, fullName, roleID, password, email, phone, externalID, externalMethod FROM Users WHERE status = 1";

    private static final String GET_USER_INFO_BY_EMAIL = "SELECT userID, fullName FROM Users WHERE status = 1 AND email = ?";
    private static final String GET_EMAIL_BY_USER_ID = "SELECT email FROM Users WHERE status = 1 AND userID = ?";
    private static final String UPDATE_PASSWORD = "UPDATE Users SET password = ? WHERE email = ?";
    
     public boolean updatePassword(String email, String password) throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PASSWORD);
                ptm.setString(1, password);
                ptm.setString(2, email);
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

    public UserDTO getEmailByUserID(int userID) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_EMAIL_BY_USER_ID);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String email = rs.getNString("email");
                    user = new UserDTO(email);
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
        return user;
    }

    public UserDTO getUserInfoByEmail(String email) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_INFO_BY_EMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String fullName = rs.getNString("fullName");
                    user = new UserDTO(userID, fullName);
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
        return user;
    }

    public boolean insert(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getPhone());
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

    public boolean delete(int userID) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, userID);
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

    public boolean update(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setInt(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
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

    public List<UserDTO> getListUserDefault() throws SQLException, ClassNotFoundException, NamingException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_USER_DEFAULT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("userID");
                    String fullName = rs.getNString("fullName");
                    String roleID = rs.getNString("roleID");
                    String password = rs.getNString("password");
                    String email = rs.getNString("email");
                    String phone = rs.getNString("phone");
                    String externalID = rs.getNString("externalID");
                    String externalMethod = rs.getNString("externalMethod");
                    listUser.add(new UserDTO(userID, fullName, roleID, password, email, phone, externalID, externalMethod));
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
        return listUser;
    }

    public List<UserDTO> getListUser(String search) throws SQLException, ClassNotFoundException, NamingException {
        List<UserDTO> listUser = new ArrayList<>();
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
                    int userID = rs.getInt("userID");
                    String fullName = rs.getNString("fullName");
                    String roleID = rs.getNString("roleID");
                    String password = rs.getNString("password");
                    String email = rs.getNString("email");
                    String phone = rs.getNString("phone");
                    String externalID = rs.getNString("externalID");
                    String externalMethod = rs.getNString("externalMethod");
                    listUser.add(new UserDTO(userID, fullName, roleID, password, email, phone, externalID, externalMethod));
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
        return listUser;
    }

    public UserDTO checkExternalLogin(String externalID) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EXTERNAL_LOGIN);
                ptm.setString(1, externalID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String fullName = rs.getNString("fullName");
                    String roleID = rs.getNString("roleID");
                    String email = rs.getNString("email");
                    String phone = rs.getNString("phone");
                    user = new UserDTO(userID, fullName, roleID, email, phone);
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
        return user;
    }

    public UserDTO checkLogin(String email, String password) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, email);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String fullName = rs.getNString("fullName");
                    String roleID = rs.getNString("roleID");
                    String phone = rs.getNString("phone");
                    user = new UserDTO(userID, fullName, roleID, email, phone);
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
        return user;
    }

}
