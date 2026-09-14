package com.nexustech.controller;

import com.nexustech.dto.LaudoBuscaDTO;
import com.nexustech.entity.LaudoTecnicoEntity;
import com.nexustech.service.ManutencaoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manutencao")
public class ManutencaoController {

    private final ManutencaoService service;

    public ManutencaoController(
            ManutencaoService service
    ) {
        this.service = service;
    }

    @PostMapping(
            value = "/processar-e-salvar",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LaudoTecnicoEntity>
    processarESalvar(@RequestBody String relato) {

        return ResponseEntity.ok(
                service.processarEGuardar(relato)
        );
    }

    @GetMapping("/historico")
    public ResponseEntity<List<LaudoTecnicoEntity>>
    listarHistorico() {

        return ResponseEntity.ok(
                service.listarHistorico()
        );
    }

    @GetMapping("/historico/criticos")
    public ResponseEntity<List<LaudoTecnicoEntity>>
    listarCriticos() {

        return ResponseEntity.ok(
                service.listarCriticos()
        );
    }

    @PostMapping(
            value = "/processar-rag",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LaudoTecnicoEntity>
    processarComRAG(@RequestBody String relato) {

        return ResponseEntity.ok(
                service.processarComRAG(relato)
        );
    }

    @GetMapping("/busca-semantica")
    public ResponseEntity<List<LaudoBuscaDTO>>
    buscarSemantica(@RequestParam String termo) {

        return ResponseEntity.ok(
                service.buscarPorSimilaridade(termo)
        );
    }
}