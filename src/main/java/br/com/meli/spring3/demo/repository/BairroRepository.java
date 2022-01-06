package br.com.meli.spring3.demo.repository;

import br.com.meli.spring3.demo.entity.Bairro;
import exception.BusinessException;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Classe que ira percistir os dados em uma lista de bairros
 */
@Data
@Repository
public class BairroRepository {

    private List<Bairro> bairros;

    public BairroRepository() { }

    /**
     * Metodo para verificar se o bairro está cadastrado
     * @param bairro
     * @return boolean
     */
    public boolean cadastrarBairro(Bairro bairro) {
        if(bairros.contains(bairro)) {
            return false;
        } else {
            bairros.add(bairro);
            return true;
        }
    }

    /**
     * Metodo que retorna verdadeiro se o bairro encontra-se na lista
     * @param bairro
     * @return boolean
     * @exception BusinessException
     */
    public boolean bairroExiste(Bairro bairro) {
        if(bairros.contains(bairro)) {
            return true;
        }
        throw new BusinessException("Bairro não encontrado.");
    }
}
