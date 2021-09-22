package br.edu.ifsp.ptb.ra.exame.exception;

public class EventoNaoEncontradoException extends Exception
{
    private static final long serialVersionUID = 6613554390351583834L;

    public EventoNaoEncontradoException(Long idEvento)
    {
        super("O evento com ID=" + idEvento + " não foi encontrado!");
    }
}
