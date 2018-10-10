package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;
import org.junit.Test;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserResourceUnitTest {

    UserResource userResource = new UserResource();
    UserDao userDao = new UserDao();
    User user = new User("Pikachu", "pika@mon.com", Arrays.asList("member", "admin"));

    @Test
    public void addUser(){
        Response response = userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(200, response.getStatus());

        response = userResource.findUser(user.getName());
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(response.getEntity().toString(), Collections.singletonList(user).toString());

        response = userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(304, response.getStatus());
    }

    @Test
    public void getUsersTest() {
        userDao.saveUser(user);
        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }

}
