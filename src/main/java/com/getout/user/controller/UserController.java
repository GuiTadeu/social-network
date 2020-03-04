package com.getout.user.controller;

import com.getout.config.security.role.AllowZombie;
import com.getout.user.User;
import com.getout.user.UserRepository;
import com.getout.user.form.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @AllowZombie
    @GetMapping("/{id}/account")
    public ResponseEntity accountDetails(@PathVariable Long id) {
        return ResponseEntity.ok("Nothing Here...");
    }
}
