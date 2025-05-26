package com.nike.apinike.controller;

import com.nike.apinike.dto.ProductDto;
import com.nike.apinike.models.ProductModels;
import com.nike.apinike.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/listar")
    public ResponseEntity<List<ProductModels>> listarProduct() {
        return ResponseEntity.ok(productService.listarProduct().getBody());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductModels>> listaProductName(@RequestParam String nome) {
        return ResponseEntity.ok(productService.listaProductName(nome).getBody());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> cadastrarProduto(@Valid @ModelAttribute ProductDto productDto) {
        return productService.cadastrarProduto(productDto);
    }

    @PutMapping(value = "/atualizar/{nome}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateProduct(@PathVariable String nome, @ModelAttribute ProductDto productDto) throws IOException {
        return productService.updateProduct(nome, productDto);


    }


    @DeleteMapping("/deletar")
    public ResponseEntity<String> deleteProduct(@RequestParam String nome) {
        return productService.deleteProduct(nome);
    }


}
