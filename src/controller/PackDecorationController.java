/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.LignePack;
import Entity.PackDecoration;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import java.util.List;
import service.PackDecorationService;

/**
 *
 * @author Dreamer
 */
public class PackDecorationController extends Controller {

    private Container packsContainer,parentContainer,detailContainer ;
    private ComboBox categorie ;
    private Button all,favoris;
    private List<PackDecoration> packs ;
    
    @Override
    public void initialize() {
        parentContainer = new Container(BoxLayout.y());
        packsContainer = new Container(BoxLayout.y());
        packsContainer.setScrollableY(true);
        initCategorie();
        initData("ALL");
        all = new Button("All");
        favoris = new Button("Favoris");
        all.addActionListener(evt->initData("ALL"));
        favoris.addActionListener(evt->initFavoris());
        
        parentContainer.add(BoxLayout.encloseX(all,favoris));
        parentContainer.add(categorie);
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
    
    
    private void initDetailContainer(PackDecoration pack ) {
        detailContainer = new Container(BoxLayout.y());
        Container listProduitContainer = new Container(BoxLayout.y());
        listProduitContainer.setScrollableY(true);
        Button btn = new Button("Liste des packs");
        Button favBtn = new Button("Ajouter au liste des favoris");
        if(Storage.getInstance().exists("pack"+pack.getId().toString())) favBtn.setEnabled(false);
        favBtn.addActionListener((evt)->{
            Storage.getInstance().writeObject("pack"+pack.getId().toString(), pack);
            Storage.getInstance().flushStorageCache();
            favBtn.setEnabled(false);
        });
        btn.addActionListener(this::retourList);
        detailContainer.add(BoxLayout.encloseX(btn,favBtn));
        detailContainer.add(BorderLayout.centerAbsolute(new Label(getImageFromURL(pack.getImagePack()))));
        detailContainer.add(BoxLayout.encloseX(new Label("Nom :"),new Label(pack.getNomPack())));
        Label description = new Label(pack.getDescriptionPack());
        detailContainer.add(BoxLayout.encloseX(new Label("Description :")));
        detailContainer.add(BoxLayout.encloseX(description));
        detailContainer.add(BoxLayout.encloseX(new Label("Prix :"),new Label(String.valueOf(pack.getPrixP()))));
        detailContainer.add(new Label("Liste des produits :"));
        detailContainer.add(BorderLayout.centerAbsolute(listProduitContainer));
        for(LignePack lignePack : pack.getLignePackCollection()) {
            
            listProduitContainer.add(BoxLayout.encloseX(
                    new Label(getImageProduitFromURL(lignePack.getIdProduit().getImage())),
                    BoxLayout.encloseY(
                        new Label(lignePack.getIdProduit().getNomProd()),
                        new Label(String.valueOf(lignePack.getIdProduit().getPrixProd())),
                        new Label(String.valueOf(lignePack.getQuantitePack()))
                    )
            ));
            
            listProduitContainer.revalidate();
            
        }
        
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,detailContainer);
        this.rootContainer.revalidate();
    }
    
    private void initData(String cat){
        categorie.setVisible(true);
        if(cat.equals("ALL")) packs = PackDecorationService.findAll(null);
        else packs =  PackDecorationService.findAll(cat) ;
        packsContainer.removeAll();
        for(PackDecoration pack : packs) {
            packsContainer.add(packItem(pack));
        }    
        packsContainer.revalidate();
    }
    
    private void initFavoris() {
        Storage st = Storage.getInstance();
        categorie.setVisible(false);
        packsContainer.removeAll();
        for(String s : st.listEntries()){
            if(s.contains("pack")) {
                Container c = packItem((PackDecoration) st.readObject(s));
                Button btn = new Button("Supprimer");
                c.add(btn);
                btn.addActionListener((ActionEvent evt) -> {
                    st.deleteStorageFile(s);
                    c.remove();
                    packsContainer.revalidate();
                });
                packsContainer.add(c);
            }
        }
        packsContainer.revalidate();
    }
    
    private void initCategorie() {
        categorie = new ComboBox();
        categorie.addItem("ALL");
        categorie.addItem("Packs pour petit bassin");
        categorie.addItem("Packs pour ombre ou mi-ombre");
        categorie.addItem("Packs de bordure");
        categorie.addItem("Packs de phyto-épuration");
        
        categorie.addActionListener((ActionListener) (ActionEvent evt) -> {
            initData((String)categorie.getSelectedItem());
        });
    }
    
    private Container packItem(PackDecoration pack){
        Container parent = new Container(BoxLayout.x());
        parent.getUnselectedStyle().setMarginBottom(5);
        ImageViewer imageViewer = new ImageViewer(getImageFromURL(pack.getImagePack()));
        imageViewer.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(Component cmp) {
                initDetailContainer(pack);
            }

            @Override
            public void focusLost(Component cmp) {
                
            }
        });
        parent.add(imageViewer);
        Container child = new Container(BoxLayout.y());
        parent.add(child);
        Container subChild = new Container(BoxLayout.x());
        Label nom = new Label(pack.getNomPack());
        Label prix = new Label(String.valueOf(pack.getPrixP()));
        subChild.add(nom);
        subChild.add(prix);
        child.add(subChild);
        Label description = new Label(pack.getDescriptionPack());
        child.add(description);
        return parent ;
    }
    
    
    public static Image getImageFromURL(String url)
    {
        try {
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(Controller.ip+"/SprintMobileAPI/web/uploads/packs/"+url);
            NetworkManager.getInstance().addToQueueAndWait(request);
            Image image = Image.createImage(new ByteArrayInputStream(request.getResponseData()));
            return image.scaled(64, 64);
        } catch (IOException ex) {
        }
        
        return null;
    }
    
        public static Image getImageProduitFromURL(String url)
    {
        try {
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(Controller.ip+"/SprintMobileAPI/web/images/"+url);
            NetworkManager.getInstance().addToQueueAndWait(request);
            Image image = Image.createImage(new ByteArrayInputStream(request.getResponseData()));
            return image.scaled(64, 64);
        } catch (IOException ex) {
        }
        
        return null;
    }
    
}