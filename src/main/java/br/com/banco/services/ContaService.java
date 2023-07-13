package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.exceptions.NotFoundException;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.utils.CalculaSaldoUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CalculaSaldoUtil calculaSaldoUtil;

    public ContaService(ContaRepository contaRepository, CalculaSaldoUtil calculaSaldoUtil) {
        this.contaRepository = contaRepository;
        this.calculaSaldoUtil = calculaSaldoUtil;
    }

    public Conta buscaPeloId(Long id){
        return contaRepository.findById(id).orElseThrow(()-> new NotFoundException("Conta não encontrada."));
    }

    public Conta iniciarConta(Long idConta){
        Conta conta = buscaPeloId(idConta);
        conta.setSaldoTotal(calculaSaldoUtil.calculaSaldoConta(conta));
        return conta;
    }

    public Conta iniciarContaPeriodo(Long idConta, Timestamp dataInicio, Timestamp dataFim){
        Conta conta = buscaPeloId(idConta);
        conta.setSaldoTotal(calculaSaldoUtil.calculaSaldoConta(conta));
        conta.setSaldoPeriodo(calculaSaldoUtil.calculaSaldoPeriodo(conta, dataInicio, dataFim));
        return conta;
    }
}
