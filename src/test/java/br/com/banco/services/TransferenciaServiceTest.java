package br.com.banco.services;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.enums.TiposTransferenciaEnum;
import br.com.banco.mapper.TransferenciaMapper;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.CalculaSaldoUtil;
import br.com.banco.utils.DataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransferenciaServiceTest {

    private static final Long ID_CONTA = 1L;
    private static final int PAGE = 0;
    private static final int NUMERO_ITENS_PAGINA = 4;
    private static final String NOME_OPERADOR = "Beltrano";
    private static final String DATA_INICIO = "2019-01-01 12:00:00";
    private static final String DATA_FIM = "2021-04-01 12:12:04";
    private Timestamp dataInicioFormatada;
    private Timestamp dataFimFormatada;
    private Conta conta;
    private Pageable pageable;
    private Transferencia transferencia;
    private TransferenciaDto transferenciaDto;
    private Page<Transferencia> pageTransferencia;
    private Page<TransferenciaDto> pageTransferenciaDto;

    @InjectMocks
    private TransferenciaService service;
    @Mock
    private TransferenciaRepository repository;
    @Mock
    private ContaService contaService;
    @Mock
    private TransferenciaMapper transferenciaMapper;
    @Mock
    private DataUtil dataUtil;
    @Mock
    private CalculaSaldoUtil calculaSaldoUtil;
    @Spy
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarDados();
    }

    @Test
    @DisplayName("Método: buscaPeloNumeroConta()")
    void buscaPeloNumeroContaDeveriaRetornarTransferenciaDtoPage() {
        when(contaService.iniciarConta(ID_CONTA)).thenReturn(conta);
        when(repository.findByConta(conta, pageable)).thenReturn(pageTransferencia);
        when(transferenciaMapper.paraPageTransferenciaDto(pageTransferencia)).thenReturn(pageTransferenciaDto);

        Page<TransferenciaDto> pageTransferenciaRecebida = service.buscaPeloNumeroConta(ID_CONTA, PAGE);

        assertEquals(TransferenciaDto.class, pageTransferenciaRecebida.getContent().get(0).getClass());
    }

    @Test
    @DisplayName("Método: buscaPorUmPeriodo()")
    void buscaPorUmPeriodoDeveriaRetornarTransferenciaDtoPage() {
        when(dataUtil.formataData(DATA_INICIO)).thenReturn(dataInicioFormatada);
        when(dataUtil.formataData(DATA_FIM)).thenReturn(dataFimFormatada);
        when(contaService.iniciarContaPeriodo(ID_CONTA, dataInicioFormatada, dataFimFormatada)).thenReturn(conta);
        when(repository.findByContaAndDataTransferenciaBetween(conta, dataInicioFormatada,
                dataFimFormatada, pageable)).thenReturn(pageTransferencia);
        when(transferenciaMapper.paraPageTransferenciaDto(pageTransferencia)).thenReturn(pageTransferenciaDto);

        Page<TransferenciaDto> pageTransferenciaRecebida = service.buscaPorUmPeriodo(ID_CONTA, PAGE, DATA_INICIO, DATA_FIM);

        assertEquals(TransferenciaDto.class, pageTransferenciaRecebida.getContent().get(0).getClass());
    }

    @Test
    @DisplayName("Método: buscaPorNomeOperador()")
    void buscaPorNomeOperadorDeveriaRetornarTransferenciaDtoPage() {
        when(contaService.iniciarConta(ID_CONTA)).thenReturn(conta);
        when(repository.findByContaAndNomeOperadorTransacao(conta, NOME_OPERADOR, pageable)).thenReturn(pageTransferencia);
        when(transferenciaMapper.paraPageTransferenciaDto(pageTransferencia)).thenReturn(pageTransferenciaDto);

        Page<TransferenciaDto> pageTransferenciaRecebida = service.buscaPorNomeOperador(ID_CONTA, PAGE, NOME_OPERADOR);

        assertEquals(TransferenciaDto.class, pageTransferenciaRecebida.getContent().get(0).getClass());
    }

    @Test
    @DisplayName("Método: buscaPorNomeOperadorEPeriodo")
    void buscaPorNomeOperadorEPeriodoDeveriaRetornarTransferenciaDtoPage() {
        when(dataUtil.formataData(DATA_INICIO)).thenReturn(dataInicioFormatada);
        when(dataUtil.formataData(DATA_FIM)).thenReturn(dataFimFormatada);
        when(contaService.iniciarContaPeriodo(ID_CONTA, dataInicioFormatada, dataFimFormatada)).thenReturn(conta);
        when(repository.findByContaAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                conta, NOME_OPERADOR, dataInicioFormatada, dataFimFormatada, pageable)).thenReturn(pageTransferencia);
        when(transferenciaMapper.paraPageTransferenciaDto(pageTransferencia)).thenReturn(pageTransferenciaDto);

        Page<TransferenciaDto> pageTransferenciaRecebida = service.buscaPorNomeOperadorEPeriodo(ID_CONTA, PAGE,
                NOME_OPERADOR, DATA_INICIO, DATA_FIM);

        assertEquals(TransferenciaDto.class, pageTransferenciaRecebida.getContent().get(0).getClass());
    }

    private void iniciarDados(){
        dataInicioFormatada = Timestamp.valueOf(DATA_INICIO);
        dataFimFormatada = Timestamp.valueOf(DATA_FIM);
        conta = new Conta(ID_CONTA, "Carlos", BigDecimal.ZERO, BigDecimal.ZERO);
        pageable = PageRequest.of(PAGE, NUMERO_ITENS_PAGINA);
        transferencia = new Transferencia(1L, Timestamp.valueOf("2020-09-01 12:00:00"), BigDecimal.ZERO,
                TiposTransferenciaEnum.TRANSFERENCIA, "Beltrano", conta);
        transferenciaDto = new TransferenciaDto(Timestamp.valueOf("2020-09-01 12:00:00"), BigDecimal.ZERO,
                TiposTransferenciaEnum.TRANSFERENCIA, "Beltrano", null);
        pageTransferencia = new PageImpl<>(List.of(transferencia), pageable, 1);
        pageTransferenciaDto = new PageImpl<>(List.of(transferenciaDto), pageable, 1);
    }
}
