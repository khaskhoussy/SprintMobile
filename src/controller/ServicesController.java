/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Produit;
import Entity.User;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.main.Controller;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import service.MesServicesService;
import service.UserService;


/**
 *
 * @author hp
 */
public class ServicesController extends Controller {
Form f ;
Image img;
Image img1;
    Label jardinier;
    Label paysagiste;
    Container contX;
    Container contY;
    ImageViewer iv1;
    ImageViewer iv2;
    ImageViewer iv3;
    ImageViewer iv4;
    ImageViewer iv5;
    ImageViewer iv6;
    ImageViewer iv7;
    ImageViewer iv8;
    @Override
    public void initialize() {
           
     contX = new Container(new BoxLayout(BoxLayout.X_AXIS));
        contY = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contY.setScrollableY(true);
        iv1 = new ImageViewer();
        img = theme.getImage("icone-dessin-anime-homme-jardinier_24908-8960.jpg");
        iv1.setImage(img.scaled(300, 300));
        iv2 = new ImageViewer();

        img1 = theme.getImage("paysagiste.png");
        iv2.setImage(img1.scaled(300, 300));
        

        jardinier = new Label("Jardinier");
        paysagiste = new Label("Paysagiste");
        contY.add(iv1);
        contY.add(jardinier);
        contY.add(iv2);
        contY.add(paysagiste);
        theme = UIManager.initFirstTheme("/theme");
        iv1.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rootContainer.removeAll();
                ListeJardinierController profilController = new ListeJardinierController();
                profilController.initialize();
                rootContainer.addComponent(BorderLayout.CENTER, profilController.getView());
                rootContainer.revalidate();
            }
        });
        iv2.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rootContainer.removeAll();
                ListePaysagisteController profilController = new ListePaysagisteController();
                profilController.initialize();
                rootContainer.addComponent(BorderLayout.CENTER, profilController.getView());
                rootContainer.revalidate();
            }
        });
        Container main = new Container(new BoxLayout(BoxLayout.X_AXIS));
        main.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Button b = new Button("Where am I?");
        main.addComponent(b);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                showMeOnMap();
            }
        });
        contY.add(main);

//        image = getImageFromURL(user.getImage());
    //    detailsC.add(BorderLayout.centerAbsolute(new Label(image)));
    
        this.rootContainer.removeAll();
        
        this.rootContainer.add(BorderLayout.CENTER,contY);


      
} 
     private void showMeOnMap() {
        Form map = new Form("Map");
        map.setLayout(new BorderLayout());
        map.setScrollable(false);
        final MapComponent mc = new MapComponent();

        mc.zoomToLayers();

        map.addComponent(BorderLayout.CENTER, mc);

    }
      
      
    
    public static Image getImageFromURL(String url)
    {
        try {
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(Controller.ip+"/SprintMobileAPI/web/uploads/Users/"+url);
            NetworkManager.getInstance().addToQueueAndWait(request);
            Image image = Image.createImage(new ByteArrayInputStream(request.getResponseData()));
            return image.scaled(755, 455);
        } catch (IOException ex) {
        }
        
        return null;
    }
  public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
