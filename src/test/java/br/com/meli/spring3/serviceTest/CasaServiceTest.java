package br.com.meli.spring3.serviceTest;

import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.CasaRepository;
import br.com.meli.spring3.demo.service.CasaService;
import br.com.meli.spring3.utils.MockListaCasas;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasaServiceTest {

    private MockListaCasas listaCasas;
    private Casa casa;
    private CasaRepository mockCasaRepository;
    private CasaService casaService;

    @BeforeEach
    void init() {
        listaCasas = new MockListaCasas();
        casa = listaCasas.getCasas().get(0);;

        mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.salva(casa)).thenReturn(casa);
        Mockito.when(mockCasaRepository.buscaCasa("casa1")).thenReturn(casa);
        Mockito.when(mockCasaRepository.buscaCasa("casa2")).thenReturn(listaCasas.getCasas().get(1));

        casaService = new CasaService(mockCasaRepository);
    }

    @Test
    // CasaService.salvar()
    public void deveVerificarACriacaoDeUmaNovaCasa(){
        Casa casaCriada = casaService.salvar(casa);
        assertTrue(casaCriada.equals(casa));
    }

    @Test
    //CasaService.findOne()
    public void deveRetornarUmaCasa(){
        Casa casaRetornada = casaService.findOne("casa1");
        assertTrue(casa.equals(casaRetornada));
    }

    @Test
    // CasaService.listaComodoDTO()
    public void deveRetornarListaDeComodosDTO(){
        List<ComodoSaidaDTO> comodosModelo = ComodoSaidaDTO.converte(casa.getComodos());
        List<ComodoSaidaDTO> listaComodoSaidaDTO = new ArrayList<>();
        listaComodoSaidaDTO =  casaService.listaComodoDTO(casa);
        assertTrue(listaComodoSaidaDTO.equals(comodosModelo));
    }

    @Test
    // CasaService.valorCasa()
    public void deveRetornarOValorDaCasa(){
        BigDecimal valorCasa = casaService.valorCasa("casa1");
        assertTrue(valorCasa.compareTo(new BigDecimal(4250)) == 0);
    }

    @Test
    // CasaService.calculaArea()
    public void deveRetornarMetrosQuadradosTotalDaCasa() {
        double totalAreaCasa = casaService.calculaArea("casa1");
        assert(totalAreaCasa == 17);
    }

    @Test
    // CasaService.maiorComodo()
    public void deveVerificarMaiorComodo() throws IOException {
        Comodo maiorComodoModelo = new Comodo("sala", 5,4); //20
        Comodo maiorComodo = casaService.maiorComodo("casa2");
        assertTrue(maiorComodo.equals(maiorComodoModelo));
    }

}
