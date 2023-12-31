package br.com.banco.services;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.mapper.TransferenciaMapper;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.DataUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaService contaService;
    private final TransferenciaMapper transferenciaMapper;
    private final DataUtil dataUtil;
    private static final int NUMERO_ITENS_PAGINA = 4;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService,
                                TransferenciaMapper transferenciaMapper, DataUtil dataUtil) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaService = contaService;
        this.transferenciaMapper = transferenciaMapper;
        this.dataUtil = dataUtil;
    }

    public Page<TransferenciaDto> buscaPeloNumeroConta(Long idConta, int page){
        Conta conta = contaService.iniciarConta(idConta);
        Pageable pageable = PageRequest.of(page, NUMERO_ITENS_PAGINA);

        return transferenciaMapper.paraPageTransferenciaDto(transferenciaRepository.findByConta(conta, pageable));
    }

    public Page<TransferenciaDto> buscaPorUmPeriodo(Long idConta, int page, String dataInicio, String dataFim){
        Timestamp dataInicioFormatada = dataUtil.formataData(dataInicio);
        Timestamp dataFimFormatada = dataUtil.formataData(dataFim);
        Conta conta = contaService.iniciarContaPeriodo(idConta, dataInicioFormatada, dataFimFormatada);

        Pageable pageable = PageRequest.of(page, NUMERO_ITENS_PAGINA);

        return transferenciaMapper.paraPageTransferenciaDto(
                transferenciaRepository.findByContaAndDataTransferenciaBetween(conta, dataInicioFormatada,
                        dataFimFormatada, pageable));
    }

    public Page<TransferenciaDto> buscaPorNomeOperador(Long idConta, int page, String nomeOperador){
        Conta conta = contaService.iniciarConta(idConta);
        Pageable pageable = PageRequest.of(page, NUMERO_ITENS_PAGINA);

        return transferenciaMapper.paraPageTransferenciaDto(
                transferenciaRepository.findByContaAndNomeOperadorTransacao(conta, nomeOperador, pageable));
    }

    public Page<TransferenciaDto> buscaPorNomeOperadorEPeriodo(Long idConta, int page, String nomeOperador,
                                                               String dataInicio, String dataFim){
        Timestamp dataInicioFormatada = dataUtil.formataData(dataInicio);
        Timestamp dataFimFormatada = dataUtil.formataData(dataFim);
        Conta conta = contaService.iniciarContaPeriodo(idConta, dataInicioFormatada, dataFimFormatada);
        Pageable pageable = PageRequest.of(page, NUMERO_ITENS_PAGINA);

        return transferenciaMapper.paraPageTransferenciaDto(
                transferenciaRepository.findByContaAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                        conta, nomeOperador, dataInicioFormatada, dataFimFormatada, pageable));
    }
}