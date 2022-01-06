package br.com.meli.spring3.repositoryTest;

import br.com.meli.spring3.demo.entity.Bairro;
import br.com.meli.spring3.demo.repository.BairroRepository;
import exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BairroRepositoryTest {

    /**
     * Método usado para testar que o bairro não estava cadastrado no repository
     */
    @Test
    public void testaBairroQueNaoExiste() {

        List<Bairro> bairros = new ArrayList<>();

        Bairro bairro1 = new Bairro("Flor", new BigDecimal(300));
        Bairro bairro2 = new Bairro("Republica", new BigDecimal(500));
        Bairro bairro3 = new Bairro("Dom Pedro", new BigDecimal(400));

        bairros.add(bairro1);
        bairros.add(bairro2);
        bairros.add(bairro3);

        Bairro bairroParaCadastrar = new Bairro("Luzia", new BigDecimal(600));

        BairroRepository bairroRepository = new BairroRepository();
        bairroRepository.setBairros(bairros);

        BusinessException excecaoEsperada = assertThrows(
                BusinessException.class,
                () -> bairroRepository.bairroExiste(bairroParaCadastrar) //act
        );

        assertTrue(excecaoEsperada.getMessage().contains("Bairro não encontrado."));
    }

    /**
     * Método usado para testar que o bairro estava cadastrado no repository
     */
    @Test
    public void testaBairroQueExiste() {

        List<Bairro> bairros = new ArrayList<>();

        Bairro bairro1 = new Bairro("Flor", new BigDecimal(300));
        Bairro bairro2 = new Bairro("Republica", new BigDecimal(500));
        Bairro bairro3 = new Bairro("Dom Pedro", new BigDecimal(400));

        bairros.add(bairro1);
        bairros.add(bairro2);
        bairros.add(bairro3);

        BairroRepository bairroRepository = new BairroRepository();
        bairroRepository.setBairros(bairros);

        assertTrue(bairroRepository.bairroExiste(bairro1));
    }

    /**
     * Método para confirmar se o bairro foi salvo
     */
    @Test
    public void deveSalvarBairro() {

        List<Bairro> bairros = new ArrayList<>();

        Bairro bairro1 = new Bairro("Flor", new BigDecimal(300));
        Bairro bairro2 = new Bairro("Republica", new BigDecimal(500));
        Bairro bairro3 = new Bairro("Dom Pedro", new BigDecimal(400));

        bairros.add(bairro1);
        bairros.add(bairro2);
        bairros.add(bairro3);

        Bairro bairroParaCadastrar = new Bairro("Luzia", new BigDecimal(600));

        BairroRepository bairroRepository = new BairroRepository();
        bairroRepository.setBairros(bairros);

        assertTrue(bairroRepository.cadastrarBairro(bairroParaCadastrar));

    }

    /**
     * Método que não salva o bairro, pois ele já existe
     */
    @Test
    public void naoDeveSalvarBairro() {

        List<Bairro> bairros = new ArrayList<>();

        Bairro bairro1 = new Bairro("Flor", new BigDecimal(300));
        Bairro bairro2 = new Bairro("Republica", new BigDecimal(500));
        Bairro bairro3 = new Bairro("Dom Pedro", new BigDecimal(400));

        bairros.add(bairro1);
        bairros.add(bairro2);
        bairros.add(bairro3);


        BairroRepository bairroRepository = new BairroRepository();
        bairroRepository.setBairros(bairros);

        assertFalse(bairroRepository.cadastrarBairro(bairro1));
    }
}
