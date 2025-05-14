package com.nike.apinike.service.impl;

import com.nike.apinike.models.UserModels;
import com.nike.apinike.repository.UserRepository;
import com.nike.apinike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserModels save(UserModels user) {
        UserModels userExisist = userRepository.findByname(user.getName());
        if (Objects.nonNull(userExisist)) {
            throw new RuntimeException("Esse usuario já existe");

        } else {
            String passwordHash = passwordEncoder.encode(user.getPassword());
            UserModels usersave = new UserModels(user.getId(), user.getName(),
                    user.getEmail(), user.getPassword(), user.getRole());
            UserModels newUser = userRepository.save(usersave);
            return new UserModels(newUser.getId(), newUser.getName(),
                    newUser.getEmail(), newUser.getPassword(), newUser.getRole());

        }
    }

    @Override
    public ResponseEntity<List<UserModels>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
