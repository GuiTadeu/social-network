package com.getout.controller;

import com.getout.form.user.UserCreateForm;
import com.getout.form.user.UserLoginForm;
import com.getout.model.user.User;
import com.getout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody UserCreateForm form) {
        User user = form.converter();
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLoginForm form) {
        User user = userRepository.findByEmail(form.getEmail());
        var bcrypt = new BCryptPasswordEncoder();

        if(bcrypt.matches(form.getPlainPassword(), user.getPassword()))
            return ResponseEntity.accepted().build();

        return ResponseEntity.badRequest().build();
    }
}
