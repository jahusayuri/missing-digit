package com.personal.missingdigit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MissingDigitController {

    @GetMapping(value="/{equation}")
    public String findMissingDigit(@PathVariable String equation) {
        // Todo add service that separates equation and returns missing digit.
        return equation;
    }
}
