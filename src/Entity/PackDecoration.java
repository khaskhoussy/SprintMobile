/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.codename1.io.Externalizable;
import com.codename1.io.Util;
import controller.PackDecorationController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dreamer
 */
@Entity
@Table(name = "pack_decoration")
@NamedQueries({
    @NamedQuery(name = "PackDecoration.findAll", query = "SELECT p FROM PackDecoration p")})
public class PackDecoration implements Serializable,Externalizable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_pack")
    private String nomPack;
    @Basic(optional = false)
    @Column(name = "typeP")
    private String typeP;
    @Basic(optional = false)
    @Column(name = "description_pack")
    private String descriptionPack;
    @Basic(optional = false)
    @Column(name = "prixP")
    private double prixP;
    @Basic(optional = false)
    @Column(name = "image_pack")
    private String imagePack;
    @OneToMany(mappedBy = "idPack")
    private Collection<LignePack> lignePackCollection;

    public PackDecoration() {
    }

    public PackDecoration(Integer id) {
        this.id = id;
    }

    public PackDecoration(Integer id, String nomPack, String typeP, String descriptionPack, double prixP, String imagePack) {
        this.id = id;
        this.nomPack = nomPack;
        this.typeP = typeP;
        this.descriptionPack = descriptionPack;
        this.prixP = prixP;
        this.imagePack = imagePack;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomPack() {
        return nomPack;
    }

    public void setNomPack(String nomPack) {
        this.nomPack = nomPack;
    }

    public String getTypeP() {
        return typeP;
    }

    public void setTypeP(String typeP) {
        this.typeP = typeP;
    }

    public String getDescriptionPack() {
        return descriptionPack;
    }

    public void setDescriptionPack(String descriptionPack) {
        this.descriptionPack = descriptionPack;
    }

    public double getPrixP() {
        return prixP;
    }

    public void setPrixP(double prixP) {
        this.prixP = prixP;
    }

    public String getImagePack() {
        return imagePack;
    }

    public void setImagePack(String imagePack) {
        this.imagePack = imagePack;
    }

    public Collection<LignePack> getLignePackCollection() {
        return lignePackCollection;
    }

    public void setLignePackCollection(Collection<LignePack> lignePackCollection) {
        this.lignePackCollection = lignePackCollection;
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
        if (!(object instanceof PackDecoration)) {
            return false;
        }
        PackDecoration other = (PackDecoration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PackDecoration[ id=" + id + " ]";
    }

    @Override
    public int getVersion() {
        return 1 ;
    }

    @Override
    public void externalize(DataOutputStream out) throws IOException {
        Util.writeObject(this.id, out);
        Util.writeObject(this.nomPack, out);
        Util.writeObject(this.descriptionPack, out);
        Util.writeObject(this.prixP, out);
        Util.writeObject(this.imagePack, out);
    }

    @Override
    public void internalize(int version, DataInputStream in) throws IOException {
        this.id = (Integer) Util.readObject(in);
        this.nomPack = (String) Util.readObject(in);
        this.descriptionPack = (String) Util.readObject(in);
        this.prixP = (double) Util.readObject(in);
        this.imagePack = (String) Util.readObject(in);
    }

    @Override
    public String getObjectId() {
        return "PackDecoration";
    }
    
}