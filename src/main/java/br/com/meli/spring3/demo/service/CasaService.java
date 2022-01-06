package br.com.meli.spring3.demo.service;

import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.CasaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
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
            ComodoSaidaDTO comodoDTO = new ComodoSaidaDTO(c.getNome(),c.getComprimento() * c.getLargura());
            comodos.add(comodoDTO);
        }
        return comodos;
    }

    public Double calculaArea(String nome) {
        Casa casa = casaRepository.buscaCasa(nome);
        double totalArea = 0;
        for (Comodo comodo : casa.getComodos()) {
            totalArea += comodo.getComprimento() * comodo.getLargura();
        }
        return totalArea;
    }

    public BigDecimal valorCasa(String nome) {
        return BigDecimal.valueOf(calculaArea(nome)).multiply(casaRepository.buscaCasa(nome).getBairro().getValorMetroQuadrado());
    }

    public Comodo maiorComodo(String nome) {
        Casa casa = casaRepository.buscaCasa(nome);

        int i;
        List<Comodo> comodos = casa.getComodos();
        Comodo maiorComodo = comodos.get(0);

        for(i=0;i<comodos.size();i++){
            Double areaAtual = comodos.get(i).getLargura() * comodos.get(i).getComprimento();
            Double areaSeguinte = comodos.get(i+1).getLargura() * comodos.get(i+1).getComprimento();
            if(areaAtual < areaSeguinte) {
                maiorComodo = comodos.get(i+1);
            }
        }
        return maiorComodo;
    }

    private boolean temComodo(Casa casa) {
        return casa.getComodos().size() > 0;
    }

}
