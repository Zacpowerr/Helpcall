package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.ChamadoDao;
import br.com.helpcall.model.Chamado;
import br.com.helpcall.model.ChamadoAtivo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Eduardo Bastos
 */
public class ChamadoDaoImpl extends BaseDaoImpl<Chamado, Long>
        implements ChamadoDao {

    @Override
    public List<Chamado> listarPorMes(Integer month, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Chamado where MONTH(horainit) = :month and YEAR(horainit) = YEAR(now())");
        consulta.setParameter("month", month);
        return consulta.list();

    }

    @Override
    public List<Chamado> listarPorAno(Integer year, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Chamado where YEAR(horainit) = YEAR(:year)");
        consulta.setParameter("year", year);
        return consulta.list();
    }

    @Override
    public List<ChamadoAtivo> listarChamadoAtivo(Session session) throws HibernateException {
        return session.getNamedQuery("ChamadoAtivo.findAll").list();
    }

    @Override
    public List<Chamado> listarTodos(Session session) throws HibernateException {
        return session.createQuery("from Chamado order by idChamado DESC").setMaxResults(10).list();
    }

    public File gerarPdf(Integer year, Integer month, Session session) throws IOException {
        Document doc = new Document();
        List<Chamado> listChamados = new ArrayList<>();
        if (year != null) {
            listChamados = listarPorAno(year, session);
        } else if (month != null) {
            listChamados = listarPorMes(month, session);
        } else {
            listChamados = listarTodos(session);
        }
        String arquivoPDF = "Relatório" + LocalDate.now() + ".pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Eduardo\\Desktop\\PDF\\" + arquivoPDF));
            doc.open();
            Paragraph para = new Paragraph("Relatório de Chamados");
            para.setAlignment(1);
            doc.add(para);
            para = new Paragraph(" ");
            doc.add(para);
            PdfPTable table = new PdfPTable(5);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Numero do Chamado"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Quarto"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Leito"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Hora do Chamado"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Finalização do Chamado"));

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);

            for (Chamado c : listChamados) {
                cell1 = new PdfPCell(new Paragraph(c.getIdChamado().toString()));
                cell2 = new PdfPCell(new Paragraph(c.getMACidMAC().getIdQuarto().getQuarto()));
                cell3 = new PdfPCell(new Paragraph(c.getMACidMAC().getLeito()));
                cell4 = new PdfPCell(new Paragraph(c.getHorainit().toString()));
                cell5 = new PdfPCell(new Paragraph(c.getHoraend().toString()));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
            }
            doc.add(table);
            doc.close();
            //Desktop.getDesktop().open(new File(arquivoPDF));

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
        return new File(arquivoPDF);
    }

    public WritableWorkbook GerarExcel(Integer year, Integer month, Session session) {
        List<Chamado> listChamados = new ArrayList<>();
        if (year != null) {
            listChamados = listarPorAno(year, session);
        } else if (month != null) {
            listChamados = listarPorMes(month, session);
        } else {
            listChamados = listarTodos(session);
        }
        String arquivoXLS = "Relatório" + LocalDate.now() + ".xls";
        WritableWorkbook workbook = null;
        try {
            String filename = "C:\\Users\\Eduardo\\Desktop\\Excel" + arquivoXLS;
            //C:\\Users\\Aluno\\Desktop\\file.xls

            workbook = Workbook.createWorkbook(new File(filename));
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            Label quarto = new Label(0, 0, "Quarto");
            sheet.addCell(quarto);
            Label leito = new Label(1, 0, "Leito");
            sheet.addCell(leito);
            Label horainit = new Label(2, 0, "Hora de Inicio");
            sheet.addCell(horainit);
            Label horaend = new Label(3, 0, "Hora de Termino");
            sheet.addCell(horaend);
            int linha = 1;
            int coluna = 0;
            for (Chamado c : listChamados) {
                if (coluna > 3) {
                    linha++;
                    coluna = 0;
                } else {
                    System.out.println("tudo certo");
                }
                Label quartol = new Label(coluna, linha, String.valueOf(c.getMACidMAC().getIdQuarto().getQuarto()));
                sheet.addCell(quartol);
                coluna++;
                Label leitol = new Label(coluna, linha, c.getMACidMAC().getLeito());
                sheet.addCell(leitol);
                coluna++;
                Label horainitl = new Label(coluna, linha, c.getHorainit().toString());
                sheet.addCell(horainitl);
                coluna++;
                Label horaendl = new Label(coluna, linha, c.getHoraend().toString());
                sheet.addCell(horaendl);
                coluna++;
            }
            workbook.write();
            workbook.close();

            // Desktop.getDesktop().open(workbook);
        } catch (Exception e) {
        }
        return workbook;
    }

}
