package br.com.meli.spring3.demo.repository;

import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CasaRepository {

    private List<Casa> casas = new ArrayList<Casa>();

    public void salva(Casa casa) {
        casas.add(casa);
    }

    public double calculaArea(String nome) {
        return findOne(nome).calculaArea();
    }

    public BigDecimal valorCasa(String nome) {
        return findOne(nome).valorCasa();
    }

    public Comodo maiorComodo(String nome) {
        return findOne(nome).maiorComodo();
    }

    public Casa findOne(String nome) {
        Optional<Casa> optinal = casas.stream().filter(c -> c.getNome().equals(nome)).findAny();
        return optinal.get();
    }

}
