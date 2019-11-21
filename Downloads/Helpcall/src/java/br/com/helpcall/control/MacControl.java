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
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import br.com.helpcall.dao.QuartoDao;
import br.com.helpcall.model.Quarto;
import br.com.helpcall.util.Mensagens;
import javax.faces.bean.SessionScoped;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Thaisa
 */
@ManagedBean(name = "macC")
@SessionScoped
public class MacControl implements Serializable {

    private Mac mac;
    private Quarto quarto;
    private List<SelectItem> quartos;
    private MacDao macDao;
    private Session session;
    private List<Mac> macs;
    private int numCombo;
    private int id;

    @PostConstruct
    public void inicializar() {
        carregaCboxQuarto();

    }

    public MacControl() {
        System.out.println("");
        listar();
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
        if (quarto == null) {
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

            mac.setQuartoId(quarto);
            mac.setStatus("1");
            session = HibernateUtil.abreConexao();
            if (verifLocalMAC() && verifLimite(quarto.getId())) {
                macDao.salvarOuAlterar(mac, session);
                Mensagens.salvoComSucesso();
            }

            quarto = new Quarto();
            mac = new Mac();
        } catch (ConstraintViolationException ex) {
            System.out.println("Erro ao cadastrar " + ex.getMessage());
            if (ex.getCause().toString().contains(mac.getMacadress())) {
                Mensagens.erroDuplicado("O MAC Address " + mac.getMacadress());
            }

        } catch (HibernateException e) {
            Mensagens.erroCadastro();
            System.out.println("Erro ao cadastrar " + e.getMessage());

        } finally {
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
        quartoList.forEach((q) -> {
            quartos.add(new SelectItem((q.getId()), q.getQuarto()));
        });

    }

    public String listarPage() {
        listar();
        return "gestor/listaControles";
    }

    private void listar() {
        try {
            macDao = new MacDaoImpl();
            session = HibernateUtil.abreConexao();
            macs = macDao.listarTodos(session);
            session.close();
        } catch (HibernateException e) {
            System.out.println("Erro ao listar " + e.getMessage());
        }

    }

    public boolean verifLocalMAC() {
        if (!macDao.listarPorLeito(mac, session)) {
            Mensagens.erroCadLocalControle();
            return false;

        }

        return true;

    }

    public boolean verifLimite(Long quartoId) {
        macDao = new MacDaoImpl();
        QuartoDao quartoDao = new QuartoDaoImpl();
        quarto = quartoDao.pesquisarPorID(quartoId, session);
        int limite = quarto.getLimiteControle();
        mac.setQuartoId(quarto);
        long totmacs = macDao.ContarMacsPorQuarto(quarto.getId(), session);
        if (totmacs < limite) {
            return true;
        }
        Mensagens.erroCadLimControle();
        return false;
    }

    public String editarPage(int index) {
        session = HibernateUtil.abreConexao();
        macDao = new MacDaoImpl();
        mac = macDao.listarPorId(index, session);
        return "editarControle.xhtml";
    }

    public void verificarMacUnico() {
        session = HibernateUtil.abreConexao();
        try {
            macDao = new MacDaoImpl();
            Mac mac2 = macDao.verificarMacUnico(mac.getMacadress(), session);
            if (mac2 != null) {
                Mensagens.erroCadUnico("O MAC address");
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
    }

    public void editar() {

//        if (verif(quarto.getId(), session)) {
//            mac.setQuartoId(quarto);
//            macDao.salvarOuAlterar(mac, session);
//        }
        System.out.println(mac.getQuartoId().getId());
        System.out.println(mac.getLeito());
        System.out.println(mac.getStatus());
    }
}
