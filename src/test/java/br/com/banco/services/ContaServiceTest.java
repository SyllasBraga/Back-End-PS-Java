package br.com.banco.services;

import br.com.banco.dtos.ContaDto;
import br.com.banco.entities.Conta;
import br.com.banco.exceptions.NotFoundException;
import br.com.banco.mapper.ContaMapper;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.utils.CalculaSaldoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ContaServiceTest {

    private static final Long ID_CONTA = 1L;
    private static final String NOME_RESPONSAVEL = "Fulano";
    private static final BigDecimal SALDO = BigDecimal.ZERO;
    private Conta conta;
    private ContaDto contaDto;
    private List<Conta> listaConta;
    private List<ContaDto> listaContaDto;
    private Optional<Conta> optConta;
    private Timestamp dataInicioFormatada;
    private Timestamp dataFimFormatada;

    @InjectMocks
    private ContaService service;
    @Mock
    private ContaRepository repository;
    @Mock
    private CalculaSaldoUtil calculaSaldoUtil;
    @Mock
    private ContaMapper contaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarDados();
    }

    @Test
    @DisplayName("Método: buscaPeloId()")
    void buscaPeloIdDeveriaRetornarConta(){
        when(repository.findById(anyLong())).thenReturn(optConta);

        Conta contaRecebida = service.buscaPeloId(ID_CONTA);

        assertEquals(Conta.class, contaRecebida.getClass());
    }

    @Test
    @DisplayName("Método: buscarTodasContas()")
    void buscaTodasContaRetornarListaContaDto(){
        when(repository.findAll()).thenReturn(listaConta);
        when(contaMapper.toContaDto(any())).thenReturn(contaDto);

        List<ContaDto> contaDtosRecebidas = service.buscarTodasContas();

        assertEquals(ContaDto.class, contaDtosRecebidas.get(0).getClass());
    }

    @Test
    @DisplayName("Método: buscaPeloId(): Exception")
    void buscaPeloIdDeveriaRetornarUmaNotFoundException(){
        when(repository.findById(anyLong())).thenThrow(new NotFoundException("Entidade não encontrada"));

        try {
            service.buscaPeloId(ID_CONTA);
        }catch (NotFoundException ex){
            assertEquals(NotFoundException.class, ex.getClass());
        }
    }

    @Test
    @DisplayName("Método: iniciarConta()")
    void iniciarContaDeveriaRetornarConta(){
        when(repository.findById(anyLong())).thenReturn(optConta);
        when(calculaSaldoUtil.calculaSaldoConta(any())).thenReturn(BigDecimal.ZERO);

        Conta contaRecebida = service.iniciarConta(ID_CONTA);

        assertEquals(Conta.class, contaRecebida.getClass());
    }

    @Test
    @DisplayName("Método: iniciarContaPeriodo()")
    void iniciarContaPeriodoDeveriaRetornarConta(){
        when(repository.findById(anyLong())).thenReturn(optConta);
        when(calculaSaldoUtil.calculaSaldoConta(any())).thenReturn(BigDecimal.ZERO);
        when(calculaSaldoUtil.calculaSaldoPeriodo(any(), any(), any())).thenReturn(BigDecimal.ZERO);

        Conta contaRecebida = service.iniciarContaPeriodo(ID_CONTA, dataInicioFormatada, dataFimFormatada);

        assertEquals(Conta.class, contaRecebida.getClass());
    }

    private void iniciarDados() {
        conta = new Conta(ID_CONTA, NOME_RESPONSAVEL, BigDecimal.ZERO, BigDecimal.ZERO);
        contaDto = new ContaDto(ID_CONTA, NOME_RESPONSAVEL, BigDecimal.ZERO, BigDecimal.ZERO);
        optConta = Optional.of(conta);
        dataInicioFormatada = Timestamp.valueOf("2019-01-01 12:00:00");
        dataFimFormatada = Timestamp.valueOf("2021-04-01 12:12:04");
        listaConta = List.of(conta);
        listaContaDto = List.of(contaDto);
    }
}
