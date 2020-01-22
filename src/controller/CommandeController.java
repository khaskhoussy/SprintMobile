/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Commande;
import Entity.LigneCommande;
import Entity.User;
import com.codename1.main.Controller;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import service.CommandeService;
import service.UserService;

/**
 *
 * @author abdelli
 */
public class CommandeController extends Controller {

public static int id;
public static Commande selectedCommande ;
    private Container commandesContainer ;
    private Container lcommandesContainer ;
    private Container titre;
    //private ComboBox categorie ;
    
    @Override
    public void initialize() {
        
        
         UserService u = new UserService();
 User u1= u.getProfilUser();
  

        commandesContainer = new Container(BoxLayout.y());
        commandesContainer.add(new Label(u1.getNom()+", voici vos commandes :")); 
        Label a = new Label();
        a.getUnselectedStyle().setMarginBottom(200);
        commandesContainer.add(a);
        

        commandesContainer.add(new Label("Les commandes")); 

                Label c = new Label();
        c.getUnselectedStyle().setMarginBottom(50);
        commandesContainer.add(c);
        
        
        lcommandesContainer = new Container(BoxLayout.x());
                commandesContainer.add(new Label("Date de Validation       Prix Total"));
        //commandesContainer.add(new Label("Prix Total"));
        for(Commande commande : CommandeService.getAll()) {
            //id  = commande.getId();
//            System.err.println(commande);
//            Label date = new Label(commande.getDateValidation());
        
//                    subChild.add(date);
            
            
            commandesContainer.add(packItem(commande));
                        
            
            
    
        }

        Label b = new Label();
        b.getUnselectedStyle().setMarginBottom(50);
        commandesContainer.add(b);
                        commandesContainer.add(new Label("Cliquez sur la date de la validation de la "));
                commandesContainer.add(new Label("commande pour consultez les détails")); 
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,commandesContainer);
        this.rootContainer.revalidate();
        

    }    
    
        /*private void initCategorie() {
        categorie = new ComboBox();
    }*/
    
        private Container packItem(Commande commande){
            
        Container parent = new Container(BoxLayout.x());
        //parent.add(getImageFromURL(commande.getImagePack()));
        Container child = new Container(BoxLayout.y());
        parent.add(child);
        Container subChild = new Container(BoxLayout.x());
        Label date = new Label(commande.getDateValidation());
        Label prix = new Label(String.valueOf(commande.getPrixTotal()));


            date.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               System.err.println(commande.getId());
                //System.err.println(id);
                rootContainer.removeAll();
               id   = commande.getId();
               selectedCommande = commande ;

                
                
                //NewController.show();
//                LigneCommandeController.initialize();
                //mainForm.addComponent(BorderLayout.CENTER, LigneCommandeController.getView());
                
//                mainForm.revalidate();
             //System.err.println("hff");
             NewController NewController = new NewController();
             NewController.initialize();
             rootContainer.addComponent(BorderLayout.CENTER,NewController.getView());
             rootContainer.revalidate();
            }
                
                
            
        }
        );
        
    
        subChild.add(date);
        subChild.add(prix);
        child.add(subChild);
        //Label description = new Label(commande.getDescriptionPack());
        //child.add(description);
        return parent ;
    }
        

}

