package br.edu.ifsp.ptb.ra.exame.pdf.writer;

import static br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig.ATIVIDADE;
import static br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig.HORA;
import static br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig.MEDICAMENTO;
import static br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig.NUMERO_AFERICAO;
import static br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig.PAD;
import static br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig.PAS;
import static br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig.SINTOMA;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import br.edu.ifsp.ptb.ra.exame.dto.AfericaoPaDTO;
import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;
import br.edu.ifsp.ptb.ra.exame.enums.DiarioAtividadePdfHeaderConfig;

public class DiarioAtividadesPdfTableWriter
{
    private static final String CELL_NO_CONTENT = "";

    public static PdfPTable write(QuadroPaDTO quadroPa) throws DocumentException
    {
        PdfPTable table = new PdfPTable(DiarioAtividadePdfHeaderConfig.getHeaderCount());
        table.setWidthPercentage(100);

        table.setWidths(new float[] {
            NUMERO_AFERICAO.getWidth(),
            HORA.getWidth(),
            PAS.getWidth(),
            PAD.getWidth(),
            ATIVIDADE.getWidth(),
            SINTOMA.getWidth(),
            MEDICAMENTO.getWidth(),
        });

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        headerFont.setSize(10);

        PdfPCell headerCell;

        headerCell = new PdfPCell(new Phrase(NUMERO_AFERICAO.getName(), headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase(HORA.getName(), headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase(PAS.getName(), headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase(PAD.getName(), headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase(ATIVIDADE.getName(), headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase(SINTOMA.getName(), headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase(MEDICAMENTO.getName(), headerFont));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        writeTableBody(quadroPa, table);

        return table;
    }

    private static void writeTableBody(QuadroPaDTO quadroPa, PdfPTable table)
    {
        Integer numeroAfericao = 1;

        for (AfericaoPaDTO afericao : quadroPa.getAfericoes())
        {
            PdfPCell cell;

            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
            cellFont.setSize(10);

            cell = new PdfPCell(new Phrase((numeroAfericao++).toString(), cellFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            String horaAfericao = DateTimeFormatter.ofPattern("HH:mm").format(afericao.getTimestamp());
            cell = new PdfPCell(new Phrase(horaAfericao, cellFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            String pas = afericao.getPas().toString();
            cell = new PdfPCell(new Phrase(pas, cellFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            String pad = afericao.getPad().toString();
            cell = new PdfPCell(new Phrase(pad, cellFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            String atividades = afericao.getEventoAssociado().stream().map(EventoDTO::getDescricao).collect(Collectors.joining(", "));
            cell = new PdfPCell(new Phrase(atividades, cellFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            String sintomas = afericao.getEventoAssociado().stream().map(EventoDTO::getSintoma).collect(Collectors.joining(", "));
            if (sintomas.equals("null"))
            {
                sintomas = CELL_NO_CONTENT;
            }
            cell = new PdfPCell(new Phrase(sintomas, cellFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            String medicamentos = afericao.getEventoAssociado().stream().map(EventoDTO::getMedicamento).collect(Collectors.joining(", "));
            if (medicamentos.equals("null"))
            {
                medicamentos = CELL_NO_CONTENT;
            }
            cell = new PdfPCell(new Phrase(medicamentos, cellFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
        }
    }
}
