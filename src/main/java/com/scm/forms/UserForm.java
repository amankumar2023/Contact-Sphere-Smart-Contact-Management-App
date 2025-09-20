package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    // @NotBlank(message = "UserName is required")
    @Size(min = 3,message = "UserName should be of minimum 3 character")
    private String name;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    // @NotBlank(message = "Phone Number is required")
    @Size(min = 10,max = 10,message = "Numbr should be of 10 digit")
    private String phoneNumber;

    // @NotBlank(message = "Password is required")
    @Size(min = 6,message = "Password should be of minimum 6 character")
    private String password;

    @Size(min = 10,message = "Content should be of minimum 10 character")
    private String about;
}
