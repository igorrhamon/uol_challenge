package br.com.uol.service;

import br.com.uol.controller.HeroiRepository;
import br.com.uol.model.Heroi;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class HeroiService {

    private final HeroiRepository heroiRepository;

    public HeroiService(HeroiRepository heroiRepository) {
        this.heroiRepository = heroiRepository;
    }

    @Transactional
    public Heroi salvar(Heroi heroi) {
         heroiRepository.persist(heroi);
         return heroi;
    }

    public Heroi buscarPorId(Long id) {
        return heroiRepository.findById(id);
    }

    public Heroi buscarPorNome(String nome) {
        return heroiRepository.findByNome(nome);
    }

    @Transactional
    public void remover(Long id) {
        heroiRepository.deleteById(id);
    }

    @Transactional
    public void atualizar(Heroi heroi) {
        heroiRepository.persist(heroi);
    }

    public List<Heroi> buscarTodos() {
        return heroiRepository.listAll ();
    }


    @Transactional
    public List<Heroi> salvarTodos(List<Heroi> herois) {
        herois.forEach(heroiRepository::persist);
        return herois;
    }

}
