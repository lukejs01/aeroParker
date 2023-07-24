package com.aeroparker.aeroParker.controllers;

import com.aeroparker.aeroParker.model.AeroParker;
import com.aeroparker.aeroParker.services.AeroParkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/api/v1/aeroparker")
public class AeroParkerController {

    @Autowired
    private AeroParkerService service;

    public AeroParkerController(AeroParkerService service) {
        this.service = service;
    }


    /**
     * @should save entity from request body
     * @should return 400 if email already exists
     * @should return 400 if name is incorrect format
     * @should return 400 if number is incorrect format
     */
    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody AeroParker user) {

        service.saveUser(user);
    }
}
