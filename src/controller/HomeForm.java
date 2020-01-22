/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package controller;

import Entity.Produit;
import service.ServiceProduit;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import service.PanierService;
import service.UserService;


/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class HomeForm extends Form {
    Label nom;
    Label supp;
    Label modif;
    Button ajout;
    Button rech;
    Button stat;
    Label jaim;
    Label jaimpas;
    Label panier;
    TextField re;
    ImageViewer imv;
    Image img;
    EncodedImage enc;
    int compteur = 0;
    String imgName;
    String imgUrl;
    private Resources res ;
    
    
    public HomeForm(Resources res) {
        
        super("Nos Produits", BoxLayout.y());
        this.res = res ;
         Image icon1 = res.getImage("back.png");
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
       // super.addSideMenu(res);
        tb.addSearchCommand(e -> {
            
        });
        tb.addCommandToOverflowMenu("Filtrage", null, new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt) {
                       // ProduitForm fp=new ProduitForm(theme);
                UIBuilder ui = new UIBuilder(); 
         Container ct1 = ui.createContainer(res,"Filtrage");
         Form fp= (Form)ct1;
           
            System.out.println("Filtrage");
            
        fp.show();
          Slider sl = (Slider) ui.findByName("Slider", ct1);
          Button btfiltre = (Button) ui.findByName("Button", ct1);
          Label lab = (Label) ui.findByName("Label", ct1);
          
          //getStyle().setBgColor(0x000000);
          
              sl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                lab.setText("Prix:"+sl.getProgress()); 
                System.out.println(sl.getProgress());
            }
        });
            Form f1 = new Form();
       Toolbar tf2 = new Toolbar(true);
                        f1.setToolbar(tf2);
                        f1.getToolbar().getStyle().setBgColor(0xD3D3D3);
                        f1.getToolbar().getStyle().setBgTransparency(250);
                        f1.getToolbar().setTitle("Favoris");
                        
                        f1.getToolbar().addCommandToLeftBar("back", res.getImage("back.png"), new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                              HomeForm produit = new HomeForm(res); // Logger.getLogger(AboutForm.class.getName()).log(Level.SEVERE, null, ex);
                                produit.show();
                            }
                        });
                         btfiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean d;
                d =Dialog.show("Prix", "Voulez-vous Choisir le prix "+sl.getProgress(), "Yes", "No");
               
                
                
                     ConnectionRequest conF = new ConnectionRequest();
       // ConnectionRequest con2 = new ConnectionRequest();
        conF.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/Prix/"+sl.getProgress());
       // con2.setUrl("http://localhost/madame/web/app_dev.php/pi_mobile/produitSearch/"+re.getText());
        conF.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

               ArrayList<Produit> list = new ArrayList<>();
               list.addAll(getListEtudiant(new String(conF.getResponseData())));
          
                System.out.println(list);
                for (Produit eq : getListEtudiant(new String(conF.getResponseData()))) {
                   try {
                       addItem1(eq,fp,res,icon1);
                   } catch (IOException ex) {
                    
                   }
                }
                Container C = new Container(new BoxLayout(BoxLayout.X_AXIS));
             
                fp.add(C);
 fp.show();
            }
        });
              NetworkManager.getInstance().addToQueue(conF);
       
            
              
            }
        });
          
     
   
              }
 });

     
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
       addTab(swipe, res.getImage("images.jpg"), spacer1, "", "","");
       // addTab(swipe, res.getImage("images.jpg"), spacer2,"","","");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
