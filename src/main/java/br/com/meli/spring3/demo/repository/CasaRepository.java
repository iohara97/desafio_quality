package br.com.meli.spring3.demo.repository;

import br.com.meli.spring3.demo.entity.Casa;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsavel por gerir a lista de casas
 */
@Repository
@Data
public class CasaRepository {

    private List<Casa> casas = new ArrayList<Casa>();

    /**
     * Metodo que salva casa em uma lista
     * @param casa
     * @return casa
     */
    public Casa salva(Casa casa) {
        casas.add(casa);
        return casa;
    }

    /**
     * Metodo de procura uma respectiva casa
     * @param nome
     * @return casa localizada
     */
    public Casa buscaCasa(String nome) {
        Optional<Casa> optinal = casas.stream().filter(c -> c.getNome().equals(nome)).findAny();
        return optinal.get();
    }

}
