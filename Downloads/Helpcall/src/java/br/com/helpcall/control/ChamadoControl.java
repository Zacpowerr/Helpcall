package br.com.helpcall.control;

import br.com.helpcall.dao.ChamadoDao;
import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.daoImpl.ChamadoDaoImpl;
import br.com.helpcall.model.Chamado;
import br.com.helpcall.model.ChamadoAtivo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import jxl.write.WritableWorkbook;
import org.hibernate.Session;

/**
 *
 * @author Eduardo Bastos
 */
@ManagedBean(name = "chamadoC")
@ViewScoped
public class ChamadoControl implements Serializable {

    private ChamadoDao chamadoDao;
    private Session session;
    private List<ChamadoAtivo> listChamadoAtivo;
    private List<Chamado> listTodos;
    private String year;
    private Integer month;
    private boolean error;

    public ChamadoControl() {
        chamadoAtivo();

    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public final void chamadoAtivo() {
        listChamadoAtivo = new ArrayList<>();
        chamadoDao = new ChamadoDaoImpl();
        session = HibernateUtil.abreConexao();
        listChamadoAtivo = chamadoDao.listarChamadoAtivo(session);
        listTodos = chamadoDao.listarTodos(session);
        session.flush();
//        session.close();
    }
//Metodos de geracao de docs 

    public void gerarPdf(String mes, String ano) throws IOException {
        ChamadoDaoImpl chamadoDaoImpl = new ChamadoDaoImpl();
        session = HibernateUtil.abreConexao();
        if (!mes.equals("")) {
            month = Integer.parseInt(mes);
        }
        year = ano;
        File pdf = chamadoDaoImpl.gerarPdf(year, month, session);
        error = !pdf.canRead();

        session.close();

    }

    public void gerarExcel(String mes, String ano) throws IOException {
        ChamadoDaoImpl chamadoDaoImpl = new ChamadoDaoImpl();
        session = HibernateUtil.abreConexao();
        if (!mes.equals("")) {
            month = Integer.parseInt(mes);
        }
        year = ano;
        WritableWorkbook excel = chamadoDaoImpl.GerarExcel(year, month, session);
        session.close();

    }

    public List<ChamadoAtivo> getListChamadoAtivo() {
        return listChamadoAtivo;
    }

    public List<Chamado> getListTodos() {
        return listTodos;
    }

    public void setListTodos(List<Chamado> listTodos) {
        this.listTodos = listTodos;
    }

    public void setListChamadoAtivo(List<ChamadoAtivo> listChamadoAtivo) {
        this.listChamadoAtivo = listChamadoAtivo;
    }

}
