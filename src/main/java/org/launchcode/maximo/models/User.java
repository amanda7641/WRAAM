package org.launchcode.maximo.models;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{3,14}", message = "Invalid username")
    private String username;


    @NotNull
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private Role role;

    public User() {

    }

    public User(String username, String password, Role role){
        this.username = username;
        this.pwHash = hashPassword(password);
        this.role = role;
    }

    public String getUsername(){
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPwHash(String password) {
        this.pwHash = hashPassword(password);
    }


    public static String hashPassword(String password){
        return encoder.encode(password);
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

}
