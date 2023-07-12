package br.com.banco.services;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.mapper.TransferenciaMapper;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.utils.DataUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaService contaService;
    private final TransferenciaMapper transferenciaMapper;
    private final DataUtil dataUtil;
    private static final int numeroItensPagina = 4;

    public TranferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService,
                               TransferenciaMapper transferenciaMapper, DataUtil dataUtil) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaService = contaService;
        this.transferenciaMapper = transferenciaMapper;
        this.dataUtil = dataUtil;
    }

    public Page<TransferenciaDto> buscaPeloNumeroConta(Long idConta, int page){
        Conta conta = contaService.buscaPeloId(idConta);
        Pageable pageable = PageRequest.of(page, numeroItensPagina);

        return transferenciaMapper.paraPageTransferenciaDto(transferenciaRepository.findByConta(conta, pageable));
    }

    public Page<TransferenciaDto> buscaPorUmPeriodo(Long idConta, int page, String dataInicio, String dataFim){
        Conta conta = contaService.buscaPeloId(idConta);
        Pageable pageable = PageRequest.of(page, numeroItensPagina);

        return transferenciaMapper.paraPageTransferenciaDto(
                transferenciaRepository.findByContaAndDataTransferenciaBetween(conta, dataUtil.formataData(dataInicio),
                dataUtil.formataData(dataFim), pageable));
    }
}
