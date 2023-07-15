package br.com.banco.controller;

import br.com.banco.controllers.ContaController;
import br.com.banco.dtos.ContaDto;
import br.com.banco.services.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ContaControllerTest {

    private List<ContaDto> listaContaDto;
    private static final Long ID_CONTA = 1L;
    private static final String NOME_RESPONSAVEL = "Fulano";
    
    @InjectMocks
    private ContaController contaController;
    @Mock
    private ContaService contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarDados();
    }

    @Test
    @DisplayName("Endpoint: buscarTodasContas()")
    void buscarTodasContasDeveriaRetornarUmHttpStatusOk(){
        when(contaService.buscarTodasContas()).thenReturn(listaContaDto);

        ResponseEntity<List<ContaDto>> response = contaController.buscarTodasContas();
    
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    private void iniciarDados() {
        listaContaDto = List.of(new ContaDto(ID_CONTA, NOME_RESPONSAVEL, BigDecimal.ZERO, BigDecimal.ZERO));
    }
}
