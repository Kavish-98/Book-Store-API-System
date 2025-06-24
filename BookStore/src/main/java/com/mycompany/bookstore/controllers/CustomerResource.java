/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.controllers;
import com.mycompany.bookstore.Database;
import com.mycompany.bookstore.exceptions.CustomerNotFoundException;
import com.mycompany.bookstore.exceptions.InvalidInputException;
import com.mycompany.bookstore.models.Customer;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 *
 * @author Kavish Weerasingha
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private static int idCounter = 1;

    @POST
    public Response createCustomer(Customer customer) {
        if (customer.getName().isEmpty() || customer.getEmail().isEmpty() || customer.getPassword().isEmpty()) {
            throw new InvalidInputException("Please enter valid name, email and password");
        }
        
        customer.setId(idCounter++);
        Database.customers.put(customer.getId(), customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    public Collection<Customer> getCustomers() {
        return Database.customers.values();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") int id) {
        Customer customer = Database.customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        if (!Database.customers.containsKey(id)) {
            throw new CustomerNotFoundException("Customer not found");
        }
        customer.setId(id);
        Database.customers.put(id, customer);
        return Response.ok(customer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        if (!Database.customers.containsKey(id)) {
            throw new CustomerNotFoundException("Customer not found");
        }
        Database.customers.remove(id);
        return Response.noContent().build();
    }
} 