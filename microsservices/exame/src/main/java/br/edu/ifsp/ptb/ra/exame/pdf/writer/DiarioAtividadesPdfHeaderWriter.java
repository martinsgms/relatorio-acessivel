package br.edu.ifsp.ptb.ra.exame.pdf.writer;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

public class DiarioAtividadesPdfHeaderWriter
{
    public static Paragraph write()
    {
        Paragraph cabecalho = new Paragraph();

        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(14);
        cabecalho.add(new Phrase("HeartCare ".toUpperCase(), cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase("Clínica HeartCare Serviços de Assistência à Saúde LTDA\n", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase("Paciente: ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);
        cabecalho.add(new Phrase("Daiana dos Santos", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(" | Exame Nº: ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);
        cabecalho.add(new Phrase("IEX-12983979X", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(" | Data de instalação: ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);
        cabecalho.add(new Phrase("01/10/2021", cellFont));

        return cabecalho;
    }
}
