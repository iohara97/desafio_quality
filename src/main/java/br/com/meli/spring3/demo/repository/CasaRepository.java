package br.com.meli.spring3.demo.repository;

import br.com.meli.spring3.demo.entity.Casa;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Data
public class CasaRepository {

    private List<Casa> casas = new ArrayList<Casa>();

    public Casa salva(Casa casa) {
        casas.add(casa);
        return casa;
    }

    public Casa buscaCasa(String nome) {
        Optional<Casa> optinal = casas.stream().filter(c -> c.getNome().equals(nome)).findAny();
        return optinal.get();
    }

}
