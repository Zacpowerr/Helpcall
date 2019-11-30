package br.com.helpcall.control;

import br.com.helpcall.dao.ChamadoDao;
import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.daoImpl.ChamadoDaoImpl;
import br.com.helpcall.model.Chamado;
import br.com.helpcall.model.ChamadoAtivo;
import br.com.helpcall.util.Mensagens;
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
    private String mensagem;

    private int number;

    public int getNumber() {
        return number;
    }

    public void increment() {
        number++;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ChamadoControl() {
        chamadoAtivo();
        mensagem = Mensagens.mensagemSalvamentoSucesso;
    }

    public void chamadoAtivo() {
        listChamadoAtivo = new ArrayList<>();
        chamadoDao = new ChamadoDaoImpl();
        session = HibernateUtil.abreConexao();
        listChamadoAtivo = chamadoDao.listarChamadoAtivo(session);
        listTodos = chamadoDao.listarTodos(session);
        session.flush();
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
