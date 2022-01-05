package br.com.meli.spring3.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {
    private String nome;
    private BigDecimal valorMetroQuadrado;
}
