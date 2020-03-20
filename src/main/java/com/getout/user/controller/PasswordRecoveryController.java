package com.getout.user.controller;

import com.getout.config.exception.NotFoundException;
import com.getout.user.UserRepository;
import com.getout.user.form.UserNewPasswordForm;
import com.getout.user.form.UserRecoveryPasswordForm;
import com.getout.user.passwordRecovery.PasswordRecoveryCodeRepository;
import com.getout.user.passwordRecovery.PasswordRecoveryCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Log4j2
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PasswordRecoveryController {

    final UserRepository userRepository;
    final PasswordRecoveryCodeRepository passwordRecoveryCodeRepository;
    final PasswordRecoveryCodeService passwordRecoveryCodeService;

    @PostMapping("/passwordRecovery")
    public ResponseEntity passwordRecovery(@Valid @RequestBody UserRecoveryPasswordForm form, UriComponentsBuilder uriBuilder) {
        var user = userRepository.findByEmail(form.getEmail()).orElseThrow(NotFoundException::new);

        var passwordRecovery = passwordRecoveryCodeService.generateAndSaveTemporaryCodeByUser(user);

        URI uri = uriBuilder.path("/passwordRecovery/{code}")
                .buildAndExpand(passwordRecovery.getCode()).toUri();

        log.info("Email successfully sent!");
        log.info("URL for recovery your password: " + uri);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/passwordRecovery/{code}")
    public ResponseEntity passwordRecovery(@Valid @RequestBody UserNewPasswordForm form, @PathVariable Integer code) {

        var user = userRepository.findByEmail(form.getEmail()).orElseThrow(NotFoundException::new);

        if (passwordRecoveryCodeService.isValidCodeForUser(code, user)) {
            user.setPassword(form.getNewPlainPassword());
            return ResponseEntity.ok("Password changed successfully!");
        }

        return ResponseEntity.ok("Invalid Email or Code!");
    }
}
