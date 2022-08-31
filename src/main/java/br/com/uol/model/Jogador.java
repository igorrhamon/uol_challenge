package br.com.uol.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Jogador extends PanacheEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefone", length = 11)
    private String telefone;

    @OneToOne(cascade = CascadeType.MERGE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "heroi_id", nullable = false, unique = true)
    private Heroi heroi;

    @Override
    public String toString() {
        return getClass ().getSimpleName () + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "heroi = " + heroi + ")";
    }
}
