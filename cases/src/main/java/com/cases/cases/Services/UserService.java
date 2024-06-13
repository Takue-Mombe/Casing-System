package com.cases.cases.Services;

import com.cases.cases.Models.Users;
import com.cases.cases.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Users save(Users user) {
        return userRepository.save(user);
    }
}
