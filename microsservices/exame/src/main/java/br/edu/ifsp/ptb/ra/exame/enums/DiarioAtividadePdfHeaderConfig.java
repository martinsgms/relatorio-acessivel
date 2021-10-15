package br.edu.ifsp.ptb.ra.exame.enums;

public enum DiarioAtividadePdfHeaderConfig
{

    NUMERO_AFERICAO("Nº", 0.2f),
    HORA("Hora", 0.4f),
    PAS("PAS", 0.3f),
    PAD("PAD", 0.3f),
    ATIVIDADE("Atividade", 1.5f),
    SINTOMA("Sintoma", 1f),
    MEDICAMENTO("Medicamento", 1f);

    private String name;
    private Float width;

    DiarioAtividadePdfHeaderConfig(String name, Float width)
    {
        this.name = name;
        this.width = width;
    }

    public String getName()
    {
        return name;
    }

    public Float getWidth()
    {
        return width;
    }

    public static int getHeaderCount()
    {
        return values().length;
    }
}
