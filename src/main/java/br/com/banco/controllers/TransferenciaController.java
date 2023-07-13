package br.com.banco.controllers;

import br.com.banco.dtos.TransferenciaDto;
import br.com.banco.services.TransferenciaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping(path = "/conta")
    public ResponseEntity<Page<TransferenciaDto>> buscaPeloNumeroConta(@RequestParam(value = "id-conta") Long idConta,
                                                                       @RequestParam(value = "page") int page){
        return ResponseEntity.ok().body(transferenciaService.buscaPeloNumeroConta(idConta, page));
    }

    @GetMapping(path = "/periodo")
    public ResponseEntity<Page<TransferenciaDto>> buscaPorUmPeriodo(@RequestParam(value = "id-conta") Long idConta,
                                                                    @RequestParam(value = "page") int page,
                                                                    @RequestParam(value = "data-inicio") String dataInicio,
                                                                    @RequestParam(value = "data-fim") String dataFim){
        return ResponseEntity.ok().body(transferenciaService.buscaPorUmPeriodo(idConta, page, dataInicio, dataFim));
    }

    @GetMapping(path = "/nome-operador")
    public ResponseEntity<Page<TransferenciaDto>> buscaPeloNomeOperador(@RequestParam(value = "id-conta") Long idConta,
                                                                        @RequestParam(value = "page") int page,
                                                                        @RequestParam(value = "nome-operador") String nomeOperador){
        return ResponseEntity.ok().body(transferenciaService.buscaPorNomeOperador(idConta, page, nomeOperador));
    }

    @GetMapping(path = "/nome-operador-periodo")
    public ResponseEntity<Page<TransferenciaDto>> buscaPeloNomeOperadorEPeriodo(
                                                                @RequestParam(value = "id-conta") Long idConta,
                                                                @RequestParam(value = "page") int page,
                                                                @RequestParam(value = "nome-operador") String nomeOperador,
                                                                @RequestParam(value = "data-inicio") String dataInicio,
                                                                @RequestParam(value = "data-fim") String dataFim){
        return ResponseEntity.ok().body(transferenciaService.buscaPorNomeOperadorEPeriodo(idConta, page, nomeOperador,
                dataInicio, dataFim));
    }
}
