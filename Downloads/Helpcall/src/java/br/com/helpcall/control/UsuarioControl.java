/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.control;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.UsuarioDao;
import br.com.helpcall.daoImpl.UsuarioDaoImpl;
import br.com.helpcall.mail.SendMail;
import br.com.helpcall.model.Perfil;
import br.com.helpcall.model.Usuario;
import br.com.helpcall.util.Mensagens;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
@ManagedBean(name = "usuarioC")
@SessionScoped
public class UsuarioControl implements Serializable {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Session session;
    private List<Usuario> usuarios;
    private SendMail sendMail;
    private String receiver;
    private String subject;
    private String message;
    private String confirmaSenha;

    public UsuarioControl() {
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void salvar() {
        if (usuario.getSenha().equals(confirmaSenha)) {

            try {
                usuarioDao = new UsuarioDaoImpl();
                session = HibernateUtil.abreConexao();
                Perfil perfil = usuarioDao.carregarGestor(session);
                usuario.setPerfilId(perfil);
                usuario.setEnable(true);
                usuarioDao.salvarOuAlterar(usuario, session);
                enviarEmail(false);
                Mensagens.salvoComSucesso();
                session.close();
                usuario = new Usuario();

            } catch (HibernateException e) {
                Mensagens.erroCadastro();
                enviarEmail(true);
                System.out.println("Erro ao cadastrar " + e.getMessage());
            }
        } else {
            Mensagens.erroSenhasDiferentes();
        }
        confirmaSenha = "";
    }

    private void enviarEmail(boolean err) {
        sendMail = new SendMail();
        receiver = usuario.getLogin();
        if (err) {
            message = "Erro ao cadastrar sistema Helpcall";
            subject = "Cadastro nao efetuado";
        } else {
            subject = "Cadastro no sistema Helpcall";
            message = "Bem vindo ao sistema Helpcall";
        }
        sendMail.sendEmail(receiver, subject, message);
    }

    public String listar() {
        try {
            usuarioDao = new UsuarioDaoImpl();
            session = HibernateUtil.abreConexao();
            usuarios = usuarioDao.listarGestores(session);
            session.close();

        } catch (HibernateException e) {
            System.out.println("Erro ao listar " + e.getMessage());
        }
        return "/gestor/listaUsuarios.xhtml?faces-redirect=true";
    }

    public void desAtivar(Usuario usuario) {
        try {
            usuarioDao = new UsuarioDaoImpl();
            session = HibernateUtil.abreConexao();
            if (usuario.getEnable()) {

                usuario.setEnable(false);
            } else {
                usuario.setEnable(true);
            }
            usuarioDao.salvarOuAlterar(usuario, session);
        } catch (HibernateException e) {
            System.out.println("Erro ao desAtivar " + e.getMessage());
        } finally {
            session.close();
        }

    }

    //getters e setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

}
