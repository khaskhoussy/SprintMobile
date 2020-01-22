/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dreamer
 */
@Entity
@Table(name = "evenement")
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e")})
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "datedebut")
    private String datedebut;
    @Basic(optional = false)
    @Column(name = "datefin")
    private String datefin;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "lieu")
    private String lieu;
    @Basic(optional = false)
    @Column(name = "nbr_participants")
    private int nbrParticipants;
    @Basic(optional = false)
    @Column(name = "eveprix")
    private int eveprix;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_offre")
    private Double prixOffre;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "idEvenement")
    private Collection<Participation> participationCollection;
    @OneToMany(mappedBy = "idEvenement")
    private Collection<EventComments> eventCommentsCollection;
    @JoinColumn(name = "IdOffre", referencedColumnName = "id")
    @ManyToOne
    private OffrePromotion idOffre;

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(Integer id, String nom, String datedebut, String datefin, String description, String lieu, int nbrParticipants, int eveprix, String image) {
        this.id = id;
        this.nom = nom;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.lieu = lieu;
        this.nbrParticipants = nbrParticipants;
        this.eveprix = eveprix;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public int getEveprix() {
        return eveprix;
    }

    public void setEveprix(int eveprix) {
        this.eveprix = eveprix;
    }

    public Double getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(Double prixOffre) {
        this.prixOffre = prixOffre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }

    public Collection<EventComments> getEventCommentsCollection() {
        return eventCommentsCollection;
    }

    public void setEventCommentsCollection(Collection<EventComments> eventCommentsCollection) {
        this.eventCommentsCollection = eventCommentsCollection;
    }

    public OffrePromotion getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(OffrePromotion idOffre) {
        this.idOffre = idOffre;
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
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Evenement[ id=" + id + " ]";
    }
    
}
