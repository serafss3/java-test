package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Repository
public class UserResource{

    private static UserDao userDao = new UserDao();

    @POST
    @Path("add/")
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {
        User user = new User(name, email, roles);
        if (userDao.saveUser(user) == user) {
            return Response.ok().entity(user).build();
        } else {
            return Response.notModified().build();
        }
    }

    @PUT
    @Path("update/")
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {
        User user = new User(name, email, roles);
        if(userDao.updateUser(user) == user) {
            return Response.ok().entity(user).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("delete/")
    public Response deleteUser(@QueryParam("email") String email) {
        if (userDao.deleteUser(email)) {
            return Response.ok().build();
        } else {
            return Response.notModified().build();
        }
    }

    @GET
    @Path("find/")
    public Response getUsers() {
    	
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
    		"classpath:/application-config.xml"	
    	});
    	userDao = context.getBean(UserDao.class);
    	ArrayList<User> users = new ArrayList<User>(userDao.getUsers().values());

        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(usersEntity).build();
    }

    @GET
    @Path("search/")
    public Response findUser(@QueryParam("name") String name) {
        ArrayList<User> users = userDao.findUser(name);
        return Response.ok().entity(users).build();
    }
}
