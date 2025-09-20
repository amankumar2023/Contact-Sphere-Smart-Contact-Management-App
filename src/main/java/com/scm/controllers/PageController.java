package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    

    @RequestMapping("/home")    
    public String home()
    {
        System.out.println("This is HomePage");
        return "home";
    }

    @RequestMapping("/about")    
    public String base()
    {
        System.out.println("Hello World");
        return "about";
    }

    @RequestMapping("/services")    
    public String services()
    {
        System.out.println("Hello Services");
        return "services";
    }

    @RequestMapping("/contact")    
    public String contact()
    {
        System.out.println("Hello Contact");
        return "contact";
    }

    @RequestMapping("/login")    
    public String login()
    {
        System.out.println("Hello login");
        return "login";
    }

    @RequestMapping("/signup")    
    public String register(Model model,HttpSession session)
    {
        System.out.println("Hello SignUp");
        UserForm userForm= new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult , HttpSession session)
    {

        if(rBindingResult.hasErrors())
        {
            return "register"; 
        }

        // System.out.println("Hello Everyone");
        // System.out.println(userForm.getName());
        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(user.getAbout());
        user.setPhoneNumber(user.getPhoneNumber());


        User savedUser=userService.saveUser(user);
        System.out.println(savedUser);

        session.setAttribute("message","Registration Successfully");
        // session.removeAttribute("message");

        return "redirect:/signup";
    }

}
