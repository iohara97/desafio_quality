package br.com.meli.spring3.demo.dto;

import br.com.meli.spring3.demo.entity.Bairro;
import br.com.meli.spring3.demo.entity.Casa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class BairroDTO {

    @NotNull(message = "O bairro não pode estar vazio.")
    @NotEmpty(message = "O bairro não pode estar vazio.")
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String nome;

    @NotNull(message = "O valor do metro quadrado no bairro não pode estar vazio.")
    @DecimalMin(value= "0.0", inclusive = false, message = "O valor do metro quadrado no bairro não pode estar vazio.")
    @Digits(integer=10, fraction=2, message= "O valor não pode exceder 13 caracteres, com apenas duas casas decimais.")
    private BigDecimal valorMetroQuadrado;

    public static Bairro converte(BairroDTO bairroDTO) {
        Bairro bairro = Bairro.builder()
                .nome(bairroDTO.getNome())
                .valorMetroQuadrado(bairroDTO.getValorMetroQuadrado())
                .build();
        return bairro;

    }

}