package com.getout.form.user;

import com.getout.model.user.Gender;
import com.getout.model.user.User;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter
public class UserCreateForm {

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Password is Required")
    @Size(min = 10, message = "The minimum password length is 10 characters")
    private String plainPassword;

    @NotNull(message = "Gender is Required")
    private Gender gender;

    @Past
    @NotNull(message = "Birthday is Required")
    private LocalDate birthday;

    public User converter() {
        return new User(email, name, plainPassword, gender, birthday);
    }
}