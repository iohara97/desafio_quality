package br.com.meli.spring3.entityTest;

import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.BairroRepository;
import exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testaBairroQueNaoExiste() {

        BairroRepository mock = Mockito.mock(BairroRepository.class);
        BairroRepository bairroRepository = new BairroRepository(mock);

        String bairro = "Para";

        // intera com bairros do repository
        //assert(bairroRepository.bairroExiste(bairro));

        BusinessException excecaoEsperada = assertThrows(
                BusinessException.class,
                () -> bairroRepository.bairroExiste(bairro) //act
        );

        assertTrue(excecaoEsperada.getMessage().contains("Bairro não encontrado."));
    }

    @Test
    public void testaBairroQueExiste() {

        BairroRepository mock = Mockito.mock(BairroRepository.class);
        BairroRepository bairroRepository = new BairroRepository(mock);

        String bairro = "Belenzinho";

        assertTrue(bairroRepository.bairroExiste(bairro));
    }


}
