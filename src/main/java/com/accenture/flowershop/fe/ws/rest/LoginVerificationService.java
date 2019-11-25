package com.accenture.flowershop.fe.ws.rest;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Component
@Path("/verifyLogin")
public class LoginVerificationService {
    @Autowired
    private UserBusinessService userBusinessService;

    @GET
    @Path("/{login}")
    public Response verify(@PathParam("login") String login) {
        return Response.ok(userBusinessService.isUserExists(login)).build();
    }
}
