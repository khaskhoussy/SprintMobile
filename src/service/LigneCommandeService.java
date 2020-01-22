/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Commande;
import Entity.LigneCommande;
import Entity.LigneService;
import Entity.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.main.Controller;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import controller.CommandeController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abdelli
 */
public class LigneCommandeService {
    
    
    public static List<LigneCommande> getAll1() {
        List<LigneCommande> lcommandes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/"+ CommandeController.id +"/showmobile",false);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                
                for (Map<String, Object> obj : list) {
                    System.err.println("LISTE LIGNE COMMANDE"+list);
                    LigneCommande lcommande = new LigneCommande();
                    lcommande.setId((int) Float.parseFloat(obj.get("id").toString()));
                    Produit produit = new Produit();
                    lcommande.setIdProduit(produit);
                    Map<String, Object> produitJson = (Map<String, Object>) obj.get("idproduit");
                    produit.setNomProd(produitJson.get("nomProd").toString());
                    produit.setPrixProd((double) produitJson.get("prixProd"));
                    //produit.setPrixProd((int) Float.parseFloat(obj.get("prixProd").toString()));
                    lcommande.setQte((int) Float.parseFloat(obj.get("qte").toString()));
                    
                    //commande.setId((int)Float.parseFloat(obj.get("id").toString()));
                    //pack.setNomPack(obj.get("nomPack").toString());
                    //commande.setDateValidation(obj.get("dateValidation").toString());
                    //pack.setDescriptionPack(obj.get("descriptionPack").toString());
                    //pack.setPrixP(Float.parseFloat(obj.get("prixp").toString()));
                    //commande.setPrixTotal((int) Float.parseFloat(obj.get("prixTotal").toString()));
//                      lcommande.setQte((int) Float.parseFloat(obj.get("qte").toString()));
//                    pack.setTypeP(obj.get("typep").toString());
//                    pack.setImagePack(obj.get("imagePack").toString());
                    lcommandes.add(lcommande);
                }
            } catch (IOException ex) {
                     System.err.println("LISTE LIGNE COMMANDE");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lcommandes;
    }
    
    public static List<LigneService> getAll2() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<LigneService> lservices = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/"+ CommandeController.id +"/showmobile2",false);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                
                for (Map<String, Object> obj : list) {
                    System.err.println("LISTE LIGNE SERVICE"+list);
                    LigneService lservice = new LigneService();
                    lservice.setId((int) Float.parseFloat(obj.get("id").toString()));     
                    try {
                        lservice.setDateDebut(df.parse(obj.get("datedebut").toString().substring(0,10)));
                        lservice.setDateFin(df.parse(obj.get("datefin").toString().substring(0,10)));
                    } catch (ParseException ex) {

                    }

                    //commande.setId((int)Float.parseFloat(obj.get("id").toString()));
                    //pack.setNomPack(obj.get("nomPack").toString());
                    //commande.setDateValidation(obj.get("dateValidation").toString());
                    //pack.setDescriptionPack(obj.get("descriptionPack").toString());
                    //pack.setPrixP(Float.parseFloat(obj.get("prixp").toString()));
                    //commande.setPrixTotal((int) Float.parseFloat(obj.get("prixTotal").toString()));
                      //lservice.setQte((int) Float.parseFloat(obj.get("qte").toString()));
//                    pack.setTypeP(obj.get("typep").toString());
//                    pack.setImagePack(obj.get("imagePack").toString());
                    lservices.add(lservice);
                }
            } catch (IOException ex) {
                     System.err.println("catch me if you can");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lservices;
    }
    
    
    
        /* public void supprimerCommande(Commande cmd,Resources res) 
        {
        ConnectionRequest con = new ConnectionRequest();
        //ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/"+ CommandeController.id +"/showmobile2",false);
         //String url = "http://localhost/pipdev/web/app_dev.php/supprimerproduit/" + ta.getId();
         //String url = "/SprintMobileAPI/web/app_dev.php/front/vente/"+ CommandeController.id +"/delete2";
         String url = "/SprintMobileAPI/web/app_dev.php/front/vente/"+ cmd.getId() +"/delete2";
            con.setUrl(url);
             con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Commande supprimé", "ok", null);

        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        }*/
    
    
    
}
