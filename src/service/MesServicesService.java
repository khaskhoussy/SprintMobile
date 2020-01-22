/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.ListeTravail;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author hp
 */
public class MesServicesService { 
    
    
    
     public ArrayList<User> parseListTaskJson(String json) {

        ArrayList<User> users = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> usersx= j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) usersx.get("root1");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User p = new User();

                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setUsername(obj.get("username").toString());
                p.setNom(obj.get("nom").toString());
                p.setPrenom(obj.get("prenom").toString());
                p.setTelephone(obj.get("telephone").toString());
                p.setImage(obj.get("image").toString());
                p.setEmail(obj.get("email").toString());

                
                System.out.println(p);
                
                users.add(p);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(users);
        return users;

    }
    
    
    ArrayList<User> listJardinier = new ArrayList<>();
    
    public ArrayList<User> getListJardinier(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/showJardinier");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                MesServicesService ser = new MesServicesService();
                listJardinier = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJardinier;
    }
    ArrayList<User> listPaysagiste = new ArrayList<>();
    
    public ArrayList<User> getListPaysagiste(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/showPaysagiste");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                MesServicesService ser = new MesServicesService();
                listPaysagiste = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPaysagiste;
    }
    public User getDetailsById(int id){
            MesServicesService is = new MesServicesService();
            ArrayList<User> li =is.getListJardinier();
            for(User i:li)
            {
                if(i.getId()==id)
                    return i;
            }
            return null;
        }
    public User getDetailById(int id){
            MesServicesService is = new MesServicesService();
            ArrayList<User> li =is.getListPaysagiste();
            for(User i:li)
            {
                if(i.getId()==id)
                    return i;
            }
            return null;
        }

}