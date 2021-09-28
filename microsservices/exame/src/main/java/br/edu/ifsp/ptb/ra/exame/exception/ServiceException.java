package br.edu.ifsp.ptb.ra.exame.exception;

public class ServiceException extends Exception
{
    private static final long serialVersionUID = 101653068225814716L;

    public ServiceException(String message)
    {
        super(message);
    }
}
