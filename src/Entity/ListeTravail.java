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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dreamer
 */
@Entity
@Table(name = "liste_travail")
@NamedQueries({
    @NamedQuery(name = "ListeTravail.findAll", query = "SELECT l FROM ListeTravail l")})
public class ListeTravail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "description_travail")
    private String descriptionTravail;
    @Basic(optional = false)
    @Column(name = "date_travail")
    private String dateTravail;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "IdUser", referencedColumnName = "id")
    @ManyToOne
    private User idUser;

    public ListeTravail() {
    }

    public ListeTravail(Integer id) {
        this.id = id;
    }

    public ListeTravail(Integer id, String descriptionTravail, String dateTravail, String image) {
        this.id = id;
        this.descriptionTravail = descriptionTravail;
        this.dateTravail = dateTravail;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescriptionTravail() {
        return descriptionTravail;
    }

    public void setDescriptionTravail(String descriptionTravail) {
        this.descriptionTravail = descriptionTravail;
    }

    public String getDateTravail() {
        return dateTravail;
    }

    public void setDateTravail(String dateTravail) {
        this.dateTravail = dateTravail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof ListeTravail)) {
            return false;
        }
        ListeTravail other = (ListeTravail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ListeTravail[ id=" + id + " ]";
    }
    
}
