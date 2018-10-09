package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest {
	
	@Test
    public void addUserTest() {
		UserResource userResource = new UserResource();
		
		User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(new ArrayList<String>());
        
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
    public void updateUserTest() {
		UserResource userResource = new UserResource();
		
		addUserTest();
        
        User updated = new User();
        updated.setName("integration");
        updated.setEmail("updated@integration.com");
        updated.setRoles(new ArrayList<String>());
        
        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
    public void deleteUserTest() {
	    UserResource userResource = new UserResource();

	    addUserTest();

        Response response = userResource.deleteUser("initial@integration.com");
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getUsersTest() {
        UserResource userResource = new UserResource();
        Response response = userResource.getUsers();

        Assert.assertEquals(200, response.getStatus());
    }
}
