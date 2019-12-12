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
import br.com.helpcall.model.Usuario;
import br.com.helpcall.util.Mensagens;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;

/**
 *
 * @author Eduardo
 */
@ManagedBean
public class RecuperaSenhaControl {

    SendMail sendMail = new SendMail();

    private String assunto = "Recuperação de senha Helpcall";
    private String mensagem = "Sua senha é: ";
    private String email;

    public RecuperaSenhaControl() {
    }

    public String enviarEmail() {
        System.out.println(email);
        Session session = HibernateUtil.abreConexao();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        Usuario u = usuarioDao.pesquisarPorLogin(email, session);
        sendMail.sendEmail(email, assunto, mensagem + u.getSenha());
        email = null;
        Mensagens.emailEnviado();
        return "/login.xhtml";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
