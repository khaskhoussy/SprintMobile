/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import controller.HomeForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ServiceProduit {
    public ArrayList<Produit> parseListTaskJson(String json) {

        ArrayList<Produit> listProduits = new ArrayList<>();

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
            Map<String, Object> produits= j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) produits.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Produit p = new Produit();

                float id = Float.parseFloat(obj.get("id").toString());

                p.setId((int) id);
                p.setNomProd(obj.get("nomProd").toString());
                p.setProdDescription(obj.get("prodDescription").toString());
                p.setImage(obj.get("image").toString());
                float qauntite = Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int) qauntite);
                float nbjaimes = Float.parseFloat(obj.get("nbjaimes").toString());
                p.setNbjaimes((int) nbjaimes);
                float prixProd = Float.parseFloat(obj.get("quantite").toString());
                p.setPrixProd((double) prixProd);
                 float prixOffre = Float.parseFloat(obj.get("prixOffre").toString());
                p.setPrixOffre((double) prixOffre);
                
                System.out.println(p);
                
                listProduits.add(p);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listProduits);
        return listProduits;

    }
    
    
    ArrayList<Produit> listProduits = new ArrayList<>();
    
    public ArrayList<Produit> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/Produit/all");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                listProduits = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
    public void ajoutProd(Produit p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/produit/new"
                +"?nomProd=" + p.getNomProd()
             
                + "&prodDescription=" + p.getProdDescription()+ 
                "&prixProd=" + p.getPrixProd()+
               "&quantite=" + p.getQuantite();
               
               
               
                 ;
        
        System.out.println("L'URL est : : :" + Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public void ModifierFormation(Produit p){
            ConnectionRequest con = new ConnectionRequest();
            String Url ="http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/update/"+p.getId()+"/"+
            p.getNomProd()+"/"+p.getProdDescription()+"/"+p.getPrixProd()+"/"+ p.getQuantite();
            
            con.setUrl(Url);
        con.setPost(true);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
          NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public Produit getFormationById(int id){
            ServiceProduit is = new ServiceProduit();
            ArrayList<Produit> li =is.getList2();
            for(Produit i:li)
            {
                if(i.getId()==id)
                    return i;
            }
            return null;
        }



    ArrayList<Produit> listProduitss = new ArrayList<>();
    
    public ArrayList<Produit> getList3(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/Produit/promotions");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProduit ser = new ServiceProduit();
                listProduitss = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduitss;
    }
     public void supprimerProduit(Produit ta,Resources res) 
        {
        ConnectionRequest con = new ConnectionRequest();
         String url = "http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/supprimerproduit/" + ta.getId();
            con.setUrl(url);
             con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Produit supprimé", "ok", null);

        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        }
    public ArrayList<Produit> ChercherProduit(String d) {
        ArrayList<Produit> listProduit = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/chercher/" + d);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Produit p= new Produit();
                    
                float id = Float.parseFloat(obj.get("id").toString());

                p.setId((int) id);
                p.setNomProd(obj.get("nomProd").toString());
                p.setProdDescription(obj.get("prodDescription").toString());
                p.setImage(obj.get("image").toString());
                float qauntite = Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int) qauntite);
                float nbjaimes = Float.parseFloat(obj.get("nbjaimes").toString());
                p.setNbjaimes((int) nbjaimes);
                float prixProd = Float.parseFloat(obj.get("quantite").toString());
                p.setPrixProd((double) prixProd);
                 float prixOffre = Float.parseFloat(obj.get("prixOffre").toString());
                p.setPrixOffre((double) prixOffre);
                
                System.out.println(p);
                    listProduit.add(p);
                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduit;
    }
}
