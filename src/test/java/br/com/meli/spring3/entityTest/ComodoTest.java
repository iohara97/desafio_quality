package br.com.meli.spring3.entityTest;

import br.com.meli.spring3.demo.entity.Comodo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Classe para testar os métodos da Entity Comodo
 */
public class ComodoTest {

    /**
     * Método para testar o método calcularAreaComodo
     * @throws IOException
     */
    @Test
    public void deveRetornarAreaComodo() throws IOException {

        Comodo comodo = new Comodo("cozinha", 10, 15);

        // calculo da area do comodo = 10 x 15 = 150
        assert(comodo.calculaAreaComodo() == 150);
    }

}
