/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Evenement;
import Entity.EventComments;
import Entity.ListeTravail;
import Entity.User;
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
import java.util.ArrayList;
import java.util.List;
import service.MesServicesService;

/**
 *
 * @author Dreamer
 */
public class ListeJardinierController extends Controller {

    private Container packsContainer,parentContainer,detailContainer ;
    private List<User> user ;
    
    @Override
    public void initialize() {
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
    
    

    private void initData(){
        MesServicesService sp =new MesServicesService();
        ArrayList<User> lis=sp.getListJardinier();
        for(User p : lis) {
            packsContainer.add(packItem(p));
        }    
        packsContainer.revalidate();
    }
    
    
    
    private Container packItem(User user){

            

        Container parent = new Container(BoxLayout.x());
        parent.getUnselectedStyle().setMarginBottom(5);
        ImageViewer imageViewer = new ImageViewer(getImageFromURL(user.getImage()));
        parent.add(imageViewer);
        Container child = new Container(BoxLayout.y());
        parent.add(child);
        Container subChild = new Container(BoxLayout.x());
        Label id = new Label(""+user.getId());
        Label nom = new Label("Nom: "+user.getNom());
        subChild.add(nom);
        child.add(subChild);
        Button detail = new Button("Reserver->");
        detail.addActionListener(new ActionListener() {
            @Override
        
            public void actionPerformed(ActionEvent evt) {
                ReserverController profilController = new ReserverController();
                rootContainer.removeAll();
                User.focusedId=user.getId();
                profilController.initialize();
                rootContainer.addComponent(BorderLayout.CENTER, profilController.getView());
                rootContainer.revalidate();
                 
            }
             }
             );
        child.add(detail);
        
        return parent ;
    }
    
    
    public static Image getImageFromURL(String url)
    {
        try {
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(Controller.ip+"/SprintMobileAPI/web/uploads/Users/"+url);
            NetworkManager.getInstance().addToQueueAndWait(request);
            Image image = Image.createImage(new ByteArrayInputStream(request.getResponseData()));
            return image.scaled(200, 200);
        } catch (IOException ex) {
        }
        
        return null;
    }
    
}
