package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends PagingAndSortingRepository<Transferencia, Long> {
    Page<Transferencia> findByConta (Conta conta, Pageable pageable);
}