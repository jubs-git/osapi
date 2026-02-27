/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.jfdr.eti.OSApiApplication.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author sesi3dia
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        PoblemaException problema = new PoblemaException();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos inválidos! Tente de novo.");
        problema.setDataHora(LocalDateTime.now());
        
        List<PoblemaException.CampoProblema> camposComErro = new ArrayList<PoblemaException.CampoProblema>();
        
        for (ObjetctError error : ex.getBindingResult().getAllErrors()) {
            String nomeCampo = ((FieldError) error).getField();
            String mensagemCampo = error.getDefaultMessage();
            
            camposComErro.add(new PoblemaException.CampoProblema(nomeCampo, mensagemCampo) );
        }
        
        problema.setCampos(camposComErro);
        
        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
    
    
}
