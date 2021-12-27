package br.com.meli.spring3.demo.repository;

import br.com.meli.spring3.demo.model.Casa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CasaRepository {

    private List<Casa> casas = new ArrayList<Casa>();

    public void salva(Casa casa) {
        casas.add(casa);
    }

}
