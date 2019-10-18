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
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
@ManagedBean(name = "usuarioC")
public class UsuarioControl implements Serializable {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Session session;
    private List<Usuario> usuarios;
    private SendMail sendMail;
    private String receiver;
    private String subject;
    private String message;

    public UsuarioControl(Usuario usuario, UsuarioDao usuarioDao) {
        this.usuario = usuario;
        this.usuarioDao = usuarioDao;
    }

    public UsuarioControl() {
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

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

    public String salvar() {
        subject = "Cadastro no sistema Helpcall";
        message = "Bem vindo ao sistema Helpcall";
        try {
            usuarioDao = new UsuarioDaoImpl();
            session = HibernateUtil.abreConexao();
            Perfil perfil = usuarioDao.carregarGestor(session);
            usuario.setPerfil(perfil);
            usuario.setEnable(true);
            usuarioDao.salvarOuAlterar(usuario, session);
            sendMail = new SendMail();
            receiver = usuario.getLogin();
            sendMail.sendEmail(receiver, subject, message);
            Mensagens.salvoComSucesso();
            session.close();

        } catch (HibernateException e) {
            Mensagens.erroCadastro();
            message = "Erro ao cadastrar sistema Helpcall";
            receiver = usuario.getLogin();
            sendMail.sendEmail(receiver, subject, message);
            System.out.println("Erro ao cadastrar " + e.getMessage());
        }
        return "cadUsuario";
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
        return "listaUsuarios";
    }

    public void logar() {
        try {
            usuarioDao = new UsuarioDaoImpl();
            session = HibernateUtil.abreConexao();
            // Logar!!!!!!!!!!!
            session.close();

        } catch (HibernateException e) {
            System.out.println("Erro ao logar " + e.getMessage());
        }

    }

//    public void verificarSenha() {
//        FacesContext contexto = FacesContext.getCurrentInstance();
//
//        if (!(usuario.getSenha().length() == 6)) {
//            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Senha Inválida", "Erro: senha deve conter 6 dígitos"));
//        }
//
//    }
}
