/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.LigneCommande;
import com.codename1.main.Controller;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import service.LigneCommandeService;

/**
 *
 * @author abdelli
 */
public class LigneCommandeController extends Controller {
       private Container lcommandesContainer ;
    //private ComboBox categorie ;
    
    @Override
    public void initialize() {
        lcommandesContainer = new Container(BoxLayout.y());
        for(LigneCommande lignecommande : LigneCommandeService.getAll1()) {
            lcommandesContainer.add(packItem1(lignecommande));
        }
        
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,lcommandesContainer);
        this.rootContainer.revalidate();
        

    }    
    
        /*private void initCategorie() {
        categorie = new ComboBox();
    }*/
       
        private Container packItem1(LigneCommande lignecommande){
            
        Container parent = new Container(BoxLayout.x());
        //parent.add(getImageFromURL(commande.getImagePack()));
        Container child = new Container(BoxLayout.y());
        parent.add(child);
        Container subChild = new Container(BoxLayout.x());
        //Label date = new Label(commande.getDateValidation());
        Label qte = new Label(String.valueOf(lignecommande.getQte()));
        subChild.add(qte);
        //subChild.add(prix);
        child.add(subChild);
        //Label description = new Label(commande.getDescriptionPack());
        //child.add(description);
        return parent ;
    }
    
    
    
}
