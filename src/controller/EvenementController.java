/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Evenement;
import Entity.EventComments;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import service.EvenementService;

/**
 *
 * @author Dreamer
 */
public class EvenementController extends Controller {

    private Container packsContainer,parentContainer,detailContainer ;
    private List<Evenement> events ;
    private DateFormat dateFormat ;
    @Override
    public void initialize() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        parentContainer = new Container(BoxLayout.y());
        packsContainer = new Container(BoxLayout.y());
        packsContainer.setScrollableY(true);
        initData();
        
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
    
    
    private void initDetailContainer(Evenement event ) {
        detailContainer = new Container(BoxLayout.y());
        Container listProduitContainer = new Container(BoxLayout.y());
        listProduitContainer.setScrollableY(true);
        Button btn = new Button("Liste des evenements");
        btn.addActionListener(this::retourList);
        detailContainer.add(BoxLayout.encloseX(btn));
        detailContainer.add(BorderLayout.centerAbsolute(new Label(getImageFromURL(event.getImage()))));
        detailContainer.add(BoxLayout.encloseX(new Label("Nom :"),new Label(event.getNom())));
        Label description = new Label(event.getDescription());
        detailContainer.add(BoxLayout.encloseX(new Label("Description :")));
        detailContainer.add(BoxLayout.encloseX(description));
        detailContainer.add(BoxLayout.encloseX(new Label("Prix :"),new Label(String.valueOf(event.getEveprix()))));
        detailContainer.add(BoxLayout.encloseX(new Label("Date debut :"),new Label(String.valueOf(event.getDatedebut().substring(0, 9)))));
        detailContainer.add(BoxLayout.encloseX(new Label("Date fin :"),new Label(String.valueOf(event.getDatefin().substring(0, 9)))));
        detailContainer.add(new Label("Liste des commentaires :"));
        detailContainer.add(BorderLayout.centerAbsolute(listProduitContainer));
        
        for(EventComments eventComments : event.getEventCommentsCollection()) {
            
            listProduitContainer.add(BoxLayout.encloseX(BoxLayout.encloseY(
                    new Label(eventComments.getContenu()),
                    new Label(dateFormat.format(eventComments.getDate()))
            )));
            
            listProduitContainer.revalidate();
            
        }
        
        Button participer = new Button("Participer");
        participer.setEnabled(EvenementService.checkParticipation(event.getId()));
        participer.addActionListener((ActionEvent evt) -> {
           if(EvenementService.participate(event.getId())) participer.setEnabled(false);
        });
        detailContainer.add(participer);
        
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,detailContainer);
        this.rootContainer.revalidate();
    }
    
    private void initData(){
        events = EvenementService.findAll();
        packsContainer.removeAll();
        for(Evenement event : events) {
            packsContainer.add(packItem(event));
        }    
        packsContainer.revalidate();
    }
    
    
    
    private Container packItem(Evenement event){
        Container parent = new Container(BoxLayout.x());
        parent.getUnselectedStyle().setMarginBottom(5);
        Button imageViewer = new Button(getImageFromURL(event.getImage()));
        imageViewer.addActionListener((ActionEvent evt) -> {
            initDetailContainer(event);
        });
        parent.add(imageViewer);
        Container child = new Container(BoxLayout.y());
        parent.add(child);
        Container subChild = new Container(BoxLayout.x());
        Label nom = new Label(event.getNom());
        Label prix = new Label(String.valueOf(event.getEveprix()));
        subChild.add(nom);
        subChild.add(prix);
        child.add(subChild);
        Label description = new Label(event.getDescription());
        child.add(description);
        child.add(new Label(event.getDatedebut().substring(0,9)+" -> "+event.getDatefin().substring(0,9)));
        return parent ;
    }
    
    
    public static Image getImageFromURL(String url)
    {
        try {
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(Controller.ip+"/SprintMobileAPI/web/uploads/evenment/"+url);
            NetworkManager.getInstance().addToQueueAndWait(request);
            Image image = Image.createImage(new ByteArrayInputStream(request.getResponseData()));
            return image.scaled(64, 64);
        } catch (IOException ex) {
        }
        
        return null;
    }
    
}