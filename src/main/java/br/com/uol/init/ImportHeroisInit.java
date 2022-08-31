package br.com.uol.init;

import br.com.uol.init.liga.ImportLiga;
import br.com.uol.init.vingadores.ImportVingadores;
import br.com.uol.service.HeroiService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;

@ApplicationScoped
@Slf4j
public class ImportHeroisInit {
    private final HeroiService heroiService;
    private final Instance<ImportVingadores> importVingadores;

    private final Instance<ImportLiga> importLiga;

    public ImportHeroisInit(HeroiService heroiService, Instance<ImportVingadores> importVingadores, Instance<ImportLiga> importLiga) {
        this.heroiService = heroiService;
        this.importVingadores = importVingadores;
        this.importLiga = importLiga;
    }

    public void startup() {
        if(heroiService.buscarTodos().isEmpty()) {
            log.info("Iniciando importação de vingadores");
            importVingadores.forEach(ImportVingadores::importar);
            log.info("Importação de vingadores finalizada");
            log.info("Iniciando importação de liga");
            importLiga.forEach(ImportLiga::importar);
            log.info("Importação de liga finalizada");
        }
    }

}
