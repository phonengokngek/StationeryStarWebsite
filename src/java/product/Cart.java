package product;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, ProductDTO> cart;

    public Cart() {
    }

    public Cart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<Integer, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public boolean add(ProductDTO product) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(product.getProductID())) {
                int currentQuantity = this.cart.get(product.getProductID()).getQuantity();
                product.setQuantity(currentQuantity + product.getQuantity());
            }
            this.cart.put(product.getProductID(), product);
            check = true;
        } catch (Exception e) {

        }
        return check;
    }

    public double getTotal() {
        double total = 0;
        for (ProductDTO product : this.cart.values()) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    public ProductDTO findProductByID(int productID) {
        if (cart != null) {
            for (ProductDTO product : cart.values()) {
                if (product.getProductID() == productID) {
                    return product;
                }
            }
        }
        return null;
    }

    public void editProductQuantityByID(ProductDTO product) {
        if (cart != null) {
            for (ProductDTO p : cart.values()) {
                if (p.getProductID() == product.getProductID()) {
                    p.setQuantity(product.getQuantity());
                }
            }
        }
    }

    public boolean remove(int productID) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(productID)) {
                    this.cart.remove(productID);
                    check = true;
                }
            }
        } catch (Exception e) {

        }
        return check;
    }

    public boolean isEmpty() {
        return cart == null || cart.isEmpty();
    }

    public int size() {
        return cart.size();
    }
}
