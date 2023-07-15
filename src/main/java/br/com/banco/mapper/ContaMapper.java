package br.com.banco.mapper;

import br.com.banco.dtos.ContaDto;
import br.com.banco.entities.Conta;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {

    private final ModelMapper modelMapper;

    public ContaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ContaDto toContaDto(Conta lista) {
        return modelMapper.map(lista, ContaDto.class);
    }
}
