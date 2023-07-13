package br.com.banco.mapper;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Transferencia;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransferenciaMapper {

    private final ModelMapper modelMapper;

    public TransferenciaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private TransferenciaDto paraTransferenciaDto(Transferencia transferencia){
        return modelMapper.map(transferencia, TransferenciaDto.class);
    }

    public Page<TransferenciaDto> paraPageTransferenciaDto(Page<Transferencia> pageTransferencia) {
        List<TransferenciaDto> transferenciaDtos = pageTransferencia.getContent()
                .stream()
                .map(this::paraTransferenciaDto)
                .collect(Collectors.toList());

        return new PageImpl<>(transferenciaDtos, pageTransferencia.getPageable(), pageTransferencia.getTotalElements());
    }
}
