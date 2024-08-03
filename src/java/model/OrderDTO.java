package model;

import java.sql.Timestamp;

public class OrderDTO {

    private int orderID, userID;
    private Timestamp date;
    private String total;
    private boolean status;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int userID, Timestamp date, String total, boolean status) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", userID=" + userID + ", date=" + date + ", total=" + total + ", status=" + status + '}';
    }

}
