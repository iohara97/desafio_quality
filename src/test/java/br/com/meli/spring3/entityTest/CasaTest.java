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

/**
 * Classe para testar os métodos da Entity Casa
 */
public class CasaTest {

    /**
     * Método que deve confirmar se o método calcular área está correto
     * @throws IOException
     */
//    @Test
//    public void deveRetornarMetrosQuadrados() throws IOException {
//
//        List<Comodo> comodos = new ArrayList<>();
//        comodos.add(new Comodo("cozinha", 10, 15));
//        comodos.add(new Comodo("sala", 5, 12));
//
//        Casa casa = Casa.builder()
//                .nome("casa")
//                .endereco("Bairro da Flor")
//                .valorMetroQuadrado(new BigDecimal(100))
//                .comodos(comodos)
//                .build();
//        // cálculo total da área = 10 * 15 + 5 * 12 = 210
//        assert(casa.calculaArea() == 210);
//    }

    /**
     * Método que confirma se o método maiorComodo está correto
     * @throws IOException
     */
//    @Test
//    public void verificarMaiorComodo() throws IOException {
//
//        List<Comodo> comodos = new ArrayList<>();
//        comodos.add(new Comodo("cozinha", 10, 15));
//        comodos.add(new Comodo("sala", 5, 12));
//        Comodo comodoMaior = new Comodo("quarto1", 15, 12);
//        comodos.add(comodoMaior);
//        comodos.add(new Comodo("quarto2", 5, 12));
//
//        Casa casa = Casa.builder()
//                .nome("casa")
//                .endereco("Bairro da Flor")
//                .valorMetroQuadrado(new BigDecimal(100))
//                .comodos(comodos)
//                .build();
//
//        assert(casa.maiorComodo() == comodoMaior);
//
//    }

}
