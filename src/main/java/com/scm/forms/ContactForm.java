package com.scm.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm {

    private String name;
    private String id;
    private String email;
    private String address;
    private String phoneNumber;
    private String description;
    private String linkedin;
    private String website;
    private boolean favourite;

    
}
