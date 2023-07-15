package br.com.banco.services;

import br.com.banco.dtos.ContaDto;
import br.com.banco.entities.Conta;
import br.com.banco.exceptions.NotFoundException;
import br.com.banco.mapper.ContaMapper;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.utils.CalculaSaldoUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CalculaSaldoUtil calculaSaldoUtil;
    private final ContaMapper contaMapper;

    public ContaService(ContaRepository contaRepository, CalculaSaldoUtil calculaSaldoUtil, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.calculaSaldoUtil = calculaSaldoUtil;
        this.contaMapper = contaMapper;
    }

    public Conta buscaPeloId(Long id){
        return contaRepository.findById(id).orElseThrow(()-> new NotFoundException("Conta não encontrada."));
    }

    public List<ContaDto> buscarTodasContas(){
        List<ContaDto> listaDto = new ArrayList<>();
        List<Conta> listaModel = contaRepository.findAll();
        listaModel.forEach(lista -> listaDto.add(contaMapper.toContaDto(lista)));
        return listaDto;
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
