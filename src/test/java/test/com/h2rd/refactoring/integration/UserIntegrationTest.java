package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import com.h2rd.refactoring.usermanagement.UserDao;
import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest {

    UserDao userDao = new UserDao();
    User user = new User("Zelkrom", "zelkrom@email.com", Arrays.asList("member", "admin"));
	@Test
    public void addUserTest() {
		UserResource userResource = new UserResource();
        userDao.reset();
        Response response = userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
    public void updateUserTest() {
		UserResource userResource = new UserResource();
        userDao.reset();
        userDao.saveUser(user);
		user.setName("Poilo");
        Response response = userResource.updateUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
    public void deleteUserTest() {
	    UserResource userResource = new UserResource();
	    userDao.reset();
	    userDao.saveUser(user);
        Response response = userResource.deleteUser(user.getEmail());
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getUsersTest() {
        UserResource userResource = new UserResource();
        userDao.saveUser(user);
        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(userDao.getUsers().size(), 1);
    }
}
