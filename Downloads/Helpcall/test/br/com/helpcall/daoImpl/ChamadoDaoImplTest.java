/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.ChamadoDao;
import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.model.Chamado;
import br.com.helpcall.model.ChamadoAtivo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Eduardo
 */
public class ChamadoDaoImplTest {

    public ChamadoDaoImplTest() {
    }
    ChamadoDao chamadoDao;
    Session session;
    List<Chamado> list;
    List<ChamadoAtivo> listativo;

    @Test
    public void listarTodosTeste() {
        for (int i = 0; i < 100; i++) {
            chamadoDao = new ChamadoDaoImpl();
            
            session = HibernateUtil.abreConexao();
            list = chamadoDao.listarTodos(session);
            listativo = chamadoDao.listarChamadoAtivo(session);
            session.flush();
        }

    }

//    @Test
    public void gerarPdf() throws IOException {
        Document doc = new Document();
        List<Chamado> listChamados = new ArrayList<>();
        chamadoDao = new ChamadoDaoImpl();
        session = HibernateUtil.abreConexao();
        listChamados = chamadoDao.listarTodos(session);
        String arquivoPDF = "Relatório.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
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
            Desktop.getDesktop().open(new File(arquivoPDF));

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

    }

}
