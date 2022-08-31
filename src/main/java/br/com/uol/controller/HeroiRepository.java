package br.com.uol.controller;

import br.com.uol.model.Heroi;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HeroiRepository implements PanacheRepository<Heroi> {


    public Heroi findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}
