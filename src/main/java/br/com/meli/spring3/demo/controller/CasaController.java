package br.com.meli.spring3.demo.controller;

import br.com.meli.spring3.demo.dto.CasaDTO;
import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping()
public class CasaController {

    @Autowired
    private CasaService casaService;

    // Post para receber os dados da casa
    @PostMapping
    public ResponseEntity<Casa> cadastra(@Valid @RequestBody CasaDTO dto) {
        Casa casa = CasaDTO.converte(dto);
        casaService.salvar(casa);
        return ResponseEntity.status(HttpStatus.CREATED).body(casa);
    }

    // Retorne o numero total de metros quadrados da casa
    @GetMapping("/area/{nomeDaCasa}")
    public ResponseEntity<Double> calculaArea(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.calculaArea(nomeDaCasa));
    }

    // Retorne o valor da casa tendo em consideração o valor por metro quadrado
    @GetMapping("/valor/{nomeDaCasa}")
    public ResponseEntity<BigDecimal> valorCasa(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.valorCasa(nomeDaCasa));
    }

    // Retorne o maior comado
    @GetMapping("/maiorComodo/{nomeDaCasa}")
    public ResponseEntity<Comodo> maiorComodo(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.maiorComodo(nomeDaCasa));
    }

    // Retorne a quantidade de metros quadrados por cômodo
    @GetMapping("/areaComodos/{nomeDaCasa}")
    public ResponseEntity<List<ComodoSaidaDTO>> areaComodos(@PathVariable String nomeDaCasa) {
        Casa casa = casaService.findOne(nomeDaCasa);
        return ResponseEntity.ok(casaService.listaComodoDTO(casa));
    }
}
