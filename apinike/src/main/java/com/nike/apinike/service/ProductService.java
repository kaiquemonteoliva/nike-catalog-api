package com.nike.apinike.service;

import com.nike.apinike.dto.ProductDto;
import com.nike.apinike.infrastructure.file.FileStorageService;
import com.nike.apinike.models.ProductModels;
import com.nike.apinike.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FileStorageService fileStorageService;


    public ResponseEntity<List<ProductModels>> listarProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    public ResponseEntity<List<ProductModels>> listaProductName(@RequestParam String nome) {
        List<ProductModels> produto = productRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(produto);
    }


    public ResponseEntity<Object> cadastrarProduto(@Valid @ModelAttribute ProductDto productDto) {
        var produto = new ProductModels();
        BeanUtils.copyProperties(productDto, produto);

        try {
            String caminhodafoto = fileStorageService.processarFoto(productDto.getFoto());
            if (caminhodafoto != null) {
                produto.setFoto(caminhodafoto.getBytes());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a imagem.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(produto));
    }


    public ResponseEntity<String> updateProduct(@PathVariable String nome, @ModelAttribute ProductDto productDto) throws IOException {

        List<ProductModels> produtoOpicional = productRepository.findByNome(nome);


        if (!produtoOpicional.isEmpty()) {
            ProductModels produtoExistente = produtoOpicional.get(0);


            produtoExistente.setNome(productDto.getNome());
            produtoExistente.setDescricao(productDto.getDescricao());
            produtoExistente.setTamanho(productDto.getTamanho());
            String caminhodafoto = fileStorageService.processarFoto(productDto.getFoto());
            if (caminhodafoto != null) {
                produtoExistente.setFoto(caminhodafoto.getBytes());
            }

            productRepository.save(produtoExistente);

            return ResponseEntity.ok("Produto atualizado com sucesso");
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteProduct(@RequestParam String nome) {
        productRepository.deleteByNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(nome + " foi deletado");
    }


}
