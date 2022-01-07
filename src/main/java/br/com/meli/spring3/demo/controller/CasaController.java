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
     * Metodo para receber o post de uma casa e devolver a mesma
     * @param dto (CasaDTO)
     * @return HttpResponse com cadastro de uma casa (Casa) e o status created
     */
    @PostMapping
    public ResponseEntity<Casa> cadastra(@Valid @RequestBody CasaDTO dto) {
        Casa casa = CasaDTO.converte(dto);
        casaService.salvar(casa);
        return ResponseEntity.status(HttpStatus.CREATED).body(casa);
    }

    /**
     * Metodo para retornar area total de uma casa
     * @param nomeDaCasa (String)
     * @return HttpResponse com a área total (Double)
     */
    @GetMapping("/area/{nomeDaCasa}")
    public ResponseEntity<Double> calculaArea(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.calculaArea(nomeDaCasa));
    }

    /**
     * Metodo para retornar o valor da casa
     * @param nomeDaCasa (String)
     * @return HttpResponse com o valor da casa (BigDecimal)
     */
    @GetMapping("/valor/{nomeDaCasa}")
    public ResponseEntity<BigDecimal> valorCasa(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.valorCasa(nomeDaCasa));
    }

    /**
     * Metodo para retornar o maior comodo da casa
     * @param nomeDaCasa (String)
     * @return HttpResponse com o maior comodo da casa (Comodo)
     */
    @GetMapping("/maiorComodo/{nomeDaCasa}")
    public ResponseEntity<Comodo> maiorComodo(@PathVariable String nomeDaCasa) {
        return ResponseEntity.ok(casaService.maiorComodo(nomeDaCasa));
    }

    /**
     * Metodo para retornar uma lista de comodos da casa exibindo a área total de cada comodo
     * @param nomeDaCasa (String)
     * @return HttpResponse com a lista de comodos com sua respectiva área total (List<ComodoSaidaDTO>)
     */
    @GetMapping("/areaComodos/{nomeDaCasa}")
    public ResponseEntity<List<ComodoSaidaDTO>> areaComodos(@PathVariable String nomeDaCasa) {
        Casa casa = casaService.findOne(nomeDaCasa);
        return ResponseEntity.ok(casaService.listaComodoDTO(casa));
    }
}
