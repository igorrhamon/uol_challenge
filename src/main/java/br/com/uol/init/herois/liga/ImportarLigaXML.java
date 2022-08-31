package br.com.uol.init.herois.liga;

import br.com.uol.dto.init.xml.LigadaJusticaRequestDTO;
import br.com.uol.enums.Equipe;
import br.com.uol.model.Heroi;
import br.com.uol.service.HeroiService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.quarkus.arc.Priority;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
@Priority(value = 20)
@Slf4j
public class ImportarLigaXML implements ImportLiga {


    private final HeroiService heroiService;



    public ImportarLigaXML( HeroiService heroiService) {
        this.heroiService = heroiService;
    }

    @Override
    public void importar() {
       log.info("Importando Liga XML");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI ("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml"))
                    .headers("Content-Type", "xml/plain;charset=UTF-8")
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient ().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response: {}", response.body ());
            XmlMapper xmlMapper = new XmlMapper ();
            LigadaJusticaRequestDTO ligadaJusticaRequestDTO = xmlMapper.readValue (response.body (), LigadaJusticaRequestDTO.class);
            log.info("Response: {}", ligadaJusticaRequestDTO);
            log.info ("Fim importação Liga XML");
            List<Heroi> herois = Arrays.stream(ligadaJusticaRequestDTO.getCodinomes ()).map (
                    codinome -> {
                        Heroi heroi = new Heroi ();
                        heroi.setNome (codinome.getCodinome ());
                        heroi.setEquipe (Equipe.LIGA_DA_JUSTICA);
                        return heroi;
                    }
            ).collect (Collectors.toList ());
            heroiService.salvarTodos (herois);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException (e);
        }
    }
}
