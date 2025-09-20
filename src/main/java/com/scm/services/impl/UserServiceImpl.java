package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

import jakarta.validation.constraints.Null;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoleList(List.of(AppConstants.ROLE_USER));

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
    
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
    
        User user2=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not  found"));
        
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        // user2.setProfilePic(user2.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerfied(user.isEmailVerfied());
        user2.setPhoneVerfied(user.isPhoneVerfied());
        user2.setProvider(user.getProvider());
        user.setProviderUserId(user.getProviderUserId());
    
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String id) {

        User user2=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not  found"));
        userRepo.delete(user2);

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public boolean isUserExist(String userId) {

        User user2=userRepo.findById(userId).orElse(null);
        return user2!=null ? true:false;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isUserExist'");
    }

    @Override
    public boolean isUserExistByEmail(String email) {

        User user=userRepo.findByEmail(email).orElse(null);
        return user!=null ? true:false;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isUserExistByEmail'");
    }

    @Override
    public List<User> getAllUsers() {

        return userRepo.findAll();

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public User getUserByEmail(String email) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
        return userRepo.findByEmail(email).orElse(null);
    }

}
