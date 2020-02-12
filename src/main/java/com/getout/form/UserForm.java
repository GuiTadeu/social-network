package com.getout.form;

import com.getout.model.User;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
public class UserForm {

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Password is Required")
    @Size(min = 10, message = "The minimum password length is 10 characters")
    private String plainPassword;

    public User converter() {
        return new User(email, name, plainPassword);
    }
}