//        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
         RadioButton stat = RadioButton.createToggle("stat", barGroup);
        stat.setUIID("SelectBar");
        RadioButton chicha = RadioButton.createToggle("Plante", barGroup);
        chicha.setUIID("SelectBar");
        RadioButton traditionalfood = RadioButton.createToggle("Accesoire", barGroup);
        traditionalfood.setUIID("SelectBar");
        RadioButton artisanial = RadioButton.createToggle("Materile", barGroup);
        artisanial.setUIID("SelectBar");
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(5,  chicha, traditionalfood, artisanial,stat),
                FlowLayout.encloseBottom(arrow)
        ));
        
        Tabs tab=new Tabs();
        Container ct=new Container();
   
        chicha.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(chicha, arrow);
        });
        bindButtonSelection(stat, arrow);
        bindButtonSelection(chicha, arrow);
        bindButtonSelection(traditionalfood, arrow);
        bindButtonSelection(artisanial, arrow);
       
       
       
        
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        rech=new Button("rechercher");
        ServiceProduit SP=new ServiceProduit();
         rech.addActionListener((e) -> {
            Form F2 = new Form(BoxLayout.y());
            String d = re.getText();
            ArrayList<Produit> liche = SP.ChercherProduit(d);
            for (Produit lis : liche) {
                ImageViewer imgV = new ImageViewer();
                Image placeholder = Image.createImage(180, 150);
                try {
                    enc = EncodedImage.create("/back.png");
                    imgName = "img" + compteur;
                    imgUrl = "http://localhost/SprintMobileAPI/web/images/" + lis.getImage();
                } catch (IOException ex) {
                }
                img = URLImage.createToStorage(enc, imgName, imgUrl, URLImage.RESIZE_SCALE);
                imv = new ImageViewer(img);
                compteur++;

                Label aa = new Label("Nom  : " + lis.getNomProd());
                Label desc = new Label("Description  : " + lis.getProdDescription());
                Label adresse = new Label("adresse :" + lis.getPrixProd());
                Label nbpl = new Label("catégorie :" + lis.getQuantite());
                // Label datee = new Label("Date :" + lis.getDate());
                F2.add(img);
                F2.add(aa);
                //F2.add(datee);
                F2.add(desc);
                F2.add(nbpl);
                F2.add(adresse);

                F2.show();
                Toolbar tf2 = new Toolbar(true);
                        F2.setToolbar(tf2);
                        F2.getToolbar().getStyle().setBgColor(0xD3D3D3);
                        F2.getToolbar().getStyle().setBgTransparency(250);      
      F2.getToolbar().addCommandToLeftBar("back", null, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                        /*  NewsfeedForm produit = new NewsfeedForm(res); // Logger.getLogger(AboutForm.class.getName()).log(Level.SEVERE, null, ex);
                                produit.show();*/
                            }
                        });

            }

        });
       
        
        
        stat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Container C = new Container(new BoxLayout(BoxLayout.X_AXIS));
                ApiStat a=new ApiStat();
              a.createPieChartForm(res).show();
              
                add(C);
           
                
            }
        });
        
        chicha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Plante");
               // Form fh = new Form();
                
        ConnectionRequest conH = new ConnectionRequest();
        conH.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/produitsearch/Plante");
        conH.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

              ArrayList<Produit> list = new ArrayList<>();
              
                System.out.println(new String(conH.getResponseData()));
              
               list.addAll(getListEtudiant(new String(conH.getResponseData())));
          
               
                for (Produit eq : getListEtudiant(new String(conH.getResponseData()))) {
                   try {
                       addItem(eq,res,icon1);
                       list.clear();
                     
                   } catch (IOException ex) {
                    
                   }
                }
                  
                Container C = new Container(new BoxLayout(BoxLayout.X_AXIS));
                 
                add(C);

            }
        });
        NetworkManager.getInstance().addToQueue(conH);
        //fh.show();
            }
        });
        
            traditionalfood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Materiel");
             ConnectionRequest conF = new ConnectionRequest();
        conF.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/produitsearch/Materiel");
        conF.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

        ArrayList<Produit> list = new ArrayList<>();
       
               list.addAll(getListEtudiant(new String(conF.getResponseData())));
         
               
                for (Produit eq : getListEtudiant(new String(conF.getResponseData()))) {
                   try {
                       addItem(eq,res,icon1);
                        list.clear();
                      
                   } catch (IOException ex) {
                    
                   }
                }
                 
                Container C = new Container(new BoxLayout(BoxLayout.X_AXIS));
             
                add(C);

            }
        });
        NetworkManager.getInstance().addToQueue(conF);
        //fh.show();
            }
        });
            
            artisanial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Accesoire");
             ConnectionRequest conE = new ConnectionRequest();
        conE.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/produitsearch/Accesoire");
        conE.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

               ArrayList<Produit> list = new ArrayList<>();
               list.addAll(getListEtudiant(new String(conE.getResponseData())));
          
               
                for (Produit eq : getListEtudiant(new String(conE.getResponseData()))) {
                   try {
                       addItem(eq,res,icon1);
                        list.clear();
                   } catch (IOException ex) {
                    
                   }
                }
                 
                Container C = new Container(new BoxLayout(BoxLayout.X_AXIS));
             
                add(C);

            }
        });
        NetworkManager.getInstance().addToQueue(conE);
        //fh.show();
            }
        });
            
            
            
        
