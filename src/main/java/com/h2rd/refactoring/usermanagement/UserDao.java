package com.h2rd.refactoring.usermanagement;

import com.sun.jersey.spi.resource.Singleton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
@Singleton
public class UserDao {

    private static HashMap<String, User> users = new HashMap<String, User>();

    private static UserDao userDao = new UserDao();

    public static UserDao getUserDao() {
        return userDao;
    }

    public void reset(){ users = new HashMap<String, User>();}

    public User saveUser(User user) {
        if (!users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
            return user;
        } else {
            return null;
        }
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public Boolean deleteUser(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
            return true;
        } else {
            return false;
        }
    }

    public User updateUser(User userToUpdate) {
        if (users.containsKey(userToUpdate.getEmail())) {
            users.put(userToUpdate.getEmail(), userToUpdate);
            return userToUpdate;
        } else {
            return null;
        }
    }

    /**
     *
     * @param name Not a unique identifier.
     * @return All users that share the name we want to find.
     */
    public ArrayList<User> findUser(String name) {
        try {
            ArrayList<User> sharedNameUsers = new ArrayList<User>();
            for (User user: users.values())
                if (user.getName().equals(name))
                    sharedNameUsers.add(user);
            return sharedNameUsers;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
