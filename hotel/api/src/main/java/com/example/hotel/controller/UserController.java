package com.example.hotel.controller;

import com.example.hotel.entity.Status;
import com.example.hotel.entity.User;
import com.example.hotel.model.UserMo;
import com.example.hotel.repository.UserRepository;
import com.example.hotel.repositoryMo.UserMoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMoRepository userMoRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();
        System.out.println("New user: " + newUser.toString());
        for (User user : users) {
            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody UserMo user) {
        List<UserMo> users = userMoRepository.findAll();
        for (UserMo other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userMoRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    /*public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }*/

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    /**
     * Get all users from Mongo
      * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getUsers")
    public List<UserMo> getUsers(){
        return userMoRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }


}
