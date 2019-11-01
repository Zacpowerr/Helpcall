/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "quarto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quarto.findAll", query = "SELECT q FROM Quarto q")
    , @NamedQuery(name = "Quarto.findById", query = "SELECT q FROM Quarto q WHERE q.id = :id")
    , @NamedQuery(name = "Quarto.findByQuarto", query = "SELECT q FROM Quarto q WHERE q.quarto = :quarto")
    , @NamedQuery(name = "Quarto.findByLimiteControle", query = "SELECT q FROM Quarto q WHERE q.limiteControle = :limiteControle")})
public class Quarto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "quarto")
    private String quarto;
    @Column(name = "limite_controle")
    private Integer limiteControle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quartoId")
    private Collection<Mac> macCollection;

    public Quarto() {
    }

    public Quarto(Long id, String quarto) {
        this.id = id;
        this.quarto = quarto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public Integer getLimiteControle() {
        return limiteControle;
    }

    public void setLimiteControle(Integer limiteControle) {
        this.limiteControle = limiteControle;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quarto)) {
            return false;
        }
        Quarto other = (Quarto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.helpcall.model.Quarto[ id=" + id + " ]";
    }
    
}
