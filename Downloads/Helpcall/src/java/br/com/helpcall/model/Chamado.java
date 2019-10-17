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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Eduardo
 */
@Entity
@Table(name = "chamado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chamado.findAll", query = "SELECT c FROM Chamado c")
    , @NamedQuery(name = "Chamado.findByIdChamado", query = "SELECT c FROM Chamado c WHERE c.idChamado = :idChamado")
    , @NamedQuery(name = "Chamado.findByStatus", query = "SELECT c FROM Chamado c WHERE c.status = :status")
    , @NamedQuery(name = "Chamado.findByHorainit", query = "SELECT c FROM Chamado c WHERE c.horainit = :horainit")
    , @NamedQuery(name = "Chamado.findByHoraend", query = "SELECT c FROM Chamado c WHERE c.horaend = :horaend")})
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idChamado")
    private Integer idChamado;
    @Column(name = "status")
    private String status;
    @Column(name = "horainit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horainit;
    @Column(name = "horaend")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaend;
    @JoinColumn(name = "MAC_idMAC", referencedColumnName = "macadress")
    @ManyToOne
    private Mac mACidMAC;

    public Chamado() {
    }

    public Chamado(Integer idChamado) {
        this.idChamado = idChamado;
    }

    public Integer getIdChamado() {
        return idChamado;
    }

    public void setIdChamado(Integer idChamado) {
        this.idChamado = idChamado;
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

    public Date getHoraend() {
        return horaend;
    }

    public void setHoraend(Date horaend) {
        this.horaend = horaend;
    }

    public Mac getMACidMAC() {
        return mACidMAC;
    }

    public void setMACidMAC(Mac mACidMAC) {
        this.mACidMAC = mACidMAC;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChamado != null ? idChamado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chamado)) {
            return false;
        }
        Chamado other = (Chamado) object;
        if ((this.idChamado == null && other.idChamado != null) || (this.idChamado != null && !this.idChamado.equals(other.idChamado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.helpcall.model.Chamado[ idChamado=" + idChamado + " ]";
    }

}
