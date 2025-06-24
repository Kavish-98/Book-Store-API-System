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
import com.mycompany.bookstore.exceptions.AuthorNotFoundException;
import com.mycompany.bookstore.exceptions.InvalidInputException;
import com.mycompany.bookstore.models.Author;
import com.mycompany.bookstore.models.Book;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*; 

@Path("/authors")
public class AuthorResource {
    private static int idCounter = 1;

    // POST /authors
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAuthor(Author author) {
        if (author.getName() == null || author.getName().length() < 3) {
            throw new InvalidInputException("Please enter valid author name");
        }

        author.setId(idCounter++);
        Database.authors.put(author.getId(), author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    // GET /authors
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Author> getAuthors() {
        return Database.authors.values();
    }

    // GET /authors/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthor(@PathParam("id") int id) {
        Author author = Database.authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found");
        }
        return Response.ok(author).build();
    }

    // PUT /authors/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAuthor(@PathParam("id") int id, Author author) {
        if (!Database.authors.containsKey(id)) {
            throw new AuthorNotFoundException("Author not found");
        }
        
        if (author.getName() == null || author.getName().length() < 3) {
            throw new InvalidInputException("Please enter valid author name");
        }
        author.setId(id);
        Database.authors.put(id, author);
        return Response.ok(author).build();
    }
    
    
    // GET /authors/{id}/books
    @GET
    @Path("/{id}/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(@PathParam("id") int id) {
        if (!Database.authors.containsKey(id)) {
            throw new AuthorNotFoundException("Author not found");
        }
        
        List<Book> result = new ArrayList<>();
        for (Book book : Database.books.values()) {
            if (book.getAuthorId() == id) {
                result.add(book);
            }
        }
        
        return Response.ok(result).build();
    }

    // DELETE /authors/{id}   
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        if (!Database.authors.containsKey(id)) {
            throw new AuthorNotFoundException("Author not found");
        }
        Database.authors.remove(id);
        return Response.status(Response.Status.OK).build();
    }
}