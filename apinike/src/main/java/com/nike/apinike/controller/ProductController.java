package com.nike.apinike.controller;

import com.nike.apinike.Dto.ProductDto;
import com.nike.apinike.models.ProductModels;
import com.nike.apinike.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalogo")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<ProductModels>> listarProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductModels>> listaProductId(@RequestParam String nome){
        List<ProductModels> produto = productRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarProduto(@RequestBody @Valid ProductDto productDto){
        var produto = new ProductModels();
        BeanUtils.copyProperties(productDto, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(produto));

    }

    @PutMapping("/atualizar/{nome}")
    public ResponseEntity<String> updateProduct(@PathVariable String nome, @RequestBody @Valid ProductDto productDto){

        Optional<ProductModels> produtoOpicional = productRepository.findByNome(nome);

        if (produtoOpicional.isPresent()){
            ProductModels produtoExistente = produtoOpicional.get();

            produtoExistente.setNome(productDto.getNome());
            produtoExistente.setDescricao(productDto.getDescricao());
            produtoExistente.setFoto(productDto.getFoto());
            produtoExistente.setTamanho(productDto.getTamanho());

            productRepository.save(produtoExistente);

            return ResponseEntity.ok("Produto atualizado com sucesso");
        }else {

            return ResponseEntity.notFound().build();
        }

    }

    @Transactional
    @DeleteMapping("/deletar")
    public ResponseEntity<String> deleteProduct(@RequestParam String nome){
        productRepository.deleteByNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(nome +" foi deletado");
    }



}
