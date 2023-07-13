package br.com.banco.dtos;

import br.com.banco.enums.TiposTransferenciaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDto {
    private Timestamp dataTransferencia;
    private BigDecimal valor;
    private TiposTransferenciaEnum tipo;
    private String nomeOperadorTransacao;
    private ContaDto conta;
}
