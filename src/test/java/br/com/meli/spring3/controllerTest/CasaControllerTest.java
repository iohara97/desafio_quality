package br.com.meli.spring3.controllerTest;

import br.com.meli.spring3.demo.dto.CasaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootTest(classes = CasaControllerTest.class)
@AutoConfigureMockMvc
public class CasaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Cadastro de casa
    @Test
    public void deveRetornarObjetoCasa() throws Exception {
        // criar um objeto CasaDTO -> enviado json na request
        // com esse objecto da um converteDTO para casa
        // compara a response com o objeto convertido

        String json = "{\n" +
                "          \"nome\": \"Casa\",\n" +
                "          \"bairroDTO\": {\n" +
                "            \"nome\": \"Flor\",\n" +
                "            \"valorMetroQuadrado\": 500\n" +
                "          },\n" +
                "          \"comodosDTO\": [\n" +
                "            {\n" +
                "              \"nome\": \"Quarto\",\n" +
                "              \"largura\": 15,\n" +
                "              \"comprimento\": 12\n" +
                "            }\n" +
                "          ]\n" +
                "        }";


        ObjectMapper mapper = new ObjectMapper();
        CasaDTO casaDTO = mapper.readValue(json, CasaDTO.class);
        Casa casaEsperada = CasaDTO.converte(casaDTO);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Casa casaRecebida = mapper.readValue(response.body(), Casa.class);

        assert(casaRecebida.equals(casaEsperada));

    }

    // Deve retornar o numero total de metros quadrados da casa
    @Test
    public void get() throws Exception {
        MvcResult mcvResult = mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/area/Casa1")
        ).andReturn();
    }

}
