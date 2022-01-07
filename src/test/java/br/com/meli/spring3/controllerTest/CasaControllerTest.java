package br.com.meli.spring3.controllerTest;

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

@SpringBootTest(classes = CasaControllerTest.class)
@AutoConfigureMockMvc
public class CasaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Cadastro de casa
    @Test
    public void deveRetornarObjetoCasa() throws Exception {

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
        //List<Comodo> comodos = new ArrayList<Comodo>();
        //comodos.add(new Comodo("quarto", 15,12));
        //Casa c = new Casa("Casa", new Bairro("Flor", new BigDecimal(500)), comodos );


        //System.out.println(json);

        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/criarCasa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isCreated()
                );
    }

    // Deve retornar o numero total de metros quadrados da casa
    @Test
    public void get() throws Exception {
        MvcResult mcvResult = mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/area/Casa1")
        ).andReturn();
    }

}
