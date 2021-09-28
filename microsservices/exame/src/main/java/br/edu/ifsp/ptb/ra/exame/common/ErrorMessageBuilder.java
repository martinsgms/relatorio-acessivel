package br.edu.ifsp.ptb.ra.exame.common;

public class ErrorMessageBuilder
{

    public static final String RECURSO_EXAME = "Exame";
    public static final String RECURSO_EVENTO = "Evento";
    public static final String RECURSO_SERVICO_SAUDE = "Serviço de Saúde";
    public static final String RECURSO_USUARIO = "Usuário";

    private ErrorMessageBuilder()
    {
        // sem implementação
    }

    public static final String recursoInexistente(String recurso, Object id)
    {
        return "O " + recurso + " com o identificador '" + id + "' não existe.";
    }

}
