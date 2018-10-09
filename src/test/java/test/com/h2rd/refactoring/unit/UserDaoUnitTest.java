package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class UserDaoUnitTest {

    private UserDao userDao;

    @Test
    public void saveUserTest() {
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        userDao.saveUser(user);
    }

    @Test
    public void getUsersTest(){
        saveUserTest();
        HashMap<String, User> users = userDao.getUsers();
        Assert.assertEquals(users.size(), 1);
    }

    @Test
    public void deleteUserTest() {
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        try {
            userDao.deleteUser(user.getName());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    /*
     * Hashmap put function returns the old value when it is overriding an existing one.
     * We verify here if we are updating the correct user by giving it's old value.
     */
    public void updateUserTest(){
        saveUserTest();

        User user = userDao.updateUser(new User("Weird person", "fake@email.com", Collections.singletonList("")));
        Assert.assertSame(user.getName(), "Fake Name");
    }

    @Test
    public void findUserTest(){
        saveUserTest();
        Assert.assertEquals(userDao.findUser("Weird person").get(0).getEmail(), "fake@email.com");
    }



}