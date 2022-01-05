package br.com.meli.spring3.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Casa {

    private String nome;
    private String endereco;
    private BigDecimal valorMetroQuadrado;
    private List<Comodo> comodos;

    public double calculaArea() {
        double totalArea = 0;
        for (Comodo comodo : comodos) {
            totalArea += comodo.calculaAreaComodo();
        }
        return totalArea;
    }

    public BigDecimal valorCasa() {
        return BigDecimal.valueOf(calculaArea()).multiply(valorMetroQuadrado);
    }

    public Comodo maiorComodo() {
         return comodos.stream().max(Comparator.comparing(Comodo::calculaAreaComodo)).orElse(new Comodo());
    }

}
