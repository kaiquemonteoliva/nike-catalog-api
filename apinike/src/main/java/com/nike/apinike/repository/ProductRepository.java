package com.nike.apinike.repository;

import com.nike.apinike.models.ProductModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<ProductModels, UUID> {
    List<ProductModels> findByNomeContainingIgnoreCase(String nome);
    void deleteByNome(String nome);

    Optional<ProductModels> findByNome(String nome);
}