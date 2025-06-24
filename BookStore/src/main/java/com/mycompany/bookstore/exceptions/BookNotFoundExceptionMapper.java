/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Kavish Weerasingha
 */
@Provider
public class BookNotFoundExceptionMapper implements ExceptionMapper<BookNotFoundException> {
    @Override
    public Response toResponse(BookNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND) // 404
                .entity("{\"error\": \"Book not found\", \"message\": \"" + 
                exception.getMessage() + "\"}") 
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
