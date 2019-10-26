/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.control;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.MacDao;
import br.com.helpcall.dao.PortaDao;
import br.com.helpcall.daoImpl.MacDaoImpl;
import br.com.helpcall.daoImpl.PortaDaoImpl;
import br.com.helpcall.model.Mac;
import br.com.helpcall.model.Porta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
@ManagedBean(name = "macC")
@ViewScoped
public class MacControl implements Serializable {

    private Mac mac;
    private Porta porta;
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

    public String salvar() {
        try {
            mac.setIdQuarto(porta);
            mac.setStatus(true);
            macDao = new MacDaoImpl();
            session = HibernateUtil.abreConexao();
            if (verifcaDados(mac, session)) {
                macDao.salvarOuAlterar(mac, session);

            } else {
                System.out.println("Leito ja possui controle");
            }
            session.close();
            porta = new Porta();
            mac = new Mac();
        } catch (HibernateException e) {
            System.out.println("Erro ao cadastrar " + e.getMessage());
        }

        return "";
    }

    private void carregaCboxQuarto() {
        session = HibernateUtil.abreConexao();
        PortaDao portaDao = new PortaDaoImpl();
        quartos = new ArrayList<>();
        List<Porta> portas = portaDao.listarTodos(session);
        session.close();
        for (Porta port : portas) {
            quartos.add(new SelectItem(Integer.parseInt(port.getQuarto()), port.getQuarto()));
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

    public Porta getPorta() {
        if (porta == null) {
            porta = new Porta();
        }
        return porta;
    }

    public void setPorta(Porta quarto) {
        this.porta = quarto;
    }

    public void ativar() {
        System.out.println(mac.isStatus());
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

    private boolean verifcaDados(Mac mac, Session session) {
        List<Mac> list = macDao.listarPorLeito(mac, session);
        return list.isEmpty();
    }

}
