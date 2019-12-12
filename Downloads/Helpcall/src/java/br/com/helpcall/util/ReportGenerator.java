/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.util;

import br.com.helpcall.daoImpl.ChamadoDaoImpl;
import br.com.helpcall.model.Chamado;
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
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.hibernate.Session;

/**
 *
 * @author Eduardo
 */
public class ReportGenerator {
    
    private final ChamadoDaoImpl chamadoDaoImpl;
    private final String directoryName = "C://Helpcall";
//    private final String directoryName = "/home/pi/sambashare";

    public ReportGenerator() {
        chamadoDaoImpl = new ChamadoDaoImpl();
        createDirectory();
    }
    
    public File gerarPdf(String year, Integer month, Session session) throws IOException {
        Document doc = new Document();
        List<Chamado> listChamados = new ArrayList<>();
        if (!year.equals("")) {
            listChamados = chamadoDaoImpl.listarPorAno(year, session);
        } else if (month != null) {
            listChamados = chamadoDaoImpl.listarPorMes(month, session);
        } else {
            listChamados = chamadoDaoImpl.listarTodos(session);
        }
        String arquivoPDF = "Relatório" + LocalDate.now() + ".pdf";
        try {
            
            PdfWriter.getInstance(doc, new FileOutputStream(createDirectory("PDF") + "\\" + arquivoPDF));
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
                cell1 = new PdfPCell(new Paragraph(c.getId().toString()));
                cell2 = new PdfPCell(new Paragraph(c.getMacId().getQuartoId().getQuarto()));
                cell3 = new PdfPCell(new Paragraph(c.getMacId().getLeito()));
                cell4 = new PdfPCell(new Paragraph(c.dateFormatter(c.getHoraInit())));
                if (c.getHoraEnd() == null) {
                    cell5 = new PdfPCell(new Paragraph(""));
                } else {
                    cell5 = new PdfPCell(new Paragraph(c.dateFormatter(c.getHoraEnd())));
                }
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
    
    public WritableWorkbook GerarExcel(String year, Integer month, Session session) {
        List<Chamado> listChamados = new ArrayList<>();
        if (!year.equals("")) {
            listChamados = chamadoDaoImpl.listarPorAno(year, session);
        } else if (month != null) {
            listChamados = chamadoDaoImpl.listarPorMes(month, session);
        } else {
            listChamados = chamadoDaoImpl.listarTodos(session);
        }
        String arquivoXLS = "Relatório" + LocalDate.now() + ".xls";
        WritableWorkbook workbook = null;
        try {
            
            String filename = createDirectory("Excel") + "\\" + arquivoXLS;
            
            workbook = Workbook.createWorkbook(new File(filename));
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            Label numChamado = new Label(0, 0, "Numero do chamado");
            sheet.addCell(numChamado);
            Label quarto = new Label(1, 0, "Quarto");
            sheet.addCell(quarto);
            Label leito = new Label(2, 0, "Leito");
            sheet.addCell(leito);
            Label horainit = new Label(3, 0, "Hora de Inicio");
            sheet.addCell(horainit);
            Label horaend = new Label(4, 0, "Hora de Termino");
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
                Label numChamado1 = new Label(coluna, linha, c.getId().toString());
                sheet.addCell(numChamado1);
                coluna++;
                Label quartol = new Label(coluna, linha, String.valueOf(c.getMacId().getQuartoId().getQuarto()));
                sheet.addCell(quartol);
                coluna++;
                Label leitol = new Label(coluna, linha, c.getMacId().getLeito());
                sheet.addCell(leitol);
                coluna++;
                Label horainitl = new Label(coluna, linha, c.dateFormatter(c.getHoraInit()));
                sheet.addCell(horainitl);
                coluna++;
                Label horaendl;
                if (c.getHoraEnd() == null) {
                    horaendl = new Label(coluna, linha, "");
                } else {
                    horaendl = new Label(coluna, linha, c.dateFormatter(c.getHoraEnd()));
                }
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
    
    private void createDirectory() {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Diretorio criado");
        } else {
            
            System.out.println("Diretorio ja existe");
        }
        
    }
    
    private String createDirectory(String tipo) {
        String diretorio = directoryName + "\\" + tipo;
        File directory = new File(diretorio);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Sup diretorio criado");
        } else {
            
            System.out.println("Sup diretorio ja existe");
        }
        return diretorio;
    }
}
