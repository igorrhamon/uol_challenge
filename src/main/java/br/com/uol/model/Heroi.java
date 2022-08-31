package br.com.uol.model;

import br.com.uol.enums.Equipe;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Slf4j
public class Heroi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "equipe", nullable = false)
    private Equipe equipe;

    @OneToOne(mappedBy = "heroi", orphanRemoval = true)
    private Jogador jogador;

    @Override
    public String toString() {
        return getClass ().getSimpleName () + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "equipe = " + equipe + ")";
    }
}
