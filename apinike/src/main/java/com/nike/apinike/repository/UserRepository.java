package com.nike.apinike.repository;

import com.nike.apinike.models.UserModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModels, UUID> {

    UserModels findByname(String nome);
}