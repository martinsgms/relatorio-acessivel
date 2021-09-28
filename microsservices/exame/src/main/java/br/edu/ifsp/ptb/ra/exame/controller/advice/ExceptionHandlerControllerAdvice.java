package br.edu.ifsp.ptb.ra.exame.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.ifsp.ptb.ra.exame.dto.DefaultExceptionDTO;
import br.edu.ifsp.ptb.ra.exame.exception.ServiceException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice
{
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public DefaultExceptionDTO serviceException(ServiceException exception, HttpServletRequest request)
    {
        return new DefaultExceptionDTO(exception, request);
    }
}
