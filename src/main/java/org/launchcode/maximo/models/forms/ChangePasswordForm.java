package org.launchcode.maximo.models.forms;

import org.launchcode.maximo.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ChangePasswordForm {

    private User user;

    @NotNull
    @Pattern(regexp = "(\\S){4,25}", message = "Password must have between 4-20 non-whitespace characters.")
    private String currentPassword;

    @NotNull(message="Passwords do not match.")
    private String verifyPassword;

    @NotNull
    @Pattern(regexp = "(\\S){4,25}", message = "Password must have between 4-20 non-whitespace characters.")
    private String password;

    public ChangePasswordForm(){

    }

    public ChangePasswordForm(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
