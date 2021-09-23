package br.edu.ifsp.ptb.ra.exame.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.ifsp.ptb.ra.exame.dto.DefaultErrorDTO;
import br.edu.ifsp.ptb.ra.exame.exception.EventoNaoEncontradoException;
import br.edu.ifsp.ptb.ra.exame.exception.ExameNaoEncontradoException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice
{
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExameNaoEncontradoException.class)
    public DefaultErrorDTO exameNaoEncontrado(ExameNaoEncontradoException exception, HttpServletRequest request)
    {
        return new DefaultErrorDTO(exception, request);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EventoNaoEncontradoException.class)
    public DefaultErrorDTO eventoNaoEncontrado(EventoNaoEncontradoException exception, HttpServletRequest request)
    {
        return new DefaultErrorDTO(exception, request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public DefaultErrorDTO defaultError(ExameNaoEncontradoException exception, HttpServletRequest request)
    {
        return new DefaultErrorDTO(exception, request);
    }
}
