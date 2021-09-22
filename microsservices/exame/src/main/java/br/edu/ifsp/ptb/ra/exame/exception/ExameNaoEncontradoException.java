package br.edu.ifsp.ptb.ra.exame.exception;

public class ExameNaoEncontradoException extends Exception
{
    private static final long serialVersionUID = 101653068225814716L;

    public ExameNaoEncontradoException(Long idExame)
    {
        super("O exame com ID=" + idExame + " não foi encontrado!");
    }
}
