package xyz.juandiii.jdbc;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() throws SQLException {
        List<User> users = userService.getUsers();
        return Response.ok().entity(users).build();
    }
}
