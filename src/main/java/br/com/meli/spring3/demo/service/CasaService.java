package br.com.meli.spring3.demo.service;

import br.com.meli.spring3.demo.model.Casa;
import br.com.meli.spring3.demo.repository.CasaRepository;
import org.springframework.stereotype.Service;

@Service
public class CasaService {

    private CasaRepository casaRepository;

    public CasaService(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    public void salvar(Casa casa) {
        if (temComodo(casa)) {
            casaRepository.salva(casa);
        } else {
            throw new RuntimeException("a casa deve ter pelos menos um comodo");
        }
    }

    private boolean temComodo(Casa casa) {
        return casa.getComodos().size() > 0;
    }

    public Double calculaArea(String nome) {

    }
}
