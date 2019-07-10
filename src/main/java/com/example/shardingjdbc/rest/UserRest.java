package com.example.shardingjdbc.rest;

import com.example.shardingjdbc.entity.User;
import com.example.shardingjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRest {
    @Autowired
    private UserService service;

    @GetMapping("/user")
    public List<User> listAll() {
        return service.queryAll();
    }
}
