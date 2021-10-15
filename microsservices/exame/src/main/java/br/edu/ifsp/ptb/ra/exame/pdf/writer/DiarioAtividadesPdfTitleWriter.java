package br.edu.ifsp.ptb.ra.exame.pdf.writer;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

public class DiarioAtividadesPdfTitleWriter
{
    public static Paragraph write()
    {
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(10);

        Paragraph titulo = new Paragraph();
        titulo.add(new Phrase("DIÁRIO DE ATIVIDADES DO EXAME DE MAPA\n\n", cellFont));

        return titulo;
    }
}
