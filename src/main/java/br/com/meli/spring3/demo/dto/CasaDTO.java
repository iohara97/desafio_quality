package br.com.meli.spring3.demo.dto;


import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasaDTO {
    private String nome;
    private String endereco;
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
