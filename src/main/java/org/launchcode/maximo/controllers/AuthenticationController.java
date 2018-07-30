package org.launchcode.maximo.controllers;

import org.launchcode.maximo.models.User;
import org.launchcode.maximo.models.forms.LoginForm;
import org.launchcode.maximo.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthenticationController extends AbstractController {

    @RequestMapping(value = "/register")
    public String registerForm(Model model){
        model.addAttribute(new RegisterForm());
        model.addAttribute("title", "Register");
        return "user/register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm registerForm, Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Register");
            return "user/register";
        }

        User existingUser = userDao.findByUsername(registerForm.getUsername());

        if(existingUser != null){
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "user/register";
        }

        User newUser = new User(registerForm.getUsername(), registerForm.getPassword());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

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
