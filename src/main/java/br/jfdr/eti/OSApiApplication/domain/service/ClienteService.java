/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.jfdr.eti.OSApiApplication.domain.service;

import br.jfdr.eti.OSApiApplication.domain.exception.DomainException;
import br.jfdr.eti.OSApiApplication.domain.model.Cliente;
import br.jfdr.eti.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sesi3dia
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new DomainException("Já existe um cliente com esse email!");
            
    }
        return clienteRepository.save(cliente);
    }
    
    public void excluir(Long clienteID) {
        clienteRepository.deleteById(clienteID);
    }
    
}
