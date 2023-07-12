package br.com.banco.utils;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class CalculaSaldoUtil {

    private final TransferenciaRepository transferenciaRepository;

    public CalculaSaldoUtil(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public BigDecimal calculaSaldoConta(Conta conta){
        Page<Transferencia> transferencias = transferenciaRepository.findByConta(conta, Pageable.unpaged());

        return calculaSaldo(transferencias);
    }

    public BigDecimal calculaSaldoPeriodo(Conta conta, Timestamp dataInicio, Timestamp dataFim){
        Page<Transferencia> transferencias = transferenciaRepository.
                findByContaAndDataTransferenciaBetween(conta, dataInicio, dataFim, Pageable.unpaged());

        return calculaSaldo(transferencias);
    }

    private BigDecimal calculaSaldo(Page<Transferencia> transferencias){
        BigDecimal saldo = BigDecimal.ZERO;
        for (Transferencia transferencia : transferencias) {
            saldo = saldo.add(transferencia.getValor());
        }
        return saldo;
    }
}
