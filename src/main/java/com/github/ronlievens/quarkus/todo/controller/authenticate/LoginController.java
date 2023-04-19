package com.github.ronlievens.quarkus.todo.controller.authenticate;

import com.github.ronlievens.quarkus.todo.model.User;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Path(("/authenticate"))
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid final LoginRequestModel requestModel) {
        val user = User.findByEmail(requestModel.email());

        if (user==null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

//        if (!PasswordEncoder.matches(requestModel.password(), user.passHash)) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }

        return Response.ok(new LoginResponseModel("DUMMY_TOKEN")).status(200).build();
    }
}
