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
@Table(name = "porta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porta.findAll", query = "SELECT p FROM Porta p")
    , @NamedQuery(name = "Porta.findByQuarto", query = "SELECT p FROM Porta p WHERE p.quarto = :quarto")
    , @NamedQuery(name = "Porta.findByQteControle", query = "SELECT p FROM Porta p WHERE p.qteControle = :qteControle")})
public class Porta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "quarto")
    private String quarto;
    @Column(name = "qte_controle")
    private Integer qteControle;
    @OneToMany(mappedBy = "idQuarto")
    private Collection<Mac> macCollection;

    public Porta() {
    }

    public Porta(String quarto) {
        this.quarto = quarto;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public Integer getQteControle() {
        return qteControle;
    }

    public void setQteControle(Integer qteControle) {
        this.qteControle = qteControle;
    }

    @XmlTransient
    public Collection<Mac> getMacCollection() {
        return macCollection;
    }

    public void setMacCollection(Collection<Mac> macCollection) {
        this.macCollection = macCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quarto != null ? quarto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porta)) {
            return false;
        }
        Porta other = (Porta) object;
        if ((this.quarto == null && other.quarto != null) || (this.quarto != null && !this.quarto.equals(other.quarto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.helpcall.model.Porta[ quarto=" + quarto + " ]";
    }
    
}
