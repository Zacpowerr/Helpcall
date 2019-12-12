/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "chamado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chamado.findAll", query = "SELECT c FROM Chamado c")
    , @NamedQuery(name = "Chamado.findById", query = "SELECT c FROM Chamado c WHERE c.id = :id")
    , @NamedQuery(name = "Chamado.findByStatus", query = "SELECT c FROM Chamado c WHERE c.status = :status")
    , @NamedQuery(name = "Chamado.findByHoraInit", query = "SELECT c FROM Chamado c WHERE c.horaInit = :horaInit")
    , @NamedQuery(name = "Chamado.findByHoraEnd", query = "SELECT c FROM Chamado c WHERE c.horaEnd = :horaEnd")})
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;
    @Column(name = "hora_init")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInit;
    @Column(name = "hora_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEnd;
    @JoinColumn(name = "mac_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Mac macId;

    public Chamado() {
    }

    public Chamado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getHoraEnd() {
        return horaEnd;
    }

    public void setHoraEnd(Date horaEnd) {
        this.horaEnd = horaEnd;
    }

    public Mac getMacId() {
        return macId;
    }

    public void setMacId(Mac macId) {
        this.macId = macId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chamado)) {
            return false;
        }
        Chamado other = (Chamado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.helpcall.model.Chamado[ id=" + id + " ]";
    }

    public String dateFormatter(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return dateFormat.format(date);
    }
}
