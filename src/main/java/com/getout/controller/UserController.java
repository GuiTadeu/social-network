package com.getout.controller;

import com.getout.form.UserForm;
import com.getout.model.User;
import com.getout.repository.UserRepository;
import com.getout.validation.ApiErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody UserForm form, Errors errors, UriComponentsBuilder uriBuilder) {

        if(errors.hasErrors())
            return new ResponseEntity(new ApiErrors(errors), BAD_REQUEST);

        User user = form.converter();
        userRepository.save(user);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return created(uri).body(user);
    }
}
