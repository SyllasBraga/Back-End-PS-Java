package br.com.banco.entities;

import br.com.banco.enums.TiposTransferenciaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp dataTransferencia;
    private BigDecimal valor;
    @Enumerated(value = EnumType.STRING)
    private TiposTransferenciaEnum tipo;
    private String nomeOperadorTransacao;
    @OneToMany
    private Conta conta_id;
}
