package com.h2rd.refactoring.usermanagement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@XmlRootElement
public class User {

    private String name;
    private String email;
    private List<String> roles;

    public User(){}

    public User(String name, String email, List<String> roles){
        this.setName(name);
        this.setEmail(email);
        this.setRoles(roles);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getRoles() {
        return roles;
    }
    /**
     * Instead of forcing the front-end or api users to set a minimum of 1 role for a new user,
     * I set a default role if none is provided, I find this more beautiful than returning
     * "At least one role should be provided" in the response,
     * I just give the lowest role they can get and if they have restriction issues, they can update their user.
     * */
    public void setRoles(List<String> roles) {
        if (roles.isEmpty() || roles.get(0).equals("")){ this.roles = Collections.singletonList("member"); }
        else{ this.roles = roles; }
    }

    public String toString() {
        return this.getEmail();
    }
}
