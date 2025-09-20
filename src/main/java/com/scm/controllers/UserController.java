package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.config.OAuthAuthenicationSuccessHandler;
import com.scm.helpers.Helper;



@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger=LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }

    @RequestMapping("/profile")
    public String profile(Authentication authentication) {

        String username=Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User logged in: {}", username);
        return "user/profile";
    }
    
   
}
