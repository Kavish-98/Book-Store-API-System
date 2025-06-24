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
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    @Override
    public Response toResponse(InvalidInputException exception) {
        return Response.status(Response.Status.BAD_REQUEST) // 400
                .entity("{\"error\": \"Invalid input\", \"message\": \"" + 
                exception.getMessage() + "\"}") 
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
