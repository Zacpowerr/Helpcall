/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "chamado_ativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChamadoAtivo.findAll", query = "SELECT c FROM ChamadoAtivo c")
    , @NamedQuery(name = "ChamadoAtivo.findByStatus", query = "SELECT c FROM ChamadoAtivo c WHERE c.status = :status")
    , @NamedQuery(name = "ChamadoAtivo.findByHoraInit", query = "SELECT c FROM ChamadoAtivo c WHERE c.horaInit = :horaInit")
    , @NamedQuery(name = "ChamadoAtivo.findByMacId", query = "SELECT c FROM ChamadoAtivo c WHERE c.macId = :macId")
    , @NamedQuery(name = "ChamadoAtivo.findByQuartoId", query = "SELECT c FROM ChamadoAtivo c WHERE c.quartoId = :quartoId")
    , @NamedQuery(name = "ChamadoAtivo.findByLeito", query = "SELECT c FROM ChamadoAtivo c WHERE c.leito = :leito")})
public class ChamadoAtivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "status")
    private String status;
    @Column(name = "hora_init")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInit;
    @Basic(optional = false)
    @Column(name = "mac_id")
    private int macId;
    @Basic(optional = false)
    @Column(name = "quarto_id")
    @Id
    private int quartoId;
    @Column(name = "leito")
    private String leito;

    public ChamadoAtivo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHoraInit() {
        return horaInit;
    }

    public void setHoraInit(Date horaInit) {
        this.horaInit = horaInit;
    }

    public int getMacId() {
        return macId;
    }

    public void setMacId(int macId) {
        this.macId = macId;
    }

    public int getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(int quartoId) {
        this.quartoId = quartoId;
    }

    public String getLeito() {
        return leito;
    }

    public void setLeito(String leito) {
        this.leito = leito;
    }
    
}
