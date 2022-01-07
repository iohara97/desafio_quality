package br.com.meli.spring3.serviceTest;

import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.CasaRepository;
import br.com.meli.spring3.demo.service.CasaService;
import br.com.meli.spring3.utils.MockListaCasas;
import exception.BusinessException;
import exception.ComodosVazioException;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe que contem a logica da entidade casa para efeito de teste
 */
public class CasaServiceTest {

    private MockListaCasas listaCasas;
    private Casa casa, casaSemComodo;
    private CasaRepository mockCasaRepository;
    private CasaService casaService;

    @BeforeEach
    void init() {
        listaCasas = new MockListaCasas();
        casa = listaCasas.getCasas().get(0);;
        casaSemComodo = listaCasas.getCasas().get(2);;


        mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.salva(casa)).thenReturn(casa);
        Mockito.when(mockCasaRepository.buscaCasa("casa1")).thenReturn(casa);
        Mockito.when(mockCasaRepository.buscaCasa("casa2")).thenReturn(listaCasas.getCasas().get(1));
//        Mockito.when(mockCasaRepository.salva(casaSemComodo)).thenReturn(casaSemComodo);

        casaService = new CasaService(mockCasaRepository);
    }

    /**
     * Metodo para testar se a casa está sendo salva
     */
    @Test
    public void deveVerificarACriacaoDeUmaNovaCasa(){
        Casa casaCriada = casaService.salvar(casa);
        assertTrue(casaCriada.equals(casa));
    }

    /**
     * Metodo para testar se uma casa sem cômodos não é salva
     */
    @Test
    public void deveVerificarACriacaoDeUmaNovaCasaSemComodos(){

        ComodosVazioException excecaoEsperada = assertThrows(
                ComodosVazioException.class,
                () -> casaService.salvar(casaSemComodo) //act
        );

//        assertTrue(excecaoEsperada.getMessage().contains("casa deve ter pelos menos um comodo"));
//        assert(excecaoEsperada.getMessage().contains("A casa deve ter pelos menos um comodoo") == true);
        assertEquals(excecaoEsperada.getMessage(), "A casa deve ter pelos menos um comodo");
    }

    /**
     * Metodo para testa se uma casa está sendo localizada
     */
    @Test
    public void deveRetornarUmaCasa(){
        Casa casaRetornada = casaService.findOne("casa1");
        assertTrue(casa.equals(casaRetornada));
    }

    /**
     * Metodo para testar o recebimento de uma lista comodoDTO
     */
    @Test
    public void deveRetornarListaDeComodosDTO(){
        List<ComodoSaidaDTO> comodosModelo = ComodoSaidaDTO.converte(casa.getComodos());
        List<ComodoSaidaDTO> listaComodoSaidaDTO = new ArrayList<>();
        listaComodoSaidaDTO =  casaService.listaComodoDTO(casa);
        assertTrue(listaComodoSaidaDTO.equals(comodosModelo));
    }

    /**
     * Metodo para testar se o valor da casa está com o calculo correto
     */
    @Test
    public void deveRetornarOValorDaCasa(){
        BigDecimal valorCasa = casaService.valorCasa("casa1");
        assertTrue(valorCasa.compareTo(new BigDecimal(4250)) == 0);
    }

    /**
     * Metodo para testar se o calculo da área está correto
     */
    @Test
    public void deveRetornarMetrosQuadradosTotalDaCasa() {
        double totalAreaCasa = casaService.calculaArea("casa1");
        assert(totalAreaCasa == 17);
    }

    /**
     * Metodo para testar o retorno do maior comando de uma casa
     * @throws IOException
     */
    @Test
    public void deveVerificarMaiorComodo() throws IOException {
        Comodo maiorComodoModelo = new Comodo("sala", 5,4); //20
        Comodo maiorComodo = casaService.maiorComodo("casa2");
        assertTrue(maiorComodo.equals(maiorComodoModelo));
    }

}
