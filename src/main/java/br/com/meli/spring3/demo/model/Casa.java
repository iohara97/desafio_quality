package br.com.meli.spring3.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    public BigDecimal valorCasa() {
        return BigDecimal.valueOf(calculaArea() * 800);
    }

    public Comodo maiorComodo() {
         return comodos.stream().max(Comparator.comparing(Comodo::calculaAreaComodo)).orElse(new Comodo());
    }

}
