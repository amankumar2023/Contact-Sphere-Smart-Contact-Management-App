package com.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact Save(Contact contact) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'Save'");
        String contactId=UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub

        var contactOld= contactRepo.findById(contact.getId()).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));
        contactOld.setEmail(contact.getEmail());
        contactOld.setAddress(contact.getAddress());
        contactOld.setDescription(contact.getDescription());
        contactOld.setFavourite(contact.isFavourite());
        contactOld.setLinkedinLink(contact.getLinkedinLink());
        contactOld.setName(contact.getName());
        contactOld.setPhoneNumber(contact.getPhoneNumber());
        contactOld.setWebsiteLink(contact.getWebsiteLink());

        return contactRepo.save(contactOld);

        
    }

    @Override
    public List<Contact> getAll() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAll'");
        return  contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getById'");
        return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Could not find contact with given ID"));
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'delete'");
        var contact=contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Could not find contact with given ID"));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getByUserId'");
        return contactRepo.findByUserId(userId);
    }

    @Override
    public List<Contact> getByUser(User user) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getByUser'");
        return contactRepo.findByUser(user);
    }

}
