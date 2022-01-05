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
public class ComodoDTO {

        private String nome;
        private double largura;
        private double comprimento;

        public static Comodo converte(ComodoDTO dto) {
            Comodo comodos = Comodo.builder()
                    .nome(dto.getNome())
                    .largura(dto.getLargura())
                    .comprimento(dto.getComprimento())
                    .build();
            return comodos;
        }

        public static ComodoDTO converte(Comodo comodos) {
            return ComodoDTO.builder()
                    .nome(comodos.getNome())
                    .largura(comodos.getLargura())
                    .comprimento(comodos.getComprimento())
                    .build();
        }

        public static List<Comodo> converte(List<ComodoDTO> comodos) {
            return comodos.stream().map(c -> converte(c)).collect(Collectors.toList());
        }


}
