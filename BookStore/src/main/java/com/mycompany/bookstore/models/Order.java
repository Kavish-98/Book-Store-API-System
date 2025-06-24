/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kavish Weerasingha
 */

public class Order {
    private int orderId;
    private CartItem cartItem;
    private String status; 
    
    public Order() {
        
    }
    
    public Order(CartItem cartItem) {
        this.cartItem = cartItem;
        this.status = "Pending";
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
    
    
}
