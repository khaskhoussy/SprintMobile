/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author DELL
 */
public class Produit {
    private int id;
    private String nomProd ;
    private Double prixProd;
    private int quantite ;
    private String prodDescription;
    private String image;
    private Double prixOffre;
    private int idCategorie;
    private Categorie categorie;
    private int id_user;
    private int nbjaimes;
   public static int focusedId ;

    public Produit() {
    }

    public Produit(int id, String nomProd, Double prixProd, int quantite, String prodDescription, Double prixOffre, int idCategorie) {
        this.id = id;
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.prodDescription = prodDescription;
        this.prixOffre = prixOffre;
        this.idCategorie = idCategorie;
       
    }

    public Produit(String nomProd,String prodDescription, Double prixProd, int quantite) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.prodDescription = prodDescription;
        
    }

    public Produit(String nomProd, Double prixProd, int quantite) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
    }

    public Produit(String nomProd, Double prixProd, int quantite, int idCategorie) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.idCategorie = idCategorie;
    }                

    

    public Produit(String nomProd, Double prixProd, int quantite, String image) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.image = image;
        
    }

    public Produit(String nomProd,int quantite,String prodDescription, Double prixProd,Object categorie) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.prodDescription = prodDescription;
        this.image = image;
    }

    public static int getFocusedId() {
        return focusedId;
    }

    public static void setFocusedId(int focusedId) {
        Produit.focusedId = focusedId;
    }

    public Produit(int id, String nomProd,String prodDescription,int quantite, Double prixProd) {
        this.id = id;
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.quantite = quantite;
        this.prodDescription = prodDescription;
    }
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public Double getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(Double prixProd) {
        this.prixProd = prixProd;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public Double getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(Double prixOffre) {
        this.prixOffre = prixOffre;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nomProd=" + nomProd + ", prixProd=" + prixProd + ", quantite=" + quantite + ", descriptionProd=" + prodDescription  + '}';
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getNbjaimes() {
        return nbjaimes;
    }

    public void setNbjaimes(int nbjaimes) {
        this.nbjaimes = nbjaimes;
    }

   
    
    
}
