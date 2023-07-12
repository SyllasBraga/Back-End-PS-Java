package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TranferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaService contaService;
    private static final int numeroItensPagina = 4;

    public TranferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaService = contaService;
    }

    public Page<Transferencia> buscaPeloNumeroConta(Long idConta, int page){
        Conta conta = contaService.buscaPeloId(idConta);
        Pageable pageable = PageRequest.of(page, numeroItensPagina);

        return transferenciaRepository.findByConta(conta, pageable);
    }

}
