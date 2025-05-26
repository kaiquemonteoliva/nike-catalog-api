package com.nike.apinike.service;

import com.nike.apinike.dto.ProductDto;
import com.nike.apinike.models.ProductModels;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ResponseEntity<List<ProductModels>> listarProduct();

    ResponseEntity<List<ProductModels>> listaProductName(@RequestParam String nome);

    ResponseEntity<Object> cadastrarProduto(@Valid @ModelAttribute ProductDto productDto);

    ResponseEntity<String> updateProduct(@PathVariable String nome, @ModelAttribute ProductDto productDto) throws IOException;

    ResponseEntity<String> deleteProduct(@RequestParam String nome);
}
