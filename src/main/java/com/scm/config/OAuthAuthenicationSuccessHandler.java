package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.UUIDEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.User;
import com.scm.entities.providers;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger=LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

                DefaultOAuth2User user=(DefaultOAuth2User) authentication.getPrincipal();
            
                String email=user.getAttribute("email").toString();
                String name=user.getAttribute("name").toString();

                User user1=new User();
                user1.setEmail(email);
                user1.setName(name);
                user1.setPassword("password");
                user1.setUserId(UUID.randomUUID().toString());
                user1.setProvider(providers.GOOGLE);
                user1.setEnabled(true);

                user1.setEmailVerfied(true);
                user1.setProviderUserId(user.getName());
                user1.setRoleList(List.of(AppConstants.ROLE_USER));
                user1.setAbout("This Accoutn is created by Google ID");
                
                User user2=userRepo.findByEmail(email).orElse(null);
                
                if(user2==null)
                {
                    userRepo.save(user1);
                    logger.info("User Saved: "+ email);
                }

                logger.info("OAuthAuthenicationSuccessHnadler");
                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");

            
                // logger.info(user.getName());

                // user.getAttributes().forEach((key,value) -> {
                //     logger.info("{} => {}",key,value);
                // });

                // logger.info(user.getAuthorities().toString());

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
    }

}
