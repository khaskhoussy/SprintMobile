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
@Table(name = "ligne_pack")
@NamedQueries({
    @NamedQuery(name = "LignePack.findAll", query = "SELECT l FROM LignePack l")})
public class LignePack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantite_pack")
    private int quantitePack;
    @Basic(optional = false)
    @Column(name = "description_pack")
    private String descriptionPack;
    @JoinColumn(name = "IdProduit", referencedColumnName = "id")
    @ManyToOne
    private Produit idProduit;
    @JoinColumn(name = "IdPack", referencedColumnName = "id")
    @ManyToOne
    private PackDecoration idPack;

    public LignePack() {
    }

    public LignePack(Integer id) {
        this.id = id;
    }

    public LignePack(Integer id, int quantitePack, String descriptionPack) {
        this.id = id;
        this.quantitePack = quantitePack;
        this.descriptionPack = descriptionPack;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantitePack() {
        return quantitePack;
    }

    public void setQuantitePack(int quantitePack) {
        this.quantitePack = quantitePack;
    }

    public String getDescriptionPack() {
        return descriptionPack;
    }

    public void setDescriptionPack(String descriptionPack) {
        this.descriptionPack = descriptionPack;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public PackDecoration getIdPack() {
        return idPack;
    }

    public void setIdPack(PackDecoration idPack) {
        this.idPack = idPack;
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
        if (!(object instanceof LignePack)) {
            return false;
        }
        LignePack other = (LignePack) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.LignePack[ id=" + id + " ]";
    }
    
}
