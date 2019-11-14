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
 * @author Aluno
 */
@Entity
@Table(name = "mac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mac.findAll", query = "SELECT m FROM Mac m")
    , @NamedQuery(name = "Mac.findById", query = "SELECT m FROM Mac m WHERE m.id = :id")
    , @NamedQuery(name = "Mac.findByMacadress", query = "SELECT m FROM Mac m WHERE m.macadress = :macadress")
    , @NamedQuery(name = "Mac.findByLeito", query = "SELECT m FROM Mac m WHERE m.leito = :leito")
    , @NamedQuery(name = "Mac.findByStatus", query = "SELECT m FROM Mac m WHERE m.status = :status")})
public class Mac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "macadress", unique = true)
    private String macadress;
    @Column(name = "leito")
    private String leito;
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "macId")
    private Collection<Chamado> chamadoCollection;
    @JoinColumn(name = "quarto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Quarto quartoId;

    public Mac() {
    }

    public Mac(Integer id) {
        this.id = id;
    }

    public Mac(Integer id, String macadress) {
        this.id = id;
        this.macadress = macadress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Chamado> getChamadoCollection() {
        return chamadoCollection;
    }

    public void setChamadoCollection(Collection<Chamado> chamadoCollection) {
        this.chamadoCollection = chamadoCollection;
    }

    public Quarto getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(Quarto quartoId) {
        this.quartoId = quartoId;
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
        if (!(object instanceof Mac)) {
            return false;
        }
        Mac other = (Mac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.helpcall.model.Mac[ id=" + id + " ]";
    }
    
}
