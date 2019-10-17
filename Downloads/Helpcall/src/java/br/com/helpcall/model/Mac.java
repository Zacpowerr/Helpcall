/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "mac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mac.findAll", query = "SELECT m FROM Mac m")
    , @NamedQuery(name = "Mac.findByMacadress", query = "SELECT m FROM Mac m WHERE m.macadress = :macadress")
    , @NamedQuery(name = "Mac.findByLeito", query = "SELECT m FROM Mac m WHERE m.leito = :leito")
    , @NamedQuery(name = "Mac.findByStatus", query = "SELECT m FROM Mac m WHERE m.status = :status")})
public class Mac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "macadress")
    private String macadress;
    @Column(name = "leito")
    private String leito;
    @Column(name = "status")
    private boolean status;
    @OneToMany(mappedBy = "mACidMAC")
    private Collection<Chamado> chamadoCollection;
    @JoinColumn(name = "idQuarto", referencedColumnName = "quarto")
    @ManyToOne
    private Porta idQuarto;

    public Mac() {
    }

    public Mac(String macadress) {
        this.macadress = macadress;
    }

    public String getMacadress() {
        return macadress;
    }

    public void setMacadress(String macadress) {
        this.macadress = macadress;
    }

    public String getLeito() {
        return leito;
    }

    public void setLeito(String leito) {
        this.leito = leito;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    @XmlTransient
    public Collection<Chamado> getChamadoCollection() {
        return chamadoCollection;
    }

    public void setChamadoCollection(Collection<Chamado> chamadoCollection) {
        this.chamadoCollection = chamadoCollection;
    }

    public Porta getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Porta idQuarto) {
        this.idQuarto = idQuarto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (macadress != null ? macadress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mac)) {
            return false;
        }
        Mac other = (Mac) object;
        if ((this.macadress == null && other.macadress != null) || (this.macadress != null && !this.macadress.equals(other.macadress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.helpcall.model.Mac[ macadress=" + macadress + " ]";
    }

}
