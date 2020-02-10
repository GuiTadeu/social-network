package com.getout.validation;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(Errors errors) {
        this.errors = errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(toList());
    }
}
