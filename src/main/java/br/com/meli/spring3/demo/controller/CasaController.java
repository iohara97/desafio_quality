package br.com.meli.spring3.demo.controller;

import br.com.meli.spring3.demo.dto.CasaDTO;
import br.com.meli.spring3.demo.model.Casa;
import br.com.meli.spring3.demo.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class CasaController {

    @Autowired
    private CasaService casaService;

    // Post para receber os dados da casa
    @PostMapping
    public ResponseEntity<Casa> cadastra(@RequestBody CasaDTO dto) {
        Casa casa = CasaDTO.converte(dto);
        casaService.salvar(casa);
        return ResponseEntity.ok(casa);
    }

    // Retorne o numero total de metros quadrados da casa
    @GetMapping("/area/{nomeDaCasa}")
    public ResponseEntity<Double> calculaArea(@PathVariable String nome) {
        return ResponseEntity.ok(casaService.calculaArea(nome));
    }


}
