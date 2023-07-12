package br.com.banco.controllers;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.entities.Transferencia;
import br.com.banco.services.TranferenciaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TranferenciaService tranferenciaService;

    public TransferenciaController(TranferenciaService tranferenciaService) {
        this.tranferenciaService = tranferenciaService;
    }

    @GetMapping(path = "/conta")
    public ResponseEntity<Page<TransferenciaDto>> buscaPeloNumeroConta(@RequestParam(value = "id-conta") Long idConta,
                                                                       @RequestParam(value = "page") int page){
        return ResponseEntity.ok().body(tranferenciaService.buscaPeloNumeroConta(idConta, page));
    }
}
