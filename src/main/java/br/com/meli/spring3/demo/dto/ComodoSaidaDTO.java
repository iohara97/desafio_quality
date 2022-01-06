package br.com.meli.spring3.demo.dto;

import br.com.meli.spring3.demo.entity.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComodoSaidaDTO {
    private String nome;
    private double areaTotal;

    public static ComodoSaidaDTO converte(Comodo comodos) {
        return ComodoSaidaDTO.builder()
                .nome(comodos.getNome())
                .areaTotal(comodos.getLargura() * comodos.getComprimento())
                .build();
    }

    public static List<ComodoSaidaDTO> converte(List<Comodo> comodos) {
        return comodos.stream().map(c -> converte(c)).collect(Collectors.toList());
    }
}
