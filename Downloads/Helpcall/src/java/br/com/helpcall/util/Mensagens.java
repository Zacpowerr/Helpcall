/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eduardo
 */
public class Mensagens {

    public static final String mensagemErroCadastro = "Ocorreu um erro ao cadastrar o item!";
    public static final String mensagemSucessoCadastro = "Cadastro efetuado com sucesso!";
    public static final String mensagemSalvamentoSucesso = "O item foi salvo com sucesso!";
    public static final String mensagemErroSalvamento = "Ocorreu um erro ao salvar!";

    public static void salvoComSucesso() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemSucessoCadastro, ""));
    }

    public static void erroCadastro() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemErroCadastro, ""));
    }
}
