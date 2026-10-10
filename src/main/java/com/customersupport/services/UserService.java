package com.customersupport.services;

import com.customersupport.dto.UserRequest;
import com.customersupport.dto.UserResponse;
import com.customersupport.entities.User;
import com.customersupport.enums.Role;
import com.customersupport.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private UserResponse toResponse(User user){
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

    private User toUser(UserRequest user){
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getPassword());
        userEntity.setRole(Role.valueOf(user.getRole().toUpperCase()));
        return userEntity;
    }

    public List<UserResponse> getAll(){
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse getById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));

        return toResponse(user);
    }

    public UserResponse createUser(UserRequest user){
        User userEntity = toUser(user);
        userRepository.save(userEntity);
        return  toResponse(userEntity);
    }

    public UserResponse updateUser(UserRequest newUser, Long id){

        User user = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));

        user.setName(newUser.getName());
        user.setRole(Role.valueOf(newUser.getRole().toUpperCase()));
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        User updatedUser = userRepository.save(user);

        return toResponse(updatedUser);
    }

    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User Deleted";
    }

}
