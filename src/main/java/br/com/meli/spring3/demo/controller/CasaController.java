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

/**
 * Classe responsavel por tratar as requisições
 */
@RestController
@RequestMapping()
public class CasaController {


    @Autowired
    private CasaService casaService;

    /**
     * Metodo para recebe o post e receber uma casa
     * @param dto
     * @return HttpResponse com cadastro de uma casa e o status created
     */
    @PostMapping
    public ResponseEntity<Casa> cadastra(@Valid @RequestBody CasaDTO dto) {
        Casa casa = CasaDTO.converte(dto);
        casaService.salvar(casa);
        return ResponseEntity.status(HttpStatus.CREATED).body(casa);
    }

    /**
     * Metodo para retornar area total de uma casa
     * @param nomeDaCasa
     * @return Area total
     */
    @GetMapping("/area/{nomeDaCasa}")
    public ResponseEntity<Double> calculaArea(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.calculaArea(nomeDaCasa));
    }

    /**
     * Metodo para retornar o valor da casa
     * @param nomeDaCasa
     * @return Valor da casa
     */
    @GetMapping("/valor/{nomeDaCasa}")
    public ResponseEntity<BigDecimal> valorCasa(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.valorCasa(nomeDaCasa));
    }

    /**
     * Metodo para retornar o maior comodo da casa
     * @param nomeDaCasa
     * @return Maior comodo da casa
     */
    @GetMapping("/maiorComodo/{nomeDaCasa}")
    public ResponseEntity<Comodo> maiorComodo(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.maiorComodo(nomeDaCasa));
    }

    /**
     * Metodo para retornar uma lista de comodo da casa exibindo a área total de cada comodo
     * @param nomeDaCasa
     * @return Lista de comodo com sua respectiva área total
     */
    @GetMapping("/areaComodos/{nomeDaCasa}")
    public ResponseEntity<List<ComodoSaidaDTO>> areaComodos(@PathVariable String nomeDaCasa) {
        Casa casa = casaService.findOne(nomeDaCasa);
        return ResponseEntity.ok(casaService.listaComodoDTO(casa));
    }
}
