package br.com.meli.spring3.serviceTest;

import br.com.meli.spring3.demo.dto.ComodoSaidaDTO;
import br.com.meli.spring3.demo.entity.Casa;
import br.com.meli.spring3.demo.entity.Comodo;
import br.com.meli.spring3.demo.repository.CasaRepository;
import br.com.meli.spring3.demo.service.CasaService;
import br.com.meli.spring3.utils.MockListaCasas;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe que contem a logica da entidade casa para efeito de teste
 */
public class CasaServiceTest {

    /**
     * Metodo para testar se a casa está sendo salva
     */
    @Test
    public void deveVerificarACriacaoDeUmaNovaCasa(){
        MockListaCasas listaCasas = new MockListaCasas();
        Casa casa = listaCasas.getCasas().get(0);


        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.salva(casa)).thenReturn(casa);

        CasaService casaService = new CasaService(mockCasaRepository);
        Casa casaCriada = casaService.salvar(casa);
        assertTrue(casaCriada.equals(casa));
    }

    /**
     * Metodo para testa se uma casa está sendo localizada
     */
    @Test
    public void deveRetornarUmaCasa(){
        MockListaCasas listaCasas = new MockListaCasas();
        Casa casa = listaCasas.getCasas().get(0);

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.buscaCasa("casa1")).thenReturn(casa);

        CasaService casaService = new CasaService(mockCasaRepository);
        Casa casaRetornada = casaService.findOne("casa1");

        assertTrue(casa.equals(casaRetornada));
    }

    /**
     * Metodo para testar o recebimento de uma lista comodoDTO
     */
    @Test
    public void deveRetornarListaDeComodosDTO(){
        MockListaCasas listaCasas = new MockListaCasas();
        Casa casa = listaCasas.getCasas().get(0);

        List<ComodoSaidaDTO> comodosModelo = ComodoSaidaDTO.converte(casa.getComodos());

        CasaService casaService = new CasaService();
        List<ComodoSaidaDTO> listaComodoSaidaDTO = new ArrayList<>();
        listaComodoSaidaDTO =  casaService.listaComodoDTO(casa);

        assertTrue(listaComodoSaidaDTO.equals(comodosModelo));

    }

    /**
     * Metodo para testar se o valor da casa está com o calculo correto
     */
    @Test
    public void deveRetornarOValorDaCasa(){
        MockListaCasas listaCasas = new MockListaCasas();

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.buscaCasa("casa1")).thenReturn(listaCasas.getCasas().get(0));

        CasaService casaService = new CasaService(mockCasaRepository);

        //4250
        BigDecimal valorCasa = casaService.valorCasa("casa1");
        assertTrue(valorCasa.compareTo(new BigDecimal(4250)) == 0);
    }

    /**
     * Metodo para testar se o calculo da área está correto
     */
    @Test
    public void deveRetornarMetrosQuadradosTotalDaCasa() {
        MockListaCasas listaCasas = new MockListaCasas();

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.buscaCasa("casa1")).thenReturn(listaCasas.getCasas().get(0));

        CasaService casaService = new CasaService(mockCasaRepository);
        double totalAreaCasa = casaService.calculaArea("casa1");

        // cálculo total da área = 3 * 4 + 2 * 2.5 = 17
        assert(totalAreaCasa == 17);
    }

    /**
     * Metodo para testar o retorno do maior comando de uma casa
     * @throws IOException
     */
    @Test
    public void deveVerificarMaiorComodo() throws IOException {
        MockListaCasas listaCasas = new MockListaCasas();

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.buscaCasa("casa2")).thenReturn(listaCasas.getCasas().get(1));

        CasaService casaService = new CasaService(mockCasaRepository);

        Comodo maiorComodoModelo = new Comodo("sala", 5,4); //20
        Comodo maiorComodo = casaService.maiorComodo("casa2");

        // compara maiorComodo com maiorComodoModelo
        assertTrue(maiorComodo.equals(maiorComodoModelo));
    }

}
