package br.com.meli.spring3.demo.dto;


import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CasaDTO {

    @NotNull(message = "O nome da propriedade não pode estar vazio.")
    @NotEmpty(message = "O nome da propriedade não pode estar vazio.")
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z].*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    private String nome;

    @NotNull(message = "O bairro não pode estar vazio.")
    @NotEmpty(message = "O bairro não pode estar vazio.")
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String endereco;
//
    @NotNull(message = "O valor do metro quadrado no bairro não pode estar vazio.")
    @DecimalMin(value= "0.0", inclusive = false, message = "O valor do metro quadrado no bairro não pode estar vazio.")
    @Digits(integer=10, fraction=2, message= "O valor não pode exceder 13 caracteres, com apenas duas casas decimais.")
    private BigDecimal valorMetroQuadrado;
    private List<Comodo> comodos;

    public static Casa converte(CasaDTO dto) {
        Casa casa = Casa.builder()
                .nome(dto.getNome())
                .endereco(dto.getEndereco())
                .valorMetroQuadrado(dto.getValorMetroQuadrado())
                .comodos(dto.getComodos())
                .build();
        return casa;
    }

    public static CasaDTO converte(Casa casa) {
        return CasaDTO.builder()
                .nome(casa.getNome())
                .endereco(casa.getEndereco())
                .valorMetroQuadrado(casa.getValorMetroQuadrado())
                .comodos(casa.getComodos())
                .build();
    }

    /* exemplo JSON
    {
        "nome" : "mansão",
        "endereco" : "Jardins",
        "comodos" : [
            {
            "nome" : "cozinha",
            "largura" : 10,
            "comprimento": 15
            },
            {
            "nome" : "sala",
            "largura" : 5,
            "comprimento": 12
            }
        ]
    }
*/

}
