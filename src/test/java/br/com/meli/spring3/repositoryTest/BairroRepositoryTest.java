package br.com.meli.spring3.repositoryTest;

import br.com.meli.spring3.demo.repository.BairroRepository;
import exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BairroRepositoryTest {

    /**
     * Método usado para testar que o bairro não estava cadastrado no repository
     */
    @Test
    public void testaBairroQueNaoExiste() {

        BairroRepository mock = Mockito.mock(BairroRepository.class);
        BairroRepository bairroRepository = new BairroRepository();

        String bairro = "Para";

        BusinessException excecaoEsperada = assertThrows(
                BusinessException.class,
                () -> bairroRepository.bairroExiste(bairro) //act
        );

        assertTrue(excecaoEsperada.getMessage().contains("Bairro não encontrado."));
    }

    /**
     * Método usado para testar que o bairro estava cadastrado no repository
     */
    @Test
    public void testaBairroQueExiste() {

        BairroRepository mock = Mockito.mock(BairroRepository.class);
        BairroRepository bairroRepository = new BairroRepository();

        String bairro = "Belenzinho";

        assertTrue(bairroRepository.bairroExiste(bairro));
    }
}
