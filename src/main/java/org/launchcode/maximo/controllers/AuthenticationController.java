package org.launchcode.maximo.controllers;

import org.launchcode.maximo.models.User;
import org.launchcode.maximo.models.forms.ChangePasswordForm;
import org.launchcode.maximo.models.forms.LoginForm;
import org.launchcode.maximo.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthenticationController extends AbstractController {

    //allows admins to view register new user form
    @RequestMapping(value = "/register")
    public String registerForm(Model model){
        model.addAttribute(new RegisterForm());
        model.addAttribute("title", "Register a New User");
        return "user/register";
    }

    //processes register new user form then send admin to work request index
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm registerForm, Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Register a New User");
            return "user/register";
        }

        User existingUser = userDao.findByUsername(registerForm.getUsername());

        if(existingUser != null){
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register a New User");
            return "user/register";
        }

        User newUser = new User(registerForm.getUsername(), registerForm.getPassword(), registerForm.getRole());
        userDao.save(newUser);

        return "redirect:workrequest";
    }

    //user can view change password form
    @RequestMapping(value="/updateaccount")
    public String changepassword(Model model, HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute(AbstractController.userSessionKey);
        model.addAttribute("title", "Change Password");
        model.addAttribute(new ChangePasswordForm(userDao.findOne(userId)));
        return "user/update";
    }

    //processes change password form, stores pwhash and updates user information
    @RequestMapping(value="/updateaccount", method = RequestMethod.POST)
    public String processChangePassword(@ModelAttribute @Valid ChangePasswordForm changePasswordForm, Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Change Password");
            return "user/update";
        }

        int currentUserId = changePasswordForm.getUser().getUid();
        User currentUser = userDao.findOne(currentUserId);
        String password = changePasswordForm.getCurrentPassword();

        if (!currentUser.isMatchingPassword(password)){
            errors.rejectValue("currentPassword", "password.invalid", "Invalid Current Password.");
            model.addAttribute("title", "Change Password");
            return "user/update";
        }

        if(!changePasswordForm.getPassword().equals(changePasswordForm.getVerifyPassword())){
            errors.rejectValue("verifyPassword", "verifyPassword.invalid", "New Passwords do not match.");
            model.addAttribute("title", "Change Password");
            return "user/update";
        }

        currentUser.setPwHash(changePasswordForm.getPassword());
        userDao.save(currentUser);

        return "redirect:workrequest";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute(new LoginForm());
        model.addAttribute("title", "Log In");
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid LoginForm loginForm, Errors errors, HttpServletRequest request, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Log In");
            return "user/login";
        }

        User theUser = userDao.findByUsername(loginForm.getUsername());
        String password = loginForm.getPassword();

        if (theUser == null){
            errors.rejectValue("username", "user.invalid", "The given username does not exist.");
            model.addAttribute("title", "Log In");
            return "user/login";
        }

        if (!theUser.isMatchingPassword(password)){
            errors.rejectValue("password", "password.invalid", "Invalid Password.");
            model.addAttribute("title", "Log In");
            return "user/login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:workrequest";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:login";
    }

}
