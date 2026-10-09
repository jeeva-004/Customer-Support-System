package com.customersupport.controllers;

import com.customersupport.entities.User;
import com.customersupport.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping
    public String createUser(@RequestBody User user){
        userService.createUser(user);
        return "User Created";
    }

    @PutMapping("/{id}")
    public String updateUser(@RequestBody User newUser, @PathVariable Long id){
        userService.updateUser(newUser, id);

        return "User Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return "User Deleted";
    }
}
