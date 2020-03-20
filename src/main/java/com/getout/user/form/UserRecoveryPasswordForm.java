package com.getout.user.form;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class UserRecoveryPasswordForm {

    @Email(message = "Invalid Email")
    private String email;
}
