/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.control;

import br.com.helpcall.mail.SendMail;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Eduardo
 */
@ManagedBean
public class RecuperaSenhaControl {

    SendMail sendMail = new SendMail();

    private String assunto = "";
    private String mensagem = "";
    private String email;

    public RecuperaSenhaControl() {
    }

    public void enviarEmail() {
        System.out.println(email);
        sendMail.sendEmail(email, assunto, mensagem);
        email = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
