package br.com.banco.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {
    private Long idConta;
    private String nomeResponsavel;
    private BigDecimal saldoTotal;
    private BigDecimal saldoPeriodo;
}
