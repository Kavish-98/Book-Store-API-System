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
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {
     @Override
    public Response toResponse(OutOfStockException exception) {
        return Response.status(Response.Status.BAD_REQUEST) // 400
                .entity("{\"error\": \"Out of stock\", \"message\": \"" + 
                exception.getMessage() + "\"}") 
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
