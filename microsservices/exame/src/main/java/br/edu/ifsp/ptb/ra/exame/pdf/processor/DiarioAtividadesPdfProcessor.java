package br.edu.ifsp.ptb.ra.exame.pdf.processor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;
import br.edu.ifsp.ptb.ra.exame.pdf.writer.DiarioAtividadesPdfFooterWriter;
import br.edu.ifsp.ptb.ra.exame.pdf.writer.DiarioAtividadesPdfHeaderWriter;
import br.edu.ifsp.ptb.ra.exame.pdf.writer.DiarioAtividadesPdfTableWriter;
import br.edu.ifsp.ptb.ra.exame.pdf.writer.DiarioAtividadesPdfTitleWriter;

public class DiarioAtividadesPdfProcessor
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DiarioAtividadesPdfProcessor.class);

    public static ByteArrayInputStream process(QuadroPaDTO diarioAtividades)
    {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try
        {
            Paragraph header = DiarioAtividadesPdfHeaderWriter.write();
            Paragraph title = DiarioAtividadesPdfTitleWriter.write();
            PdfPTable table = DiarioAtividadesPdfTableWriter.write(diarioAtividades);
            Paragraph footer = DiarioAtividadesPdfFooterWriter.write();

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(header);
            document.add(Chunk.NEWLINE);
            document.add(title);
            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(footer);

            writeMetadata(document);

            document.close();
        }
        catch (DocumentException ex)
        {
            LOGGER.error("Error occurred while writing pdf: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void writeMetadata(Document document)
    {
        document.addSubject("HEARTCARE-MAPA-DIARIO_ATIVIDADES");
        document.addKeywords("www.examemapa.tech");
        document.addAuthor("Sistema Diario de Atvidades Inteligente");
        document.addCreator("iText");
    }
}
