package com.example.restrictFetchData.Service;

import com.example.restrictFetchData.Entity.User;
import com.example.restrictFetchData.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userService {

    @Autowired
    private UserRepository userRepository;

    int fetchCount = 0;

    public List<User> getAllUsers() {
        fetchCount++;
        if (fetchCount <= 5) {
            return userRepository.findAll();
        } else {

        }
        return null;
    }

}
