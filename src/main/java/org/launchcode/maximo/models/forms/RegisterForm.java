package org.launchcode.maximo.models.forms;

import javax.validation.constraints.NotNull;

public class RegisterForm extends LoginForm {

    @NotNull(message="Passwords do not match.")
    private String verifyPassword;

    //Password and verify password must match before setting password
    @Override
    public void setPassword(String password){
        super.setPassword(password);
        checkPasswordForRegistration();
    }

    public String getVerifyPassword(){
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword){
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    private void checkPasswordForRegistration(){
        if (!getPassword().equals(verifyPassword)){
            verifyPassword = null;
        }
    }





}
