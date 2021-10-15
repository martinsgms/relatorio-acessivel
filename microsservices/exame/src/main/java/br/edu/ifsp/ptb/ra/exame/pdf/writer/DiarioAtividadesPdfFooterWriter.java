package br.edu.ifsp.ptb.ra.exame.pdf.writer;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

public class DiarioAtividadesPdfFooterWriter
{
    public static Paragraph write()
    {
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);

        Paragraph footer = new Paragraph();
        footer.add(new Phrase("Gerado pelo sistema Relatório Inteligente (2021)", cellFont));

        return footer;
    }
}
