package com.example.springwebservice.service;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
        this.userList.add(new User(1, "Bill", 23));
        this.userList.add(new User(2, "Yen", 20));
        this.userList.add(new User(3, "Ka", 20));
    }

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() {
        List<User> response = this.userRepository.findAll();
        return response;
    }

    public User getUserById(int id) {
        User response = this.userRepository.findById(id);
        return response;
    }

    public String createUser(CreateUserRequest request) {
        User user = new User();

        user.setId(request.getId());
        user.setName(request.getName());
        user.setAge(request.getAge());

        this.userRepository.save(user);
        return "OK";
    }

    public String updateUserById(int id, UpdateUserRequest request) {
        User user = this.userRepository.findById(id);

        if (null == user) {
            return "FAIL";
        }
        user.setName(request.getName());
        user.setAge(request.getAge());

        this.userRepository.save(user);
        return "OK";
    }

    public String deleteUserById(int id) {
        User user = this.userRepository.findById(id);

        if (null == user) {
            return "FAIL";
        }
        Long count = this.userRepository.deleteById(id);
        //userRepository.delete(user);
        return "OK";
    }
/*
    public List<User> getAllUsers() {
        return this.userList;
    }

    public User getUser(int id) {
        for (User user : this.userList) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    public User createUser(User user) {
        this.userList.add(user);
        return user;
    }

    public User updateUser(int id,User user) {
        for (User updatedUser : this.userList) {
            if (id == updatedUser.getId()) {
                updatedUser.setAge(user.getAge());
                updatedUser.setName(user.getName());
                return updatedUser;
            }
        }
        return null;
    }

    public User deleteUser(int id) {

        for (int i=0; i<this.userList.size(); i++) {
            if (id == this.userList.get(i).getId()){
                User deletedUser = this.userList.get(i);
                this.userList.remove(i);
                return deletedUser;
            }
        }
        for (User user : this.userList) {
            if (id == user.getId()) {
                this.userList.remove(user);
                return user;
            }
        }
        return null;
    }
*/
} // Class end
