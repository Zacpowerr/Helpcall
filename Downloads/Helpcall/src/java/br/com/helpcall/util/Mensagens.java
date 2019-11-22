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

    public static final String mensagemErroCadastro = "Ocorreu um erro ao cadastrar!";
    public static final String mensagemSucessoCadastro = "Cadastro efetuado com sucesso!";
    public static final String mensagemErroCadLimControle = "Limite de controles para o quarto excedido.";
    public static final String mensagemErroCadInvControle = "O MAC address informado já está cadastrado.";
    public static final String mensagemSalvamentoSucesso = "Salvo com sucesso!";
    public static final String mensagemErroSalvamento = "Ocorreu um erro ao salvar!";
    public static final String mensagemErroCadLocalControle = "No local informado já existe controle cadastrado";
    public static final String mensagemErroSenhasDiferentes = "As senhas não são iguais";

    public static void salvoComSucesso() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagemSucessoCadastro, ""));
    }

    public static void erroCadastro() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagemErroCadastro, ""));
    }

    public static void erroCadInvControle() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagemErroCadastro, ""));
    }

    public static void erroCadLocalControle() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagemErroCadLocalControle, ""));
    }

    public static void erroCadUnico(String msg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg + " já existe.", ""));
    }

    public static void erroCadLimControle() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagemErroCadLimControle, ""));
    }

    public static void erroDuplicado(String valorCampo) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, valorCampo + " já está cadastrado", ""));
    }

    public static void erroSenhasDiferentes() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagemErroSenhasDiferentes, ""));
    }
}
