package br.com.meli.spring3.demo.dto;

import br.com.meli.spring3.demo.entity.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe para otimizar a apresentações dos dados exibindo uma resposta personalizada
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ComodoDTO {
        @NotNull(message = "O nome do cômodo não pode estar vazio.")
        @NotEmpty(message = "O nome do cômodo não pode estar vazio.")
        @NotBlank(message = "O nome do cômodo não pode estar vazio.")
        @Pattern(regexp = "^[A-Z].*$", message = "O nome do cômodo deve começar com uma letra maiúscula.")
        @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
        private String nome;
        @NotNull(message = "A largura do cômodo não pode estar vazia.")
        @Max(value = 25, message = "A largura máxima permitida por cômodo é de 25 metros.")
        private double largura;
        @NotNull(message = "O comprimento do cômodo não pode estar vazgo.")
        @Max(value = 33, message = "O comprimento máximo permitido por cômodo é de 33 metros.")
        private double comprimento;

        /**
         *  Metodo que converte um DTO para sua respectiva entidade
         * @param dto
         * @return Entidade comodos
         */
        public static Comodo converte(ComodoDTO dto) {
            Comodo comodos = Comodo.builder()
                    .nome(dto.getNome())
                    .largura(dto.getLargura())
                    .comprimento(dto.getComprimento())
                    .build();
            return comodos;
        }

        /**
         *  Metodo que converte uma entidade para uma DTO
         * @param comodos
         * @return ComodoDTO
         */
        public static ComodoDTO converte(Comodo comodos) {
            return ComodoDTO.builder()
                    .nome(comodos.getNome())
                    .largura(comodos.getLargura())
                    .comprimento(comodos.getComprimento())
                    .build();
        }

        /**
         * Metodo que converte uma Lista de comodoDTO para Lista de comodos
         * @param comodos
         * @return Lista de comodos
         */
        public static List<Comodo> converte(List<ComodoDTO> comodos) {
            return comodos.stream().map(c -> converte(c)).collect(Collectors.toList());
        }

}
