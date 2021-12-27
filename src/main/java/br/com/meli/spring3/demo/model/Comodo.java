package br.com.meli.spring3.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comodo {

    private String nome;
    private double largura;
    private double comprimento;

    public double calculaAreaComodo() {
        return largura * comprimento;
    }

}
