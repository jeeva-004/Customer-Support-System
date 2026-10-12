package com.customersupport.controllers;

import com.customersupport.dto.UserRequest;
import com.customersupport.dto.UserResponse;
import com.customersupport.entities.User;
import com.customersupport.services.UserService;
import jakarta.validation.Valid;
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
    public List<UserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@Valid @RequestBody UserRequest newUser, @PathVariable Long id){
        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return "User Deleted";
    }

    @GetMapping("/email/{email}")
    public UserResponse getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/exists")
    public boolean emailExists(@RequestParam String email) {
        return userService.emailExists(email);
    }
}
