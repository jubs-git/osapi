/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.jfdr.eti.OSApiApplication.domain.dto;

import br.jfdr.eti.OSApiApplication.domain.model.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author sesi3dia
 */
public record AtualizaStatusDTO (
        
        @NotNull(message = "Status é obrigatório")
        StatusOrdemServico status

        ) {}
