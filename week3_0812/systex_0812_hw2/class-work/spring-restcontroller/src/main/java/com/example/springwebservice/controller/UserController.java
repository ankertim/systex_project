package com.example.springwebservice.controller;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.controller.dto.response.StatusResponse;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
// solved cors error
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getUserList() {
        List<User> response = this.userService.getUserList();
        return response;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        User response = this.userService.getUserById(id);
        return response;
    }

    @PostMapping()
    public StatusResponse createUser(@RequestBody CreateUserRequest request) {
        String response = this.userService.createUser(request);
        return new StatusResponse(response);
    }

    @PutMapping("/{id}")
    public StatusResponse updateUserById(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        String response = this.userService.updateUserById(id, request);
        return new StatusResponse(response);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteUserById(@PathVariable int id) {
        String response = this.userService.deleteUserById(id);
        return new StatusResponse(response);
    }
/*
    @GetMapping()
    public List<User> getAllUsers() {
        List<User> userList = this.userService.getAllUsers();
        return userList;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        User user = this.userService.getUser(id);
        return user;
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        User createdUser = this.userService.createUser(user);
        return createdUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User user) {
        User updatedUser = this.userService.updateUser(id, user);
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id) {
        User deletedUser = this.userService.deleteUser(id);
        return deletedUser;
    }
    */
} // Class end
