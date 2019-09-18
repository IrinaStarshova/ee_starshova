package com.accenture.flowershop.fe.ws.rest;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/verifyLogin")
public class LoginVerificationService {
    @Autowired
    private UserBusinessService userBusinessService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{login}")
    public boolean verify(@PathParam("login") String login){
        return userBusinessService.isUserExists(login);
    }
}
