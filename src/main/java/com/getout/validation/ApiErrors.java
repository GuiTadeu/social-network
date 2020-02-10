package com.getout.validation;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(BindingResult result) {
        this.errors = result.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(toList());
    }
}
