package br.com.banco.controllers;

import br.com.banco.dtos.ContaDto;
import br.com.banco.services.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<List<ContaDto>> buscarTodasContas(){
        return ResponseEntity.ok().body(contaService.buscarTodasContas());
    }
}
