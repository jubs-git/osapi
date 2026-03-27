/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.jfdr.eti.OSApiApplication.api.controller;

import br.jfdr.eti.OSApiApplication.domain.model.Cliente;
import br.jfdr.eti.OSApiApplication.domain.repository.ClienteRepository;
import br.jfdr.eti.OSApiApplication.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesi3dia
 */

@RestController
public class ClienteController {
    
   @Autowired
   private ClienteRepository clienteRepository;
   
   @Autowired
   private ClienteService clienteService;

   
   @GetMapping("/clientes")
   
@Operation(summary = "Pega todos os clientes", description = "Retorna lista de clientes")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "sucesso"),
 @ApiResponse(responseCode = "404", description = "Not found")
 })
   
   public List<Cliente> listas() {
       return clienteRepository.findAll();
//       return clienteRepository.findByNome("julia");
       //return clienteRepository.findByNomeContaining("Silva")
   }
   
   @GetMapping("/clientes/{clienteID}")
   @Parameter(name = "id", description = "client id", example = "1")
   @Operation(summary = "Procura cliente pelo ID", description = "Retorna o cliente pelo ID")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
 @ApiResponse(responseCode = "404", description = "Not found ")
 })
   
   public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
       
       Optional<Cliente> cliente = clienteRepository.findById(clienteID);
       
      if (cliente.isPresent()) {
          return ResponseEntity.ok(cliente.get());
      } else {
         return ResponseEntity.notFound().build();
      }
   }
   
   @PostMapping("/clientes")
   
   @Parameter(name = "cliente", description = "add cliente")
   @Operation(summary = "Recebe as informações", description = "Adiciona o cliente")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Successfully"),
 @ApiResponse(responseCode = "400", description = "Bad Request")
 })
   
   @ResponseStatus(HttpStatus.CREATED)
   public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
       
       return clienteService.salvar(cliente);
   }
   @PutMapping("/clientes/{clienteID}")
   
   @Parameter(name = "cliente id", description = "atualiza cliente")
   @Operation(summary = "Procura cliente pelo id", description = "Faz atualizações")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Successfully"),
 @ApiResponse(responseCode = "400", description = "Bad Request")
 })
   
   public ResponseEntity<Cliente> atualizar( @Valid @PathVariable Long clienteID,
           @RequestBody Cliente cliente) {
       if (!clienteRepository.existsById(clienteID)) {
           return ResponseEntity.notFound().build();
       }
       
       cliente.setId(clienteID);
       cliente = clienteService.salvar(cliente);
       return ResponseEntity.ok(cliente);
       
   }
   @DeleteMapping("/cliente/{clienteID}")
   @Parameter(name = "id", description = "client id", example = "1")
   @Operation(summary = "Procura cliente pelo ID", description = "Deleta o cliente pelo ID")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
 @ApiResponse(responseCode = "400", description = "Bad Request")
 })
   
   public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
       
       if (!clienteRepository.existsById(clienteID)) {
           return ResponseEntity.notFound().build();
       }
       
       clienteService.excluir(clienteID);
       return ResponseEntity.noContent().build();
   }
   
}
