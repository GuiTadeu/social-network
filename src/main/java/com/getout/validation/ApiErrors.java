package com.getout.validation;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FieldErrors {

    public static List<String> convertErrorsToJson(Errors errors) {
        return errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(toList());
    }
}
