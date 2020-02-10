package com.getout.model;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @Size
    @NotBlank
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
