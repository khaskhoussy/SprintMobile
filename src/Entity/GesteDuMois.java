/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dreamer
 */
@Entity
@Table(name = "geste_du_mois")
@NamedQueries({
    @NamedQuery(name = "GesteDuMois.findAll", query = "SELECT g FROM GesteDuMois g")})
public class GesteDuMois implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_geste")
    private String nomGeste;
    @Basic(optional = false)
    @Column(name = "mois_geste")
    private String moisGeste;
    @Basic(optional = false)
    @Lob
    @Column(name = "desc_geste")
    private String descGeste;
    @Basic(optional = false)
    @Column(name = "image_geste")
    private String imageGeste;

    public GesteDuMois() {
    }

    public GesteDuMois(Integer id) {
        this.id = id;
    }

    public GesteDuMois(Integer id, String nomGeste, String moisGeste, String descGeste, String imageGeste) {
        this.id = id;
        this.nomGeste = nomGeste;
        this.moisGeste = moisGeste;
        this.descGeste = descGeste;
        this.imageGeste = imageGeste;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomGeste() {
        return nomGeste;
    }

    public void setNomGeste(String nomGeste) {
        this.nomGeste = nomGeste;
    }

    public String getMoisGeste() {
        return moisGeste;
    }

    public void setMoisGeste(String moisGeste) {
        this.moisGeste = moisGeste;
    }

    public String getDescGeste() {
        return descGeste;
    }

    public void setDescGeste(String descGeste) {
        this.descGeste = descGeste;
    }

    public String getImageGeste() {
        return imageGeste;
    }

    public void setImageGeste(String imageGeste) {
        this.imageGeste = imageGeste;
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
        if (!(object instanceof GesteDuMois)) {
            return false;
        }
        GesteDuMois other = (GesteDuMois) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.GesteDuMois[ id=" + id + " ]";
    }
    
}
