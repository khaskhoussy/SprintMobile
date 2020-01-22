/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.LignePack;
import Entity.PackDecoration;
import Entity.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dreamer
 */
public class PackDecorationService {
    
    public static List<PackDecoration> findAll(String cat) {
        List<PackDecoration> packs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/pack/",false);
        if(cat != null) con.addArgument("cat", cat);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                
                for (Map<String, Object> obj : list) {
                    PackDecoration pack = new PackDecoration();
                    Collection<LignePack> lignePacks = new ArrayList();
                    pack.setLignePackCollection(lignePacks);
                    pack.setId((int)Float.parseFloat(obj.get("id").toString()));
                    pack.setNomPack(obj.get("nomPack").toString());
                    pack.setDescriptionPack(obj.get("descriptionPack").toString());
                    pack.setPrixP(Float.parseFloat(obj.get("prixp").toString()));
                    pack.setTypeP(obj.get("typep").toString());
                    pack.setImagePack(obj.get("imagePack").toString());
                    
                    List<Map<String, Object>> lignePacksJson = (List<Map<String, Object>>) obj.get("lignePacks");
                    
                    for(Map<String, Object> lignePackJson : lignePacksJson) {
                        LignePack lignePack = new LignePack();
                        lignePack.setId((int)Float.parseFloat(lignePackJson.get("id").toString()));
                        lignePack.setQuantitePack((int)Float.parseFloat(lignePackJson.get("quantitePack").toString()));
                        lignePack.setIdPack(pack);
                        Produit produit = new Produit();
                        lignePack.setIdProduit(produit);
                        Map<String, Object> produitJson = (Map<String, Object>) lignePackJson.get("idproduit");
                        produit.setId((int)Float.parseFloat(produitJson.get("id").toString()));
                        produit.setNomProd(produitJson.get("nomProd").toString());
                        produit.setPrixProd(Double.parseDouble(produitJson.get("prixProd").toString()));
                        produit.setProdDescription(produitJson.get("prodDescription").toString());
                        produit.setImage(produitJson.get("image").toString());
                        lignePacks.add(lignePack);
                    }
                    
                    packs.add(pack);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return packs;
    }
    
}