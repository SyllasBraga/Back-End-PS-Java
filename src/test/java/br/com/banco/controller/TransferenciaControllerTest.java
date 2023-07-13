package br.com.banco.controller;

import br.com.banco.controllers.TransferenciaController;
import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.enums.TiposTransferenciaEnum;
import br.com.banco.services.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class TransferenciaControllerTest {

    private static final Long ID_CONTA = 1L;
    private static final int PAGE = 0;
    private static final int NUMERO_ITENS_PAGINA = 4;
    private static final String DATA_INICIO = "2019-01-01 12:00:00";
    private static final String DATA_FIM = "2021-04-01 12:12:04";
    private static final String NOME_OPERADOR = "Beltrano";
    private Pageable pageable;
    private TransferenciaDto transferenciaDto;
    private Page<TransferenciaDto> pageTransferenciaDto;

    @InjectMocks
    private TransferenciaController transferenciaController;
    @Mock
    private TransferenciaService transferenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarDados();
    }

    @Test
    @DisplayName("Endpoint: buscaPeloNumeroConta()")
    void buscaPeloNumeroContaDeveriaRetornarUmHttpStatusOk(){
        when(transferenciaService.buscaPeloNumeroConta(anyLong(), anyInt())).thenReturn(pageTransferenciaDto);

        ResponseEntity<Page<TransferenciaDto>> response = transferenciaController.buscaPeloNumeroConta(ID_CONTA, PAGE);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TransferenciaDto.class, response.getBody().getContent().get(0).getClass());
    }

    @Test
    @DisplayName("Endpoint: buscaPorUmPeriodo()")
    void buscaPorUmPeriodoDeveriaRetornarUmHttpStatusOk(){
        when(transferenciaService.buscaPorUmPeriodo(anyLong(), anyInt(), any(), any())).thenReturn(pageTransferenciaDto);

        ResponseEntity<Page<TransferenciaDto>> response = transferenciaController.buscaPorUmPeriodo(ID_CONTA, PAGE,
                DATA_INICIO, DATA_FIM);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TransferenciaDto.class, response.getBody().getContent().get(0).getClass());
    }

    @Test
    @DisplayName("Endpoint: buscaPeloNomeOperador()")
    void buscaPeloNomeOperadorDeveriaRetornarUmHttpStatusOk(){
        when(transferenciaService.buscaPorNomeOperador(anyLong(), anyInt(), anyString())).thenReturn(pageTransferenciaDto);

        ResponseEntity<Page<TransferenciaDto>> response = transferenciaController.buscaPeloNomeOperador(ID_CONTA, PAGE,
                NOME_OPERADOR);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TransferenciaDto.class, response.getBody().getContent().get(0).getClass());
    }

    @Test
    @DisplayName("Endpoint: buscaPeloNomeOperadorEPeriodo()")
    void buscaPeloNomeOperadorEPeriodoDeveriaRetornarUmHttpStatusOk(){
        when(transferenciaService.buscaPorNomeOperadorEPeriodo(anyLong(), anyInt(), anyString(), any(), any())).
                thenReturn(pageTransferenciaDto);

        ResponseEntity<Page<TransferenciaDto>> response = transferenciaController.buscaPeloNomeOperadorEPeriodo(
                ID_CONTA, PAGE, NOME_OPERADOR, DATA_INICIO, DATA_FIM);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(TransferenciaDto.class, response.getBody().getContent().get(0).getClass());
    }

    private void iniciarDados(){
        pageable = PageRequest.of(PAGE, NUMERO_ITENS_PAGINA);
        transferenciaDto = new TransferenciaDto(Timestamp.valueOf("2020-09-01 12:00:00"), BigDecimal.ZERO,
                TiposTransferenciaEnum.TRANSFERENCIA, "Beltrano", null);
        pageTransferenciaDto = new PageImpl<>(List.of(transferenciaDto), pageable, 1);
    }
}
