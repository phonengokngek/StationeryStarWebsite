package model;

public class OrderDetail {

    private int orderID, productID;
    private double price;
    private String total;
    private boolean status;

    public OrderDetail() {
    }

    public OrderDetail(int orderID, int productID, double price, String total, boolean status) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.total = total;
        this.status = status;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return "OrderDetail{" + "orderID=" + orderID + ", productID=" + productID + ", price=" + price + ", total=" + total + ", status=" + status + '}';
    }

}