////        addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
////        addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
////        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
////        addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
   ConnectionRequest con1 = new ConnectionRequest();
       // ConnectionRequest con2 = new ConnectionRequest();
       
    
    
    
    
    
    
    
    
    }
    
    
    
      public void addItem1(Produit eq,  Form Page_equi,Resources theme,Image icon1) throws IOException {
         
          UIBuilder  ui = new UIBuilder();
         Container ct1;
 
      Form f = new Form();
         
    EncodedImage imc;
    Image img;
    ImageViewer imv;
   String url="http://localhost/SprintMobileAPI/web/images/"+eq.getImage();
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
      Label  nom = new Label(eq.getNomProd());
       Label supp = new Label("Voir plus");
    imc = EncodedImage.create("/load.png");
            
        img=URLImage.createToStorage(imc,""+eq.getNomProd(), url, URLImage.RESIZE_SCALE);
             int displayHeight = Display.getInstance().getDisplayHeight();
        ScaleImageLabel scaleImageLabel = new ScaleImageLabel(img);
        Image scImage = img.scaled(-1, displayHeight / 10);
         imv= new ImageViewer(scImage);
        supp.getAllStyles().setFgColor(0xFF0000);
                   addButton(theme,scImage, eq,nom.getText(), false, 26, 32);

       C2.add(imv); 
       C2.add(nom);
        C3.add(supp);
      //  C3.add(re);
        Page_equi.add(C2);
        Page_equi.add(C3);
        

   
         supp.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
             f.show();
              Label lnom = new Label ("Le nom est: "+eq.getNomProd());
              Label lref = new Label ("La reference est: "+eq.getId());
              Label lcat = new Label ("La categorie est: "+eq.getQuantite());
              
              Label ldesc = new Label ("La description est: "+eq.getProdDescription());
              Label lprix = new Label ("Le prix est: "+eq.getPrixProd()+"DT");
              
              f.add(lnom);
              f.add(lref);
              f.add(lcat);
              f.add(ldesc);
              f.add(lprix);
     
              Image scImage1 = img.scaled(-1, displayHeight / 3);
              ImageViewer imvo;
              imvo= new ImageViewer (scImage1);
              
              
              Container cnt = new Container (new BoxLayout (BoxLayout.X_AXIS) );
               Container cnt1 = new Container (new BoxLayout (BoxLayout.Y_AXIS) );
              cnt.add(imvo);            
            Label jaim = new Label("J'aime");
             Label jaimpas= new Label("J'aime pas");
            Label panier= new Label("Ajouter au panier");
             jaim.getAllStyles().setFgColor(0xFF0000);
             jaimpas.getAllStyles().setFgColor(0xFF0000);
             panier.getAllStyles().setFgColor(0xFF0000);
             
             cnt1.add(jaim);
             cnt1.add(jaimpas);
             cnt1.add(panier);
             cnt.add(cnt1);  
             f.add(cnt);
             f.getToolbar().addCommandToLeftBar("back", res.getImage("back.png"), new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                               HomeForm h=new HomeForm(res);
                               h.show();
                             
                            }
                        });  


            }

        });
        
               
               
    }
        
     public void addItem(Produit eq,Resources theme,Image icon1) throws IOException {
         
           UIBuilder  ui = new UIBuilder();
         Container ct1;
 
      Form f1 = new Form();
         
    EncodedImage imc;
    Image img;
    ImageViewer imv;
   String url="http://localhost/SprintMobileAPI/web/images/"+eq.getImage();
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
      Label  nom = new Label(eq.getNomProd());
       Label supp = new Label("Voir plus");
    imc = EncodedImage.create("/load.png");
            
        img=URLImage.createToStorage(imc,""+eq.getNomProd(), url, URLImage.RESIZE_SCALE);
             int displayHeight = Display.getInstance().getDisplayHeight();
        ScaleImageLabel scaleImageLabel = new ScaleImageLabel(img);
        Image scImage = img.scaled(-1, displayHeight / 10);
         imv= new ImageViewer(scImage);
        supp.getAllStyles().setFgColor(0xFF0000);

        
           addButton(theme,scImage, eq,nom.getText(), false, 26, 32);

   
        supp.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
             f1.show();
              Label lnom = new Label ("Le nom est: "+eq.getNomProd());
              Label lref = new Label ("La reference est: "+eq.getId());
              Label lcat = new Label ("La categorie est: "+eq.getQuantite());
              
              Label ldesc = new Label ("La description est: "+eq.getProdDescription());
              Label lprix = new Label ("Le prix est: "+eq.getPrixProd()+"DT");
              
              f1.add(lnom);
              f1.add(lref);
              f1.add(lcat);
              f1.add(ldesc);
              f1.add(lprix);
     
              Image scImage1 = img.scaled(-1, displayHeight / 3);
              ImageViewer imvo;
              imvo= new ImageViewer (scImage1);
              
              
              Container cnt = new Container (new BoxLayout (BoxLayout.X_AXIS) );
               Container cnt1 = new Container (new BoxLayout (BoxLayout.Y_AXIS) );
              cnt.add(imvo);            
            Label jaim = new Label("J'aime");
             Label jaimpas= new Label("J'aime pas");
            Label panier= new Label("Ajouter au panier");
             jaim.getAllStyles().setFgColor(0xFF0000);
             jaimpas.getAllStyles().setFgColor(0xFF0000);
             panier.getAllStyles().setFgColor(0xFF0000);
             
             cnt1.add(jaim);
             cnt1.add(jaimpas);
             cnt1.add(panier);
             cnt.add(cnt1);  
             f1.add(cnt);
              f1.getToolbar().addCommandToLeftBar("back",icon1,new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             /*  HomeForm produit=new HomeForm(theme);
                produit.show();*/
            }
        });


            }

        });
               
               
               
               
               
    }
     
     
     public ArrayList<Produit> getListEtudiant(String json) {
         
        ArrayList<Produit> listEtudiants = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();
            
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

       
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Produit p = new Produit();//id, json, status);
              
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
                 
                
                
                
                
                
                
              
                listEtudiants.add(p);

            }

        } catch (IOException ex) {
        }
        return listEtudiants;

    }

    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Resources res,Image img, Produit eq,String title, boolean liked, int likeCount, int commentCount) {
        UIBuilder  ui = new UIBuilder();
         Container ct1;
  
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       Label v = new Label("              ");
    
       Label likes = new Label( eq.getNbjaimes()+ " j'aimes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_RATE_REVIEW);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes,FontImage.MATERIAL_RATE_REVIEW);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes)
               ));
     
       add(cnt);
       image.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
                   Form f1 = new Form();
       Toolbar tf2 = new Toolbar(true);
                        f1.setToolbar(tf2);
                        f1.getToolbar().getStyle().setBgColor(0xD3D3D3);
                        f1.getToolbar().getStyle().setBgTransparency(250);
                        f1.getToolbar().setTitle("Favoris");
                        
                        f1.getToolbar().addCommandToLeftBar("back", res.getImage("back.png"), new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                             HomeForm h=new HomeForm(res);
                             h.show();
                            }
                        }); 

                        
              f1.show();
              Label lnom = new Label ("Le nom est: "+eq.getNomProd());
              Label lref = new Label ("La reference est: "+eq.getId());
              Label lcat = new Label ("La categorie est: "+eq.getQuantite());
              
              Label ldesc = new Label ("La description est: "+eq.getProdDescription());
              Label lprix = new Label ("Le prix est: "+eq.getPrixProd()+"DT");
      
              f1.add(lnom);
              f1.add(lref);
              f1.add(lcat);
              f1.add(ldesc);
              f1.add(lprix);
   
                int displayHeight = Display.getInstance().getDisplayHeight();
              Image scImage1 = img.scaled(-1, displayHeight / 3);
              ImageViewer imvo;
              imvo= new ImageViewer (scImage1);
              
              
              Container cnt = new Container (new BoxLayout (BoxLayout.X_AXIS) );
               Container cnt1 = new Container (new BoxLayout (BoxLayout.Y_AXIS) );
              cnt.add(imvo);  
              
            Label jaim = new Label("J'aime");
            
    
            
            
                 Button Modifier = new Button("modifier");
   ServiceProduit s=new ServiceProduit();
               Button supp = new Button("delete");
                    supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            s.supprimerProduit(eq ,res);
                            Dialog d = new Dialog("Avis");
                            Dialog.show("Avis", "Blog deleted successfuly", "OK", null);
                            d.dispose();
                           

                        }
                    });
        
           
             Label jaimpas= new Label("J'aime pas");
            Label panier= new Label("Ajouter au panier");
              TextField qt=new TextField("Veuillez Saisir la quantité");
              qt.getAllStyles().setFgColor(0xFF0000);
             jaim.getAllStyles().setFgColor(0xFF0000);
             jaimpas.getAllStyles().setFgColor(0xFF0000);
             panier.getAllStyles().setFgColor(0xFF0000);
             supp.getAllStyles().setFgColor(0xFF0000);
         //   evaluer.getAllStyles().setFgColor(0xFF0000);

             
                jaim.addPointerPressedListener(new ActionListener(){
           
            
           
          public void actionPerformed(ActionEvent evt) {
                
            ConnectionRequest con1 = new ConnectionRequest();
               con1.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/LikeMobile/"+UserService.getProfilUser().getId()+"/"+eq.getId());
               
                System.out.println(1);

                
            con1.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

            String x=new String(con1.getResponseData());
                System.out.println(new String(con1.getResponseData()));
                //int y= Integer.parseInt(x);
              int y=(int) Float.parseFloat(x);
                System.out.println("x est" +x);
                System.out.println("y est" +y);
                
                
                  if(x.equals("0"))
              {
                  boolean j;
              
                  j =Dialog.show("Mention j'aime", "Vous avez déjà effectué un j'aime sur ce produit ", "ok",null);

              }
                       
                  if(x.equals("1"))
              {
                  boolean j;
              
                  j =Dialog.show("Mention j'aime", "j'aime effectué ", "ok",null);

              }
//                      else if(y==1)
//              {
//                  boolean j;
//              
//                  j =Dialog.show("Mention j'aime", "Vous avez déjà effectué un j'aime sur ce produit, Vous ne pouvez plus renouveler ", "ok",null);
//
//              }
            }
        });
        NetworkManager.getInstance().addToQueue(con1);


             
          }
            });
              
               
          jaimpas.addPointerPressedListener(new ActionListener(){
           
            
           
          public void actionPerformed(ActionEvent evt) {
                
            ConnectionRequest con1 = new ConnectionRequest();
               con1.setUrl("http://localhost/SprintMobileAPI/web/app_dev.php/front/favorie/DislikeMobile/"+UserService.getProfilUser().getId()+"/"+eq.getId());
                System.out.println("ok");
               

                
            con1.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                 String x=new String(con1.getResponseData());
                System.out.println(new String(con1.getResponseData()));
   int y= Integer.parseInt(x);
              
                System.out.println("x est" +x);
                System.out.println("y est" +y);
    
                
                      if(x.equals("0"))
              {
                  boolean j;
              
                  j =Dialog.show("Mention j'aime pas", "Vous avez déjà effectué un j'aime pas sur ce produit ", "ok",null);

              }
                       
                  if(x.equals("1"))
              {
                  boolean j;
              
                  j =Dialog.show("Mention j'aime pas", "j'aime pas effectué ", "ok",null);

              }

            }
        });
        NetworkManager.getInstance().addToQueue(con1);


             
          }
            });
          
                       panier.addPointerPressedListener(new ActionListener() {
                           
                             
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                        if(qt.getText().equals(""))   
                   {
                       
                        
                       
                            Dialog.show("erreur", "veuillez remplir la quantite", "Ok", null);
                   }
                        else
                        {
                             PanierService.ajouterProduit(eq.getId(), Integer.parseInt(qt.getText()));
                     
                 }}
             });
                               
               
                               
       ShareButton sb = new ShareButton (); 
      sb.setText ("Partager"); 
     sb.setTextToShare ("Ce produit est excellent"+eq.getNomProd()); 
     
        
            // cnt1.add(supp);
            // cnt1.add(Modifier);
             cnt1.add(jaim);
             cnt1.add(jaimpas);
             cnt1.add(panier);
             cnt1.add(qt);
             //cnt1.add(rech);
        //  cnt1.add(re);
             cnt1.add(sb);
             cnt.add(cnt1);  
             f1.add(cnt);//To change body of generated methods, choose Tools | Templates.
           }
       });
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
