package com.scm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.services.ContactService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService; 

    @Autowired
    private UserService userService;

    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String addContactView(Model model) {

        ContactForm contactForm=new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "user/add_contacts";
    }

    @RequestMapping(value= "/add", method=RequestMethod.POST)
    public String saveContact(@ModelAttribute ContactForm contactForm, Authentication authentication, HttpSession session) {

        String username=Helper.getEmailOfLoggedInUser(authentication);

        User user=userService.getUserByEmail(username);

        Contact contact=new Contact();
        contact.setName(contactForm.getName());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setEmail(contactForm.getEmail());
        contact.setFavourite(contactForm.isFavourite());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setLinkedinLink(contactForm.getLinkedin());
        contact.setWebsiteLink(contactForm.getWebsite());
        contact.setUser(user);
        
        contactService.Save(contact);

        session.setAttribute("message","Add Contact Successfully");

        return "redirect:/user/contacts/add";
    }
    
    @RequestMapping
    public String viewContacts(Model model,Authentication authentication)
    {
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user=userService.getUserByEmail(username);

        List<Contact> contacts= contactService.getByUser(user);

        model.addAttribute("contacts", contacts);

        System.out.println("checking query workinf or not");
        for (Contact contact : contacts) {
    // access individual contact object
    System.out.println(contact.getName());
}
        return "user/contacts";
    }


    @RequestMapping(value="/deleteContact/{contactId}", method=RequestMethod.GET)
    public String deleteContact(@PathVariable String contactId) {
    System.out.println(contactId);
    contactService.delete(contactId);    // Use your repository/service here
    return "redirect:/user/contacts"; 
    }

    @RequestMapping(value="/updateView/{contactId}", method=RequestMethod.GET)
    public String updateViewContact(@PathVariable String contactId, Model model) {
    System.out.println(contactId);
    
    var contact=contactService.getById(contactId);

    ContactForm contactForm=new ContactForm();
    contactForm.setAddress(contact.getAddress());
    contactForm.setDescription(contact.getDescription());
    contactForm.setEmail(contact.getEmail());
    contactForm.setLinkedin(contact.getLinkedinLink());
    contactForm.setName(contact.getName());
    contactForm.setPhoneNumber(contact.getPhoneNumber());
    contactForm.setWebsite(contact.getWebsiteLink());
    model.addAttribute("contactForm", contactForm);
    model.addAttribute("contactId", contactId);

    return "/user/updateView_contacts"; 
    }



    @RequestMapping(value="/updateContact/{contactId}", method=RequestMethod.POST)
    public String updateContact(@PathVariable String contactId,@ModelAttribute ContactForm contactForm,Model model) {
    System.out.println(contactId);
    
    var contact=new Contact();
    contact.setId(contactId);
    contact.setName(contactForm.getName());
    contact.setAddress(contactForm.getAddress());
    contact.setDescription(contactForm.getDescription());
    contact.setEmail(contactForm.getEmail());
    contact.setFavourite(contactForm.isFavourite());
    contact.setPhoneNumber(contactForm.getPhoneNumber());
    contact.setLinkedinLink(contactForm.getLinkedin());
    contact.setWebsiteLink(contactForm.getWebsite());
        
    var update=contactService.update(contact);

    return "redirect:/user/contacts"; 
    }

}
