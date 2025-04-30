package com.nike.apinike.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
public record ProductDto(
        @NotNull String nome,


        @NotNull String descricao,


        @NotNull MultipartFile foto,


        @NotNull  String tamanho
) {


    public String getDescricao() {
        return descricao;
    }


    public byte[] getFoto() throws IOException {return foto.getBytes();}


    public String getTamanho() {
        return tamanho;
    }

    public String getNome() {
        return nome;
    }
}
