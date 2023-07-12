package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.exceptions.NotFoundException;
import br.com.banco.repositories.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta buscaPeloId(Long id){
        return contaRepository.findById(id).orElseThrow(()-> new NotFoundException("Nenhuma conta encontrada"));
    }
}
