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

    private UserDao userDao = new UserDao();

    User user1 = new User("Fake Name1", "fake@email.com", Arrays.asList("admin", "master"));

    @Test
    public void saveUserTest() {
        userDao.saveUser(user1);
    }

    @Test
    public void getUsersTest(){
        userDao.reset();
        userDao.saveUser(user1);
        HashMap<String, User> users = userDao.getUsers();
        Assert.assertEquals(users.size(), 1);
    }

    @Test
    public void deleteUserTest() {
        userDao.reset();
        userDao.saveUser(user1);
        userDao.deleteUser("Fake Name1");
    }

    @Test
    /*
     * Hashmap put function returns the old value when it is overriding an existing one.
     * We verify here if we are updating the correct user by giving it's old value.
     */
    public void updateUserTest(){
        userDao.reset();
        userDao.saveUser(user1);
        user1.setName("Another Fake Name");
        User user = userDao.updateUser(user1);
        Assert.assertSame(user.getName(), "Another Fake Name");
    }

    @Test
    public void findUserTest(){
        userDao.reset();
        User user = userDao.saveUser(user1);
        Assert.assertEquals(user, user1);
    }



}