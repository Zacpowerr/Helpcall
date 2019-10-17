/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Eduardo
 */
public class SendMail {

    private SimpleEmail email;
//    private final String subject = "Email do site Vila Mineira";
    private final String user = "ebkempfer@gmail.com";
    private final String password = "ckjkcratytlfekrm";
//    private final String receiver = "e_kempfer@hotmail.com";
    private final String header = "Olá, Vila Mineira.\n"
            + "Acabamos de captar mais um lead no seu site!\n"
            + "confira os detalhes abaixo:\n";
    private final String footer = "Não esqueça de catalogar este interessado no seu CRM.\n"
            + "Boas vendas!\n\n"
            + "Atensiosamente,\n equipe de Desenvolvimento Garapuvu.";

    public SendMail() {
        email = new SimpleEmail();
    }

    public void sendEmail(String receiver, String subject, String message) {

        try {

            email.setDebug(true);
            //Gmail
            email.setHostName("smtp.gmail.com");
            email.setAuthentication(user, password);
            email.setSSL(true);
            email.addTo(receiver); //pode ser qualquer email  
            email.setFrom(user); //será passado o email que você fará a autenticação
            email.setSubject(subject);
            email.setMsg(header + "\n" + message + "\n" + footer);
            email.send();

        } catch (EmailException e) {

            System.out.println(e.getMessage());

        }
    }

    public SimpleEmail getEmail() {
        return email;
    }

    public void setEmail(SimpleEmail email) {
        this.email = email;
    }

}
