/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.LigneCommande;
import Entity.LigneService;
import Entity.ObjetPack;
import Entity.PackDecoration;
import Entity.Produit;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.main.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cherif
 */
public class PanierService {
    
    public static List<LigneCommande> getLignesCommande() {
        
        List<LigneCommande> ligneCommandes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/panier/get",false);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("ligneCommandes");
                
                for (Map<String, Object> obj : list) {
                    LigneCommande ligneCommande = new LigneCommande();
                    ligneCommande.setQte((int) Float.parseFloat(obj.get("qte").toString()));
                    Produit produit = new Produit();
                    ligneCommande.setIdProduit(produit);
                    Map<String, Object> produitMap = (Map<String, Object>) obj.get("idproduit");
                    produit.setNomProd(produitMap.get("nomProd").toString());
                    produit.setProdDescription(produitMap.get("prodDescription").toString());
                    produit.setPrixProd(Double.parseDouble(produitMap.get("prixProd").toString()));
                    ligneCommandes.add(ligneCommande);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ligneCommandes ;
    }
    
    public static List<LigneService> getLignesService() {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<LigneService> ligneServices = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/panier/get",false);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                
 
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("ligneServices");
                
                for (Map<String, Object> obj : list) {
                    LigneService ligneService = new LigneService();
                    ligneService.setDateDebut(df.parse(obj.get("datedebut").toString().substring(0, 10)));
                    ligneService.setDateFin(df.parse(obj.get("datefin").toString().substring(0, 10)));
                    User user = new User();
                    ligneService.setIdUser(user);
                    Map<String, Object> userMap = (Map<String, Object>) obj.get("iduser");
                    user.setNom(userMap.get("nom").toString());
                    user.setPrenom(userMap.get("prenom").toString());
                    user.setEmail(userMap.get("email").toString());
                    user.setTelephone(userMap.get("telephone").toString());
                    ligneServices.add(ligneService);
                }
            } catch (IOException ex) {
            } catch (ParseException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ligneServices ;
    }
    
    public static List<ObjetPack> getObjetPacks() {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<ObjetPack> objetPacks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/panier/get",false);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                System.out.println(new String(con.getResponseData()));
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("packs");
                
                for (Map<String, Object> obj : list) {
                    ObjetPack objetPack = new ObjetPack();
                    objetPack.setQte((int) Float.parseFloat(obj.get("qte").toString()));
                    PackDecoration packDecoration = new PackDecoration();
                    objetPack.setPackDecoration(packDecoration);
                    Map<String, Object> packMap = (Map<String, Object>) obj.get("pack");
                    packDecoration.setNomPack(packMap.get("nomPack").toString());
                    packDecoration.setDescriptionPack(packMap.get("descriptionPack").toString());
                    packDecoration.setPrixP(Double.parseDouble(packMap.get("prixp").toString()));
                    objetPacks.add(objetPack);
                }
            } catch (IOException ex) {
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return objetPacks ;
    }
    
    public static String validerPanier() {
        
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/panier/validerpanierr",true);
        con.addArgument("valider", "valider");
        NetworkManager.getInstance().addToQueueAndWait(con);
        return new String(con.getResponseData()) ;
    }
    
    public static void viderPanier() {
        
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/panier/viderpanier",true);
        con.addArgument("vider", "vider");
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public static void ajouterProduit(int idProduit,int qte) {
        
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/panier/addproduct",true);
        con.addArgument("addproduit", "addproduit");
        con.addArgument("idproduit", String.valueOf(idProduit));
        con.addArgument("qte", String.valueOf(qte));
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public static void ajouterPack(int idPack,int qte) {
        
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/panier/addpack",true);
        con.addArgument("addpack", "addpack");
        con.addArgument("idpack", String.valueOf(idPack));
        con.addArgument("qte", String.valueOf(qte));
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public static void reserver(int idUser,Date dateDebut,Date dateFin) {
        
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/reserverJardinier/"+idUser,true);
        con.addArgument("dateDebut", String.valueOf(dateDebut.toString()));
        con.addArgument("dateFin", String.valueOf(dateFin.toString()));
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}
