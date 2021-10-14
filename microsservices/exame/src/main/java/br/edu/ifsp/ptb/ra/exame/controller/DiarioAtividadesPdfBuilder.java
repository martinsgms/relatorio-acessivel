package br.edu.ifsp.ptb.ra.exame.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.ifsp.ptb.ra.exame.dto.AfericaoPaDTO;
import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;

public class DiarioAtividadesPdfBuilder
{

    private static final String NO_CONTENT = "------";

//    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream build(QuadroPaDTO quadroPa)
    {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try
        {
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new float[]
            { 0.2f, 0.4f, 0.3f, 0.3f, 1.5f, 1, 1 });

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            headFont.setSize(10);

            PdfPCell hcell;
            
            hcell = new PdfPCell(new Phrase("Nº", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Hora", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("PAS", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("PAD", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Atividade", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Sintoma", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Medicamento", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

//            hcell = new PdfPCell(new Phrase("Population", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
            Integer counter = 1;
            for (AfericaoPaDTO afericao : quadroPa.getAfericoes())
            {

                PdfPCell cell;
                
                Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
                cellFont.setSize(10);

                cell = new PdfPCell(new Phrase((counter++).toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(DateTimeFormatter.ofPattern("HH:mm").format(afericao.getTimestamp()).toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(afericao.getPas().toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(afericao.getPad().toString(), cellFont));
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
                    sintomas = NO_CONTENT;
                }
                cell = new PdfPCell(new Phrase(sintomas, cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                if (sintomas.equals(NO_CONTENT))
                {
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                } else
                {

                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                }
                table.addCell(cell);

                String medicamentos = afericao.getEventoAssociado().stream().map(EventoDTO::getMedicamento).collect(Collectors.joining(", "));
                if (medicamentos.equals("null"))
                {
                    medicamentos = NO_CONTENT;
                }
                cell = new PdfPCell(new Phrase(medicamentos, cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                if (medicamentos.equals(NO_CONTENT))
                {
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                } else
                {

                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                }
                table.addCell(cell);

            }

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

            Paragraph titulo = new Paragraph();

            cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            cellFont.setSize(10);
            titulo.add(new Phrase("DIÁRIO DE ATIVIDADES DO EXAME DE MAPA\n\n", cellFont));

            Paragraph footer = new Paragraph();

            cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            cellFont.setSize(9);
            footer.add(new Phrase("Gerado pelo sistema Relatório Inteligente (2021)", cellFont));

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(cabecalho);
            document.add(Chunk.NEWLINE);
            document.add(titulo);
            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(footer);

            document.addSubject("HEARTCARE-MAPA-DIARIO_ATIVIDADES");
            document.addKeywords("www.examemapa.tech");
            document.addAuthor("Sistema Diario de Atvidades Inteligente");
            document.addCreator("iText");

            document.close();

        } catch (DocumentException ex)
        {

//            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
