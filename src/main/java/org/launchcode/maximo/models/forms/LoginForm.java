package org.launchcode.maximo.models.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginForm {
    @NotNull
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{3,14}", message = "Usernames must be between 4 and 15 characters, start with a letter, and contain only letters and numbers.")
    private String username;

    @NotNull
    @Pattern(regexp = "(\\S){4,25}", message = "Password must have between 4-20 non-whitespace characters.")
    private String password;

    public LoginForm(){

    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
