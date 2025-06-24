/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.controllers;
import com.mycompany.bookstore.Database;
import com.mycompany.bookstore.exceptions.AuthorNotFoundException;
import com.mycompany.bookstore.exceptions.BookNotFoundException;
import com.mycompany.bookstore.exceptions.InvalidInputException;
import com.mycompany.bookstore.models.Author;
import com.mycompany.bookstore.models.Book;
import java.time.Year;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*; 

/**
 *
 * @author Kavish Weerasingha
 */
@Path("/books")
public class BookResource {
    private static int idCounter = 1;

    // POST /books
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        Author auth = Database.authors.get(book.getAuthorId());
        
        if (auth == null) {
            throw new AuthorNotFoundException("Invalid Author ID");
        }
        
        if (book.getPublicationYear() > Year.now().getValue()) {
            throw new InvalidInputException("Please enter valid publication year");
        }
        
        if (book.getTitle() == null || book.getIsbn() == null || book.getPrice() < 0) {
            throw new InvalidInputException("Please enter valid author, book title, price and ISBN");
        }
        
        book.setId(idCounter++);
        Database.books.put(book.getId(), book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    // GET /books
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Book> getBooks() {
        return Database.books.values();
    }

    // GET /books/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") int id) {
        Book book = Database.books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Unable to find the book");
        }
        return Response.ok(book).build();
    }

    // PUT /books/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, Book book) {
        if (!Database.books.containsKey(id)) {
            throw new BookNotFoundException("Unable to find the book");
        }
        
        Author auth = Database.authors.get(book.getAuthorId());
        
        if (auth == null) {
            throw new AuthorNotFoundException("Invalid Author ID");
        }
        
        if (book.getPublicationYear() > Year.now().getValue()) {
            throw new InvalidInputException("Please enter valid publication year");
        }
        
        if (book.getTitle() == null || book.getIsbn() == null || book.getPrice() < 0) {
            throw new InvalidInputException("Please enter valid author, book title, price and ISBN");
        }
        
        book.setId(id);
        Database.books.put(id, book);
        return Response.ok(book).build();
    }

    // DELETE /books/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        if (!Database.books.containsKey(id)) {
            throw new BookNotFoundException("Unable to find the book");
        }
        Database.books.remove(id);
        return Response.status(Response.Status.OK).build();
    }
} 
