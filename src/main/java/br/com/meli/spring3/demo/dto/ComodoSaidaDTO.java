package br.com.meli.spring3.demo.dto;

import br.com.meli.spring3.demo.entity.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComodoSaidaDTO {
    private String nome;
    private double areaTotal;

    /*public static ComodoSaidaDTO converte(Comodo comodos) {
        return ComodoSaidaDTO.builder()
                .nome(comodos.getNome())
                .areaTotal(comodos.calculaAreaComodo())
                .build();
    }*/
}
