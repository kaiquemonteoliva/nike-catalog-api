package com.nike.apinike.controller;

import com.nike.apinike.models.UserModels;
import com.nike.apinike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    private ResponseEntity<UserModels> save(@RequestBody UserModels user) {
        UserModels saveUser = userService.save(user);
        return ResponseEntity.ok(saveUser);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserModels>> getAll() {
        return userService.getAll();

    }
}
