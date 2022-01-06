package br.com.meli.spring3.utils;

import br.com.meli.spring3.demo.entity.Bairro;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para mockar uma lista de casas
 */
@Data
public class MockListaCasas {

    private List<Casa> casas = new ArrayList<>();

    public MockListaCasas(){
        Bairro bairroFlor = new Bairro("Flor", new BigDecimal(250));
        Bairro bairroRepublica = new Bairro("Republica", new BigDecimal(500));

        Comodo quarto = new Comodo("quarto", 3,4); //12
        Comodo sala = new Comodo("sala", 5,4); //20
        Comodo cozinha = new Comodo("cozinha", 6, 3); // 18
        Comodo banheiro = new Comodo("banheiro", 2, 2.5); // 5

        List<Comodo> listaComodos1 = new ArrayList<>();
        listaComodos1.add(quarto);
        listaComodos1.add(banheiro);

        List<Comodo> listaComodos2 = new ArrayList<>();
        listaComodos2.add(quarto);
        listaComodos2.add(sala);
        listaComodos2.add(cozinha);
        listaComodos2.add(banheiro);

        this.casas.add(new Casa("Casa1", bairroFlor , listaComodos1));
        this.casas.add(new Casa("Casa2", bairroRepublica, listaComodos2));
    }
}
