package com.getout.po;

import com.getout.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserPO {

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is Required")
    private String password;

    @NotBlank(message = "Name is Required")
    private String name;

    public User converter() {
        return new User(email, new BCryptPasswordEncoder().encode(password), name);
    }
}
