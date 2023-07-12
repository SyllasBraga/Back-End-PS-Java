package br.com.banco.services;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaService contaService;
    private final ModelMapper modelMapper;
    private static final int numeroItensPagina = 4;

    public TranferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService, ModelMapper modelMapper) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaService = contaService;
        this.modelMapper = modelMapper;
    }

    public Page<TransferenciaDto> buscaPeloNumeroConta(Long idConta, int page){
        Conta conta = contaService.buscaPeloId(idConta);
        Pageable pageable = PageRequest.of(page, numeroItensPagina);

        return paraPageTransferenciaDto(transferenciaRepository.findByConta(conta, pageable));
    }

    private TransferenciaDto paraTransferenciaDto(Transferencia transferencia){
        return modelMapper.map(transferencia, TransferenciaDto.class);
    }

    public Page<TransferenciaDto> paraPageTransferenciaDto(Page<Transferencia> pageTransferencia) {
        List<TransferenciaDto> transferenciaDtos = pageTransferencia.getContent()
                .stream()
                .map(this::paraTransferenciaDto)
                .collect(Collectors.toList());

        return new PageImpl<>(transferenciaDtos, pageTransferencia.getPageable(), pageTransferencia.getTotalElements());
    }
}
