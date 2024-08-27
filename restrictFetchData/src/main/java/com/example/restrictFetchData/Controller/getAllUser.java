package com.example.restrictFetchData.Controller;

import com.example.restrictFetchData.Entity.User;
import com.example.restrictFetchData.Repository.UserRepository;
import com.example.restrictFetchData.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class getAllUser {

    @Autowired
    private userService userservice;
    @GetMapping("/getAllUsers")
    public Object getAllUsers(){
        return userservice.getAllUsers();
    }
}
