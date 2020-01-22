/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Categorie;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author DELL
 */
public class ServiceCategorie {
   public ArrayList<Categorie> parseListTaskJson(String json) {
       ArrayList<Categorie> listcategories=new ArrayList<>();
       try {
           
           JSONParser j=new JSONParser();
           Map<String, Object> categories = j.parseJSON(new CharArrayReader(json.toCharArray()));
             List<Map<String, Object>> list = (List<Map<String, Object>>) categories.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Categorie c = new Categorie();

                float id = Float.parseFloat(obj.get("id").toString());

                c.setId((int) id);
                c.setNomCat(obj.get("nomCat").toString());
                c.setDescriptionCat(obj.get("descriptionCat").toString());
                System.out.println(c);
                
                listcategories.add(c);

            }
       } catch (IOException ex) {
           
       }
       System.out.println(listcategories);
        return listcategories;
   }
   ArrayList<Categorie> listcategories = new ArrayList<>();
    
    public ArrayList<Categorie> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/categorie");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceCategorie ser = new ServiceCategorie();
                listcategories= ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcategories;
    }
    
}
