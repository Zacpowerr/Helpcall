/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.model;

import java.io.Serializable;
import java.util.Date;
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
 * @author Eduardo
 */
@Entity
@Table(name = "chamado_ativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChamadoAtivo.findAll", query = "SELECT c FROM ChamadoAtivo c")
    , @NamedQuery(name = "ChamadoAtivo.findByStatus", query = "SELECT c FROM ChamadoAtivo c WHERE c.status = :status")
    , @NamedQuery(name = "ChamadoAtivo.findByHorainit", query = "SELECT c FROM ChamadoAtivo c WHERE c.horainit = :horainit")
    , @NamedQuery(name = "ChamadoAtivo.findByLeito", query = "SELECT c FROM ChamadoAtivo c WHERE c.leito = :leito")
    , @NamedQuery(name = "ChamadoAtivo.findByIdQuarto", query = "SELECT c FROM ChamadoAtivo c WHERE c.idQuarto = :idQuarto")})
public class ChamadoAtivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "status")
    private String status;
    @Column(name = "horainit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horainit;
    @Column(name = "leito")
    private String leito;
    @Column(name = "idQuarto")
    @Id
    private String idQuarto;

    public ChamadoAtivo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getHorainit() {
        return horainit;
    }

    public void setHorainit(Date horainit) {
        this.horainit = horainit;
    }

    public String getLeito() {
        return leito;
    }

    public void setLeito(String leito) {
        this.leito = leito;
    }

    public String getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(String idQuarto) {
        this.idQuarto = idQuarto;
    }
    
}
