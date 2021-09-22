package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

public class DefaultErrorDTO
{
    private LocalDateTime timestamp = LocalDateTime.now();
    private String path;
    private String error;
    private String message;

    public DefaultErrorDTO(Exception exception, HttpServletRequest request)
    {
        error = exception.getClass().getSimpleName();
        message = exception.getMessage();
        path = request.getMethod() + " " + request.getRequestURI();
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
