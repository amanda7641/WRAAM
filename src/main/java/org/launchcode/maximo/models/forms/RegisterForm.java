package org.launchcode.maximo.models.forms;

import org.launchcode.maximo.models.Role;

import javax.validation.constraints.NotNull;

public class RegisterForm extends LoginForm {

    @NotNull(message="Passwords do not match.")
    private String verifyPassword;

    private Role[] fields = Role.values();

    private Role role;



    //Password and verify password must match before setting password
    @Override
    public void setPassword(String password){
        super.setPassword(password);
        checkPasswordForRegistration();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getVerifyPassword(){
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword){
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    public Role[] getFields() {
        return fields;
    }

    public void setFields(Role[] fields) {
        this.fields = fields;
    }

    private void checkPasswordForRegistration(){
        if (!getPassword().equals(verifyPassword)){
            verifyPassword = null;
        }
    }





}
