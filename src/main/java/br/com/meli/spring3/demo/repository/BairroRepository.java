package br.com.meli.spring3.demo.repository;

import br.com.meli.spring3.demo.entity.Bairro;
import exception.BusinessException;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.*;

@Data
@Repository
public class BairroRepository {

    private List<Bairro> bairros;

    public BairroRepository() { }

    public boolean cadastrarBairro(Bairro bairro) {
        if(bairros.contains(bairro)) {
            return false;
        } else {
            bairros.add(bairro);
            return true;
        }
    }

    public boolean bairroExiste(Bairro bairro) {
        if(bairros.contains(bairro)) {
            return true;
        }
        throw new BusinessException("Bairro não encontrado.");
    }
}
