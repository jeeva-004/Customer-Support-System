package com.customersupport.services;

import com.customersupport.entities.User;
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

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));
    }

    public String createUser(User user){
        userRepository.save(user);

        return "User Added";
    }

    public String updateUser(User newUser, Long id){

        User oldUser = userRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Record does't exist ID "+id));

        oldUser.setName(newUser.getName());
        oldUser.setRole(newUser.getRole());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());

        return "User Updated";
    }

    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User Deleted";
    }

}
