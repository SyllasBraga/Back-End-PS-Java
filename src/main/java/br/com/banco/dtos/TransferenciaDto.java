package br.com.banco.dtos;

import br.com.banco.enums.TiposTransferenciaEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransferenciaDto {
    private Timestamp dataTransferencia;
    private BigDecimal valor;
    private TiposTransferenciaEnum tipo;
    private String nomeOperadorTransacao;
    private ContaDto conta;
}
