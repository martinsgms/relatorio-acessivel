package br.edu.ifsp.ptb.ra.exame.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils
{
    public static String getDataHora(LocalDateTime timestamp)
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy à's' HH:mm").format(timestamp);
    }

    public static String getSemanaDiaMesAnoHoraExtenso(LocalDateTime timestamp)
    {
        return DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy 'às' HH:mm", new Locale ("pt", "BR")).format(timestamp);
    }

    public static String getAnoMesDiaFilename(LocalDateTime timestamp)
    {
        return DateTimeFormatter.ofPattern("yyyyMMdd").format(timestamp);
    }
}
