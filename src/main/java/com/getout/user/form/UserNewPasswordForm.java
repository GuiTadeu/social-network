package com.getout.user.form;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserNewPasswordForm {

    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "New Password is Required")
    private String newPlainPassword;
}
