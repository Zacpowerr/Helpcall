/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.control;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.MacDao;
import br.com.helpcall.daoImpl.MacDaoImpl;
import br.com.helpcall.daoImpl.QuartoDaoImpl;
import br.com.helpcall.model.Mac;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import br.com.helpcall.dao.QuartoDao;
import br.com.helpcall.model.Quarto;
import br.com.helpcall.util.Mensagens;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Thaisa
 */
@ManagedBean(name = "macC")
@ViewScoped
public class MacControl implements Serializable {

    private Mac mac;
    private Quarto quarto;
    private List<SelectItem> quartos;
    private MacDao macDao;
    private Session session;
    private List<Mac> macs;
    private int numCombo;

    @PostConstruct
    public void inicializar() {
        carregaCboxQuarto();
    }

    public MacControl() {
        System.out.println("");
    }

    public Mac getMac() {
        if (mac == null) {
            mac = new Mac();
        }
        return mac;
    }

    public void setMac(Mac mac) {
        this.mac = mac;
    }

    public MacDao getMacDao() {

        return macDao;
    }

    public void setMacDao(MacDao macDao) {
        this.macDao = macDao;
    }

    public List<Mac> getMacs() {
        return macs;
    }

    public Quarto getQuarto() {
        if(quarto == null){
            quarto = new Quarto();
        }
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
    
      public List<SelectItem> getQuartos() {
        return quartos;
    }

    public int getNumCombo() {
        return numCombo;
    }

    public void setNumCombo(int numCombo) {
        this.numCombo = numCombo;
    }
    
    

    public String salvar() {
        try {
            macDao = new MacDaoImpl();
            session = HibernateUtil.abreConexao();
            
            mac.setQuartoId(quarto);
            mac.setStatus("1");
            if (macDao.listarPorLeito(mac, session)) {
                macDao.salvarOuAlterar(mac, session);
                Mensagens.salvoComSucesso();
            } else {
                System.out.println("Leito já possui controle");
                Mensagens.erroCadastro();
            }
            
            
            quarto = new Quarto();
            mac = new Mac();
        } catch (ConstraintViolationException ex) {
            System.out.println("Erro ao cadastrar " + ex.getMessage());
            if(ex.getCause().toString().contains(mac.getMacadress())){
                Mensagens.erroDuplicado("O MAC Address " + mac.getMacadress());
            }
            
        } catch (HibernateException e) {
            System.out.println("Erro ao cadastrar " + e.getMessage());
            
        }finally{
            session.close();
        }
        
        return "";
    }

    private void carregaCboxQuarto() {
        session = HibernateUtil.abreConexao();
        QuartoDao quartoDao = new QuartoDaoImpl();
        quartos = new ArrayList<>();
        List<Quarto> quartoList = quartoDao.listarTodos(session);
        session.close();
        for (Quarto q : quartoList) {
            quartos.add(new SelectItem((q.getId()), q.getQuarto()));
        }

    }

    public String listar() {
        try {
            macDao = new MacDaoImpl();
            session = HibernateUtil.abreConexao();
            macs = macDao.listarTodos(session);
            session.close();

        } catch (HibernateException e) {
            System.out.println("Erro ao listar " + e.getMessage());
        }
        return "gestor/listaControles";
    }

  

}
