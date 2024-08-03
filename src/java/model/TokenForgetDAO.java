package model;

import model.TokenForgetPasswordDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.naming.NamingException;
import utils.DBUtils;

public class TokenForgetDAO {

    String TOKEN_FORGET = "INSERT INTO tokenForgetPassword (token, expiryTime, isUsed, userId)VALUES(?, ?, ?, ?)";
    String TOKEN_PASSWORD = "SELECT id, userID, isUsed, token, expiryTime FROM tokenForgetPassword WHERE token = ?";
    String UPDATE_STATUS = "UPDATE tokenForgetPassword SET isUsed = ? WHERE token = ?";

    public String getFormatDate(LocalDateTime myDateObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public boolean insertTokenForget(TokenForgetPasswordDTO tokenForget) throws SQLException, ClassNotFoundException, NamingException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(TOKEN_FORGET);
            ptm.setString(1, tokenForget.getToken());
            ptm.setTimestamp(2, Timestamp.valueOf(getFormatDate(tokenForget.getExpiryTime())));
            ptm.setBoolean(3, tokenForget.isIsUsed());
            ptm.setInt(4, tokenForget.getUserID());
            checkInsert = ptm.executeUpdate() > 0 ? true : false;
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkInsert;
    }

    public TokenForgetPasswordDTO getTokenPassword(String token) throws SQLException, ClassNotFoundException, NamingException {
        TokenForgetPasswordDTO tokenForgetPassword = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOKEN_PASSWORD);
                ptm.setString(1, token);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int userID = rs.getInt("userID");
                    boolean isUsed = rs.getBoolean("isUsed");
                    LocalDateTime expiryTime = rs.getTimestamp("expiryTime").toLocalDateTime();
                    tokenForgetPassword = new TokenForgetPasswordDTO(id, userID, isUsed, token, expiryTime);
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
        return tokenForgetPassword;
    }

    public boolean updateStatus(TokenForgetPasswordDTO token) throws ClassNotFoundException, SQLException, NamingException {
        System.out.println("token = " + token);
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS);
                ptm.setBoolean(1, token.isIsUsed());
                ptm.setString(2, token.getToken());
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

}
