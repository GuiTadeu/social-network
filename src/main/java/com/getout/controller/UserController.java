package com.getout.controller;

import com.getout.form.UserForm;
import com.getout.model.User;
import com.getout.repository.UserRepository;
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
    public ResponseEntity create(@Valid @RequestBody UserForm form) {
        User user = form.converter();
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
