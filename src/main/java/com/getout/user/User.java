package com.getout.user;

import com.getout.config.security.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static com.getout.config.security.role.Role.ZOMBIE;
import static java.time.LocalDateTime.now;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDateTime createdAt = now();

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

    @Past
    @NotNull
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "authority")
    private Role role;

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
        this.role = ZOMBIE;
    }

    /**
     * @param plainPassword will be encrypted inside on method
     * @see #encryptPassword(String plainPassword)
     */
    public void setPassword(String plainPassword) {
        this.password = encryptPassword(plainPassword);
    }

    private String encryptPassword(String plainPassword) {
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    public boolean emailsIsEquals(String otherEmail) {
        return email.equals(otherEmail);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(this.role);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
