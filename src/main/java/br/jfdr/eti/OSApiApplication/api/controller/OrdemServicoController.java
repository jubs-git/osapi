/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.jfdr.eti.OSApiApplication.api.controller;

import br.jfdr.eti.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.jfdr.eti.OSApiApplication.domain.model.OrdemServico;
import br.jfdr.eti.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesi3dia
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoService.listar();
    }
    
    @GetMapping("/{id}")
    public OrdemServico buscar(@PathVariable Long id) {
        return ordemServicoService.buscar(id);
    }
    
    @GetMapping("/cliente/{clienteId}")
    public List<OrdemServico>listarPorCliente(@PathVariable Long clienteId) {
        return ordemServicoService.listarPorCliente(clienteId);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
        ordemServicoService.remover(id);
    }
    
    @PutMapping("/{id}")
    public OrdemServico atualizar(@PathVariable Long id, @RequestBody OrdemServico ordemServico){
        return ordemServicoService.atualizar(id, ordemServico);
    }
    
    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
    @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO) {
        
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
        ordemServicoID, statusDTO.status());
        
        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
