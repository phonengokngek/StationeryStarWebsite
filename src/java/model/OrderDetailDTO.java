package model;

import java.sql.Date;

public class OrderDetailDTO {

    private int orderID, productID, quantity;
    private double price, total;
    private Date date;
    private String name;
    private boolean status;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderID, int productID, int quantity, double price, double total, Date date, String name, boolean status) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.date = date;
        this.name = name;
        this.status = status;
    }
    
    //getOrderDetailByUserID
    public OrderDetailDTO(int orderID, int productID, int quantity, double price, double total, Date date, String name) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.date = date;
        this.name = name;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + ", price=" + price + ", total=" + total + ", date=" + date + ", name=" + name + ", status=" + status + '}';
    }

}
