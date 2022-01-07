package br.com.meli.spring3.demo.dto;

import br.com.meli.spring3.demo.entity.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe para otimizar a apresentações dos dados exibindo uma resposta personalizada
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComodoSaidaDTO {
    private String nome;
    private double areaTotal;

    /**
     * Metodo que recebe um objeto comodo e retorna um comodoSaidaDTO
     * @param comodo (Comodo)
     * @return ComodoSaidaDTO (ComodoSaidaDTO)
     */
    public static ComodoSaidaDTO converte(Comodo comodo) {
        return ComodoSaidaDTO.builder()
                .nome(comodo.getNome())
                .areaTotal(comodo.getLargura() * comodo.getComprimento())
                .build();
    }

    /**
     * Metodo que recebe uma lista de objeto comodo e retorna uma lista de comodoSaidaDTO
     * @param comodos (List<Comodo>)
     * @return Lista ComodoSaidaDTO (List<ComodoSaidaDTO>)
     */
    public static List<ComodoSaidaDTO> converte(List<Comodo> comodos) {
        return comodos.stream().map(c -> converte(c)).collect(Collectors.toList());
    }
}
