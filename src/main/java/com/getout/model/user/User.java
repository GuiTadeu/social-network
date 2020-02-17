package com.getout.model.user;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull
    @Enumerated(STRING)
    private Gender gender;

    @NotNull
    private LocalDate birthday;

    /**
     * @param plainPassword will be encrypted inside on constructor
     * @see #encryptPassword(String plainPassword)
     */
    public User(String email, String name, String plainPassword, Gender gender, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.password = encryptPassword(plainPassword);
        this.gender = gender;
        this.birthday = birthday;
    }

    private String encryptPassword(String plainPassword) {
        return new BCryptPasswordEncoder().encode(plainPassword);
    }
}
