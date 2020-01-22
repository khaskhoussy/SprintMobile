/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.LigneCommande;
import Entity.LigneService;
import Entity.User;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import service.PanierService;
import service.UserService;

/**
 *
 * @author abdelli
 */
public class PanierController extends Controller {
public static int id;
    private Container commandesContainer ;
    private Container lcommandesContainer ;
    private Container panierserviceContainer ;
    private Container lpanierserviceContainer ;
    private Container totalContainer;
    @Override
    public void initialize() {
        
        totalContainer = new Container(BoxLayout.y());
           UserService u = new UserService();
           User u1= u.getProfilUser();
  

        totalContainer = new Container(BoxLayout.y());
        totalContainer.add(new Label(u1.getNom()+", voici votre panier ! :")); 
        
        Label a = new Label();
        a.getUnselectedStyle().setMarginBottom(200);
        
                commandesContainer = new Container(BoxLayout.y());
                lcommandesContainer = new Container(BoxLayout.y());
                
                lcommandesContainer.setScrollableX(true);
                commandesContainer.setScrollableX(true);
                
                
                
                for(LigneCommande commande : PanierService.getLignesCommande()) {
                Container lc  = new Container(BoxLayout.x());     
                lc.add(new Label(commande.getIdProduit().getNomProd()+"              "+commande.getQte()));
            lcommandesContainer.add(lc);
                        
            
            
    
        }
        
                
                
                
                
                
                        
                panierserviceContainer = new Container(BoxLayout.y());
                lpanierserviceContainer = new Container(BoxLayout.y());
                panierserviceContainer.setScrollableX(true);
                lpanierserviceContainer.setScrollableX(true);
                
                
                
                
                for(LigneService service : PanierService.getLignesService()) {
                Container ls  = new Container(BoxLayout.x());     
                ls.add(new Label(service.getDateDebut()+"      "+service.getDateFin()));
                lpanierserviceContainer.add(ls);
                        
            
            
    
        }
                
          totalContainer.add(new Label("Nom Produit    Quantite"));      
          totalContainer.add(lcommandesContainer);      
          totalContainer.add(new Label("Date de Debut                                          Date de Fin"));
          totalContainer.add(lpanierserviceContainer);   
          
                
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,totalContainer);
        this.rootContainer.revalidate();
        
        
        
        
        
        
        
        
        Button valider  = new Button("Valider");
 valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String r = PanierService.validerPanier();
                if(!r.equals("OK"))
                    //Dialog.show("validation panier", "ok");
                //Dialog.show("validation panier", r);
                        Dialog.show("Impossible de valider ce panier",r,"close",null);
                else
                {
                        Dialog.show("Validation effectué avec succées","Commande Enregistrée!","close",null);
                    
                    totalContainer.removeAll();
                    totalContainer.revalidate();
                }
                    
            }
        });
 
 
         Button vider  = new Button("Vider");
 vider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PanierService.viderPanier();

                        Dialog.show("Votre panier est vidé","Panier Vidé !","close",null);
                    
                    totalContainer.removeAll();
                    totalContainer.revalidate();
                }
                    
            
        });
 
 
         totalContainer.add(vider); 
 totalContainer.add(valider);  
        
    }
    
}
