/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.controllers;
import com.mycompany.bookstore.Database;
import com.mycompany.bookstore.exceptions.CartNotFoundException;
import com.mycompany.bookstore.exceptions.CustomerNotFoundException;
import com.mycompany.bookstore.exceptions.InvalidInputException;
import com.mycompany.bookstore.exceptions.OutOfStockException;
import com.mycompany.bookstore.models.Book;
import com.mycompany.bookstore.models.BookOrder;
import com.mycompany.bookstore.models.CartItem;
import com.mycompany.bookstore.models.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 *
 * @author Kavish Weerasingha
 */

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    @POST
    @Path("/items")
    public Response addItem(@PathParam("customerId") int customerId, BookOrder bookOrder) {
        Book bookUerReq = Database.books.get(bookOrder.getBookId());
        Customer customer = Database.customers.get(customerId);
        
        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Customer ID");
        }
        
        if (bookUerReq == null) {
            throw new InvalidInputException("Invalid book ID");
        }
        
        if (bookUerReq.getStock() <= 0) {
            throw new OutOfStockException("Out of stock only " + bookUerReq.getStock() + " in stock");
        }
        
        bookUerReq.setStock(bookUerReq.getStock() - 1);
        Database.books.put(bookUerReq.getId(), bookUerReq);
         
         CartItem cart = Database.carts.containsKey(customerId)
            ? Database.carts.get(customerId)
            : Database.carts.put(customerId, new CartItem(customerId));
         
        Database.carts.get(customerId).addBook(bookUerReq);
        return Response.ok(bookUerReq).build();
    }

    @GET
    public Response getCart(@PathParam("customerId") int customerId) {
        CartItem cart = Database.carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer " + customerId);
        }
        return Response.ok(cart).build();
    }

    @PUT
    @Path("/items/{bookId}")
    public Response updateItem(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId, Book book) {
        CartItem cart = Database.carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer " + customerId);    
        }
        book.setId(bookId);
        cart.getBooks().removeIf(b -> b.getId() == bookId);
        cart.addBook(book);
        return Response.ok(cart).build();
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response removeItem(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        Book bookUerReq = Database.books.get(bookId);
        Customer customer = Database.customers.get(customerId);
        
        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Customer ID");
        }
        
        if (bookUerReq == null) {
            throw new InvalidInputException("Invalid book ID");
        }
        
        bookUerReq.setStock(bookUerReq.getStock() + 1);
        Database.books.put(bookUerReq.getId(), bookUerReq);
        
        CartItem cart = Database.carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer " + customerId);    
        }
        cart.getBooks().removeIf(b -> b.getId() == bookId);
        return Response.ok(cart).build();
    }
}