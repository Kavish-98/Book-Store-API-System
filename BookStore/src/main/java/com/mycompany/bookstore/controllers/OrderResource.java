/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.controllers;

/**
 *
 * @author Kavish Weerasingha
 */
import com.mycompany.bookstore.Database;
import com.mycompany.bookstore.exceptions.CustomerNotFoundException;
import com.mycompany.bookstore.exceptions.InvalidInputException;
import com.mycompany.bookstore.models.Customer;
import com.mycompany.bookstore.models.Order;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;


@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {    
    private static int idCounter = 1;

    @POST
    public Response createOrder(@PathParam("customerId") int customerId, Order order) {
        Customer customer = Database.customers.get(customerId);
        
        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Customer ID");
        }
        
        order.setOrderId(idCounter++);
        
        List<Order> ordersCustomer;
        if (Database.orders.containsKey(customerId)) {
            ordersCustomer = Database.orders.get(customerId);
        } else {
            ordersCustomer = new ArrayList<>();
            Database.orders.put(customerId, ordersCustomer);
        }

        ordersCustomer.add(order);
        
        ordersCustomer.add(order);
        Database.orders.put(customerId, ordersCustomer);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    public Response getOrders(@PathParam("customerId") int customerId) {
        Customer customer = Database.customers.get(customerId);
        
        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Customer ID");
        }
        
        List<Order> customerOrders = Database.orders.get(customerId);
        if (customerOrders == null) {
            throw new InvalidInputException("Cusomer doesn't have any orders");
        }
        return Response.ok(customerOrders).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        Customer customer = Database.customers.get(customerId);
        
        if (customer == null) {
            throw new CustomerNotFoundException("Invalid Customer ID");
        }
        
        List<Order> customerOrders = Database.orders.get(customerId);
        if (customerOrders == null) {
            throw new InvalidInputException("Cusomer doesn't have any orders");
        }
        
        // Iterate through each order in the customer's order list
        for (Order order : customerOrders) {
            // Check if the order ID matches the given orderId
            if (order.getOrderId() == orderId) {
                // If a match is found, return a 200 OK response with the order data
                return Response.ok(order).build();
            }
        }

        throw new InvalidInputException("Cusomer doesn't have any orders");
    }
}