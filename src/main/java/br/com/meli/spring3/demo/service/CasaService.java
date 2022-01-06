package br.com.meli.spring3.demo.service;

import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.CasaRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que contem a logica de negocio da entidade casa
 */
@Service
@NoArgsConstructor
public class CasaService {

    @Autowired
    private CasaRepository casaRepository;

    public CasaService(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    /**
     * Metodo para salvar uma casa e armazenar em um repository
     * @param casa
     * @return casa
     * @exception RuntimeException
     */
    public Casa salvar(Casa casa) {
        if (temComodo(casa)) {
            return casaRepository.salva(casa);
        } else {
            throw new RuntimeException("a casa deve ter pelos menos um comodo");
        }
    }

    /**
     * Metodo que busta uma casa em um Repository
     * @param nome
     * @return casa
     */
    public Casa findOne(String nome) {
        return casaRepository.buscaCasa(nome);
    }

    /**
     * Metodo para listar os comodosDTO de uma casa
     * @param casa
     * @return Lista de comodos
     */
    public List<ComodoSaidaDTO> listaComodoDTO(Casa casa) {
        List<ComodoSaidaDTO> comodos = new ArrayList<>();
        for (Comodo c : casa.getComodos()) {
            ComodoSaidaDTO comodoDTO = new ComodoSaidaDTO(c.getNome(),c.getComprimento() * c.getLargura());
            comodos.add(comodoDTO);
        }
        return comodos;
    }

    /**
     * Metodo para calcular a área de uma casa
     * @param nome
     * @return Área total de uma casa
     */
    public Double calculaArea(String nome) {
        Casa casa = casaRepository.buscaCasa(nome);
        double totalArea = 0;
        for (Comodo comodo : casa.getComodos()) {
            totalArea += comodo.getComprimento() * comodo.getLargura();
        }
        return totalArea;
    }

    /**
     * Metodo para retornar o valor de uma casa
     * @param nome
     * @return Valor de uma casa
     */
    public BigDecimal valorCasa(String nome) {
        return BigDecimal.valueOf(calculaArea(nome)).multiply(casaRepository.buscaCasa(nome).getBairro().getValorMetroQuadrado());
    }

    /**
     * Metodo para o maior comodo de uma casa
     * @param nome
     * @return Maior comodo de uma casa
     */
    public Comodo maiorComodo(String nome) {
        Casa casa = casaRepository.buscaCasa(nome);
        return casa.getComodos().stream().max(Comparator.comparing(c -> c.getLargura() * c.getComprimento())).orElse(new Comodo());
    }

    /**
     * Metodo para verificar se a casa tem comodo
     * @param casa
     * @return boolean
     */
    private boolean temComodo(Casa casa) {
        return casa.getComodos().size() > 0;
    }

}
