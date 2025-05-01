package com.nike.apinike.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public record ProductDto(
        @NotNull String nome,


        @NotNull String descricao,


        @NotNull MultipartFile foto,


        @NotNull String tamanho
) {


    public String getDescricao() {
        return descricao;
    }


    public MultipartFile getFoto() {
        return foto;
    }


    public String getTamanho() {
        return tamanho;
    }

    public String getNome() {
        return nome;
    }
}
