package br.edu.ifsp.ptb.ra.exame.pdf.writer;

import java.time.LocalDateTime;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import br.edu.ifsp.ptb.ra.exame.util.DateTimeUtils;

public class DiarioAtividadesPdfFooterWriter
{
    public static Paragraph write()
    {
        Paragraph footer = new Paragraph();

        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        footer.add(new Phrase("Gerado pelo sistema ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);
        footer.add(new Phrase("Diário MAPA Inteligente (2021) ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        footer.add(new Phrase("- " + DateTimeUtils.getSemanaDiaMesAnoHoraExtenso(LocalDateTime.now()), cellFont));

        return footer;
    }
}
