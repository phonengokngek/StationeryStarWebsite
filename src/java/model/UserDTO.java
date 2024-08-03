package model;

public class UserDTO {

    private int userID;
    private String fullName, roleID, password, email, phone, externalID, externalMethod;
    private boolean status;

    public UserDTO() {

    }

    public UserDTO(int userID, String fullName, String roleID, String password, String email, String phone, String externalID, String externalMethod, boolean status) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.externalID = externalID;
        this.externalMethod = externalMethod;
        this.status = status;
    }

    //getListUser, getListUserDefault
    public UserDTO(int userID, String fullName, String roleID, String password, String email, String phone, String externalID, String externalMethod) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.externalID = externalID;
        this.externalMethod = externalMethod;
    }

    //getEmailByUserID
    public UserDTO(String email) {
        this.email = email;
    }

    //getUserInfoByEmail
    public UserDTO(int userID, String fullName) {
        this.userID = userID;
        this.fullName = fullName;
    }

    //checkLogin, checkExternalLogin
    public UserDTO(int userID, String fullName, String roleID, String email, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.email = email;
        this.phone = phone;
    }

    //insert
    public UserDTO(String fullName, String password, String email, String phone) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    //update
    public UserDTO(int userID, String fullName, String roleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }

    public String getExternalMethod() {
        return externalMethod;
    }

    public void setExternalMethod(String externalMethod) {
        this.externalMethod = externalMethod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", fullName=" + fullName + ", roleID=" + roleID + ", password=" + password + ", email=" + email + ", phone=" + phone + ", externalID=" + externalID + ", externalMethod=" + externalMethod + ", status=" + status + '}';
    }

}
