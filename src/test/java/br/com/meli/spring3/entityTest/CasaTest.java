package br.com.meli.spring3.entityTest;

import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.BairroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CasaTest {

    @Test
    public void deveRetornarMetrosQuadrados() throws IOException {

        List<Comodo> comodos = new ArrayList<>();
        comodos.add(new Comodo("cozinha", 10, 15));
        comodos.add(new Comodo("sala", 5, 12));

        Casa casa = Casa.builder()
                .nome("casa")
                .endereco("Bairro da Flor")
                .valorMetroQuadrado(new BigDecimal(100))
                .comodos(comodos)
                .build();
        // cálculo total da área = 10 * 15 + 5 * 12 = 210
        assert(casa.calculaArea() == 210);
    }

    @Test
    public void verificaBairroNoRepositorioDeBairros() throws IOException {

        BairroRepository mock = Mockito.mock(BairroRepository.class);
        BairroRepository bairroRepository = new BairroRepository(mock);

        String bairro = "Belenzinho";

        // intera com bairros do repository
        assert(bairroRepository.bairroExiste(bairro));
    }
}
