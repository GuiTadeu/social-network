package com.getout.controller;

import com.getout.model.User;
import com.getout.po.UserPO;
import com.getout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.getout.validation.FieldErrors.convertErrorsToJson;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody UserPO userPO, BindingResult result, UriComponentsBuilder uriBuilder) {

        if (result.hasErrors())
            return badRequest().body(convertErrorsToJson(result));

        User user = userPO.converter();
        userRepository.save(user);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return created(uri).body(user);
    }
}
