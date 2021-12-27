package br.com.meli.spring3.demo.service;

import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.model.Casa;
import br.com.meli.spring3.demo.model.Comodo;
import br.com.meli.spring3.demo.repository.CasaRepository;
import org.springframework.stereotype.Service;

import javax.lang.model.type.ArrayType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public Casa findOne(String nome) {
        return casaRepository.findOne(nome);
    }

    public List<ComodoSaidaDTO> listaComodoDTO(Casa casa) {
        List<ComodoSaidaDTO> comodos = new ArrayList<>();
        for (Comodo c : casa.getComodos()) {
            ComodoSaidaDTO comodoDTO = new ComodoSaidaDTO(c.getNome(),c.calculaAreaComodo());
            comodos.add(comodoDTO);
        }
        return comodos;
    }

    public Double calculaArea(String nome) {
        return casaRepository.calculaArea(nome);
    }

    public BigDecimal valorCasa(String nome) {
        return casaRepository.valorCasa(nome);
    }

    public Comodo maiorComodo(String nome) {
        return casaRepository.maiorComodo(nome);
    }

    private boolean temComodo(Casa casa) {
        return casa.getComodos().size() > 0;
    }

}
