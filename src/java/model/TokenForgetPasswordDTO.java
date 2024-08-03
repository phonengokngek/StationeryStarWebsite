package model;

import java.time.LocalDateTime;


public class TokenForgetPasswordDTO {
    private int id, userID;
    private boolean isUsed;
    private String token;
    private LocalDateTime expiryTime;

    public TokenForgetPasswordDTO() {
    }

    //getTokenPassword
    public TokenForgetPasswordDTO(int id, int userID, boolean isUsed, String token, LocalDateTime expiryTime) {
        this.id = id;
        this.userID = userID;
        this.isUsed = isUsed;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public TokenForgetPasswordDTO(int userID, boolean isUsed, String token, LocalDateTime expiryTime) {
        this.userID = userID;
        this.isUsed = isUsed;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "TokenForgetPasswordDTO{" + "id=" + id + ", userID=" + userID + ", isUsed=" + isUsed + ", token=" + token + ", expiryTime=" + expiryTime + '}';
    }
    
    
}
