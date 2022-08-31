package br.com.uol.init.vingadores;


import br.com.uol.dto.init.json.VingadoresRestDTO;
import br.com.uol.model.Heroi;
import br.com.uol.service.HeroiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.arc.Priority;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@ApplicationScoped
@Priority(value = 20)
@Slf4j
public class ImportarVingadoresJson implements ImportVingadores{

    private final ObjectMapper objectMapper;
    private final HeroiService heroiService;

    public ImportarVingadoresJson(ObjectMapper objectMapper, HeroiService heroiService) {
        this.objectMapper = objectMapper;
        this.heroiService = heroiService;
    }

    @Override
    @Transactional
    public void importar() {


        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json"))
                    .headers("Content-Type", "text/plain;charset=UTF-8")
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient ().send(request, HttpResponse.BodyHandlers.ofString());
            VingadoresRestDTO vingadoresRestDTO = objectMapper.readValue (response.body (), VingadoresRestDTO.class);
            log.info("Response: {}", vingadoresRestDTO);
            List<Heroi> herois = vingadoresRestDTO.getVingadores ().stream ().map (v -> {
                Heroi heroi = new Heroi ();
                heroi.setNome (v.getCodinome ());
                return heroi;
            }).collect (java.util.stream.Collectors.toList ());
            heroiService.salvarTodos (herois);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info ("Importando vingadores");

    }
}
