package product;

public class ProductDTO {

    private int productID, quantity;
    private String name, image;
    private double price;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(int productID, int quantity, String name, String image, double price, boolean status) {
        this.productID = productID;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
        this.price = price;
        this.status = status;
    }

    //update
    public ProductDTO(int productID, int quantity, String name, double price) {
        this.productID = productID;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    // add, getProductInfoByID, getListProductDefaultAdminPage
    public ProductDTO(int productID, int quantity, String name, String image, double price) {
        this.productID = productID;
        this.quantity = quantity;
        this.name = name;
        this.image = image;
        this.price = price;
    }
    
    //getListProduct, pagingProduct
     public ProductDTO(int productID, String name, String image, double price) {
        this.productID = productID;
        this.name = name;
        this.image = image;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", quantity=" + quantity + ", name=" + name + ", image=" + image + ", price=" + price + ", status=" + status + '}';
    }

}
