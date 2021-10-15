package br.edu.ifsp.ptb.ra.exame.pdf.writer;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;

public class DiarioAtividadesPdfHeaderWriter
{
    public static Paragraph write(QuadroPaDTO diarioAtividades)
    {
        Paragraph cabecalho = new Paragraph();

        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(14);
        cabecalho.add(new Phrase(diarioAtividades.getNomeServicoSaude().toUpperCase() + " ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(diarioAtividades.getNomeCompletoServicoSaude() + "\n", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase("Paciente: ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(diarioAtividades.getNomePaciente(), cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(" | Exame Nº: ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(diarioAtividades.getIdExternoExame(), cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(" | Data de instalação: ", cellFont));

        cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        cellFont.setSize(9);
        cabecalho.add(new Phrase(diarioAtividades.getDataHoraInstalacao(), cellFont));

        return cabecalho;
    }
}
