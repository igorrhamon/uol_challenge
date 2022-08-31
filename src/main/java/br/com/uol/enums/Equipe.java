package br.com.uol.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Equipe {
    VINGADORES("Vingadores"),
    LIGA_DA_JUSTICA("Liga da Justiça");

    private String nome;

}
