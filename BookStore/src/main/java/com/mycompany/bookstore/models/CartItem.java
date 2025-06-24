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
public class CartItem {
    private int customerId;
    private List<Book> books;
    
    public CartItem() {
        
    }

    public CartItem(int customerId) {
        this.customerId = customerId;
        this.books = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    } 
}
