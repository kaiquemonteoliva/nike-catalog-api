package com.nike.apinike.service;

import com.nike.apinike.models.UserModels;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    UserModels save(UserModels user);

    ResponseEntity<List<UserModels>> getAll();
}
