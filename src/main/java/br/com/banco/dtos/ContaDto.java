package br.com.banco.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaDto {
    private Long idConta;
    private String nomeResponsavel;
    private BigDecimal saldoTotal;
    private BigDecimal saldoPeriodo;
}
