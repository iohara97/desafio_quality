package br.com.meli.spring3.demo.service;

import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.CasaRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
@Data
public class CasaService {

    private CasaRepository casaRepository;

    public CasaService(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    public Casa salvar(Casa casa) {
        if (temComodo(casa)) {
            return casaRepository.salva(casa);
        } else {
            throw new RuntimeException("a casa deve ter pelos menos um comodo");
        }
    }

    public Casa findOne(String nome) {
        return casaRepository.buscaCasa(nome);
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
        return casa.getComodos().stream().max(Comparator.comparing(c -> c.getLargura() * c.getComprimento())).orElse(new Comodo());
    }

    private boolean temComodo(Casa casa) {
        return casa.getComodos().size() > 0;
    }

}
