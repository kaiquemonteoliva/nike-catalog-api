package com.nike.apinike.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record ProductDto(
        @NotNull String nome,


        @NotNull String descricao,


        @NotNull String foto,


        @NotNull  String tamanho
) {


    public String getDescricao() {
        return descricao;
    }


    public String getFoto() {
        return foto;
    }


    public String getTamanho() {
        return tamanho;
    }

    public String getNome() {
        return nome;
    }
}
