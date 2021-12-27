package br.com.meli.spring3.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Casa {

    private String nome;
    private String endereco;
    private List<Comodo> comodos;

    public double calculaArea() {
        double totalArea = 0;
        for (Comodo comodo : comodos) {
            totalArea += comodo.calculaAreaComodo();
        }
        return totalArea;
    }
}
