/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entity.Commande;
import Entity.LigneCommande;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import controller.CommandeController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abdelli
 */
public class CommandeService {

        public static List<Commande> getAll() {
        List<Commande> commandes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/",false);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                
                for (Map<String, Object> obj : list) {
                    Commande commande = new Commande();
                    commande.setId((int)Float.parseFloat(obj.get("id").toString()));
                                        User user = new User();
                                        commande.setIdUser(user);
                                        System.err.println(user);
                                        System.err.println(list);
                    //commande.setIdProduit(produit);
                    Map<String, Object> userJson = (Map<String, Object>) obj.get("iduser");
                    System.err.println(list);
                    //pack.setNomPack(obj.get("nomPack").toString());
                    commande.setDateValidation(obj.get("dateValidation").toString());
                    commande.getIdUser().getEmail();
                    //user.setEmail(userJson.get("email").toString());
                    //pack.setDescriptionPack(obj.get("descriptionPack").toString());
                    //pack.setPrixP(Float.parseFloat(obj.get("prixp").toString()));
                    commande.setPrixTotal((int) Float.parseFloat(obj.get("prixTotal").toString()));
//                    pack.setTypeP(obj.get("typep").toString());
//                    pack.setImagePack(obj.get("imagePack").toString());
                    commandes.add(commande);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return commandes;
    }
        
     public void supprimerCommande(Commande cmd,Resources res) 
        {
//        ConnectionRequest con = new ConnectionRequest();
//         String url = "http://localhost/SprintMobileAPI/web/app_dev.php/front/vente/" + cmd.getId();
//         ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/",false);
ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/"+ CommandeController.id +"/showmobile2",false);
//            con.setUrl(url);
             con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Produit supprimé", "ok", null);

        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        }
       
}
