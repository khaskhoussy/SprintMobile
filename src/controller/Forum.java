/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Sujet;
import Entity.User;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.main.BaseForm;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import service.ForumService;
import service.MesServicesService;

/**
 *
 * @author hp
 */
public class Forum extends Controller {

 private Container packsContainer,parentContainer,detailContainer ;
    private List<Sujet> sujet ;

    @Override
    public void initialize() {

     parentContainer = new Container(BoxLayout.y());
        packsContainer = new Container(BoxLayout.y());
        packsContainer.setScrollableY(true);
        initData();
        ImageViewer image =new ImageViewer(theme.getImage("7a94e4432299cf7ecde0b5f14d7f7030ee347cd9.png").scaled(400, 250));
        Label Sujet = new Label("Liste des sujets: ");
        parentContainer.add(image);
        parentContainer.add(Sujet);
        parentContainer.add(packsContainer);
        
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,parentContainer);
        this.rootContainer.revalidate();
    }
    
    private void retourList(ActionEvent evt) {
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,parentContainer);
        this.rootContainer.revalidate();
    }
    
    
    
    private void initData(){
        ForumService sp =new ForumService();
        ArrayList<Sujet> lis=sp.getList();
        for(Sujet p : lis) {
            packsContainer.add(packItem(p));
        }    
        packsContainer.revalidate();
    }
    
    
    
    private Container packItem(Sujet sujet){

            

        Container parent = new Container(BoxLayout.y());
        parent.getUnselectedStyle().setMarginBottom(5);

        Container child = new Container(BoxLayout.x());
        parent.add(child);
        Container subChild = new Container(BoxLayout.y());
        Label NomSujet = new Label("Nom Sujet: "+sujet.getNomsujet());
        Label Categorie = new Label("Categorie: "+sujet.getCategorie());
        Label Description = new Label("Description: "+sujet.getDescription());
        Label DatePub = new Label("Date Publication: "+sujet.getDatePub());
        Label l = new Label("_________________________________________________________________________________________");

        
        subChild.add(l);
        subChild.add(NomSujet);
        subChild.add(Categorie);
        subChild.add(Description);
        subChild.add(DatePub);        

               


        child.add(subChild);
        /*Button detail = new Button("Reserver->");
        detail.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rootContainer.removeAll();
                ReserverPaysaController profilController = new ReserverPaysaController();
                User.focusedId=user.getId();
                profilController.initialize();
                rootContainer.addComponent(BorderLayout.CENTER, profilController.getView());
                rootContainer.revalidate();
            }
        });
        child.add(detail);*/
        
        return parent ;
    }
    
}