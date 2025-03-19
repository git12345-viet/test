package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Products, Integer> cartItems;

    // Constructor
    public Cart() {
        cartItems = new HashMap<>();
    }

    private Products getProductKeyById(int id) {
        for (Map.Entry<Products, Integer> entry : cartItems.entrySet()) {
            Products p = entry.getKey();
            if (p.getID() == id) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Cart c = new Cart();
        Products p1 = new Products(1, "name", "description", 0, "image", 0);
        Products p2 = new Products(2, "name", "description", 0, "image", 0);

        c.cartItems.put(p1, 1);

        c.addToCart(p1, 1);
        c.addToCart(p1, 1);
        c.addToCart(p2, 1);
        
        c.updateProductQuantityInCart(p1, 1);

        System.out.println(c);

    }

    public void addToCart(Products p, int quantity) {
        Products product = getProductKeyById(p.getID());

        if (product != null) {
            int currentQuantity = cartItems.get(product);
            cartItems.put(product, currentQuantity + quantity);
        } else {
            cartItems.put(p, quantity);
        }
    }

    public void updateProductQuantityInCart(Products p, int newQuantity) {
        Products productToUpdate = getProductKeyById(p.getID());

        if (productToUpdate != null) {
            cartItems.put(productToUpdate, newQuantity);
        }
    }

    public void removeFromCart(Products p) {
        Products productToRemove = getProductKeyById(p.getID());

        if (productToRemove != null) {
            cartItems.remove(productToRemove);
        }
    }

    public void clearCart() {
        cartItems.clear();
    }


    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (int quantity : cartItems.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Products, Integer> entry : cartItems.entrySet()) {
            Products product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    public Map<Products, Integer> getCartItems() {
        return cartItems;
    }
    
//     private Products getProductKeyById(int id) {
//        for (Map.Entry<Products, Integer> entry : cartItems.entrySet()) {
//            Products p = entry.getKey();
//            if (p.getID() == id) {
//                return p;
//            }
//        }
//        return null;
//    }

    @Override
    public String toString() {
        return "Cart{"
                + "cartItems=" + cartItems
                + '}';
    }

}
