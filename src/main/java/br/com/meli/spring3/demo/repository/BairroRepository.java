package br.com.meli.spring3.demo.repository;

import exception.BusinessException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BairroRepository {

    HashMap<String, BigDecimal> bairros = new HashMap<>();

    public BairroRepository(BairroRepository mock) {
        bairros.put("Republica", new BigDecimal(200));
        bairros.put("Dom Pedro", new BigDecimal(500));
        bairros.put("Belenzinho", new BigDecimal(400));
        bairros.put("Cambuci", new BigDecimal(600));
    }

        public boolean bairroExiste(String bairro) {
        if(bairros.containsKey(bairro)) {
            return true;
        }
        throw new BusinessException("Bairro não encontrado.");
    }
}
