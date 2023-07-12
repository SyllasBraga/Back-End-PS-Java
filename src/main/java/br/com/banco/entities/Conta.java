package br.com.banco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConta;
    private String nomeResponsavel;
    @Transient
    private BigDecimal saldoTotal;
    @Transient
    private BigDecimal saldoPeriodo;
}
