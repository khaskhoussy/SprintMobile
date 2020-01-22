/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.Commande;
import Entity.LigneCommande;
import Entity.LigneService;
import Entity.Produit;
import Entity.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.main.Controller;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import service.CommandeService;
import service.LigneCommandeService;
import com.codename1.sendgrid.SendGrid;
import service.UserService;


/**
 *
 * @author abdelli
 */
public class NewController extends Controller{
    
Label qte ;
Label nomprod;
Label id;
Label datedebut;
Label datefin;
Label sep;
Label prixunitaire;
//private Container a ;
//private Container b ;
private Container cc ;
private Container cc2 ;
private Container cc3 ;
public static int ida;
public static int idc;
public static int lid;
public static User user = new User();
    private Container commandesContainer ;
    private Container lcommandesContainer ;
    private Container boutonContainer ;
    private Container reclamerContainer ;
    
    private Container labelContainer;
    private Container slabelContainer;
    

//Container cc = new Container(BoxLayout.y());
//Container cc2 = new Container(BoxLayout.y());
@Override
    public void initialize() {
        
        
////       commandesContainer = new Container(BoxLayout.y());
//        //setTitle("Commande détail");
        Container cc = new Container(BoxLayout.y()); 
        //lcommandesContainer.setScrollableX(true);
//        lcommandesContainer.setScrollableX(true);
//        Container cc2 = new Container(BoxLayout.y());
//        Container cc3 = new Container(BoxLayout.x());
//        Container x = new Container(BoxLayout.x());
//        x.setScrollableX(true);
//        cc.setScrollableX(true);
//                for(LigneCommande lcommande : LigneCommandeService.getAll1()) {
//                    Produit produit = new Produit();
//                       
//                    
//                    System.out.println(lcommande);
////                   Container a = new Container(BoxLayout.y());
//        //nomprod = new Label(String.valueOf(produit.getNomProd()));
//        //new Label (lcommande.getIdProduit().getNomProd());
//        
//        
////        nomprod = new  Label (lcommande.getIdProduit().getNomProd());
////        qte = new Label(String.valueOf(lcommande.getQte()));
//        
//        
//        /*if (lcommande.getIdProduit().getPrixProd()<lcommande.getIdProduit().getPrixOffre()) {
//          prixunitaire = new Label (String.valueOf(lcommande.getIdProduit().getPrixProd()));  
//        }
//        
//        else if (lcommande.getIdProduit().getPrixProd()>lcommande.getIdProduit().getPrixOffre()){
//            prixunitaire = new Label (String.valueOf(lcommande.getIdProduit().getPrixOffre()));
//        }*/
//                
//            
//       
//      x.add(qte);
//      x.add(nomprod);
//
//      //cc.add(prixunitaire);
//                
//                
//                
//                }
//                cc.add(x);
//                        
//                //add(cc);
//                
//                
//                
//                
//                
//        for(LigneService lservice : LigneCommandeService.getAll2()){ 
//            System.out.println(lservice);
//            
////            Container b = new Container(BoxLayout.x());
//           id = new Label (String.valueOf(lservice.getId()));
//           datedebut = new Label (String.valueOf(lservice.getDateDebut()));
//           datefin = new Label (String.valueOf(lservice.getDateFin()));
//            cc.add(id);
//            cc.add(datedebut);
//            
//                    }
//        //add(cc2);
//        
//        sep = new Label("tesssssssssst");
            Button supprimer = new Button("Supprimer");
            //boutonContainer.add(supprimer);  
        boutonContainer = new Container(BoxLayout.x());
        boutonContainer.add(supprimer);
        //add(cc);
//        
//
supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                //getToolbar().addCommandToRightBar("back", null, e -> .show());
                
///////////////////////////////////////////////////////                
                           /*Message m = new Message("ssss");
m.getAttachments().put("conf", "text/plain");
Display.getInstance().sendMessage(new String[] {"brahim.abdelli994@gmail.com"},id.getText(), m);*/
 ////////////////////////////////////////////////////////////////
//                 Dialog.show("Success","Commande Supprimé","close",null);
//                SendGrid s = SendGrid.create("CJPZP8zHQKm_SyCEyYRO9g");
//                s.sendSync("brahim.abdelli@esprit.tn", "abd3lli@hotmail.com", "Natural",
//                        "Votre Commande a été supprimé avec succées");
 /////////////////////////////////////////////////////////////////////
 
//Message m = new Message("Body of message");
//User u = new User();
//
//Display.getInstance().sendMessage(new String[] {u.getEmail()}, "Subject of message", m);
 
 
 
 
 
 
 
 
 
                
                
                //        ConnectionRequest con = new ConnectionRequest();
//         String url = "http://localhost/SprintMobileAPI/web/app_dev.php/front/vente/" + cmd.getId();
//         ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/",false);
ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/vente/"+ CommandeController.id +"/delete2",false);
//            con.setUrl(url);
             con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Commande supprimé", "ok", null);

        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
                
                
                /*CommandeController CommandeController = new CommandeController();
                                Form mainForm = new Form();
                mainForm.setLayout(new BorderLayout());
                mainForm.getToolbar().setHidden(true);
                mainForm.getContentPane().removeAll();
                SignUpController forumController = new SignUpController();
                forumController.initialize();
                mainForm.addComponent(BorderLayout.CENTER, forumController.getView());
                mainForm.revalidate();
                mainForm.show();*/
                

                
                rootContainer.removeAll();
                 CommandeController CommandeController = new CommandeController();
                CommandeController.initialize();
                rootContainer.addComponent(BorderLayout.CENTER,CommandeController.getView());
                rootContainer.revalidate();
            }
                           
            
        });
//
//




            Button reclamer = new Button("Reclamer");
            //boutonContainer.add(supprimer);  
        reclamerContainer = new Container(BoxLayout.x());
        reclamerContainer.add(reclamer);
        //add(cc);
//        
//
reclamer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                //getToolbar().addCommandToRightBar("back", null, e -> .show());
                
///////////////////////////////////////////////////////                
                           /*Message m = new Message("ssss");
m.getAttachments().put("conf", "text/plain");
Display.getInstance().sendMessage(new String[] {"brahim.abdelli994@gmail.com"},id.getText(), m);*/
 ////////////////////////////////////////////////////////////////
//                 Dialog.show("Success","Commande Supprimé","close",null);
//                SendGrid s = SendGrid.create("CJPZP8zHQKm_SyCEyYRO9g");
//                s.sendSync("brahim.abdelli@esprit.tn", "abd3lli@hotmail.com", "Natural",
//                        "Votre Commande a été supprimé avec succées");
 /////////////////////////////////////////////////////////////////////
 UserService u = new UserService();
 User u1= u.getProfilUser();
CommandeController nc = new CommandeController(); 

Message m = new Message("Bonjour Monsieur/Madame "+u1.getNom()+" "+u1.getPrenom()+"Votre commande qui a été validé le "+CommandeController.selectedCommande.getDateValidation()+" et qui a pour montant totale "+CommandeController.selectedCommande.getPrixTotal());
LigneCommande lc = new LigneCommande();
//+commande.getIdProduit().getNomProd()
Display.getInstance().sendMessage(new String[] {u1.getEmail()}, "Reclamation du titulaire de la commande d'ID#"+CommandeController.selectedCommande.getId(), m);
 
 

                

                
                rootContainer.removeAll();
                 CommandeController CommandeController = new CommandeController();
                CommandeController.initialize();
                rootContainer.addComponent(BorderLayout.CENTER,CommandeController.getView());
                rootContainer.revalidate();
            }
                           
            
        });









        labelContainer = new Container(BoxLayout.y());
        commandesContainer = new Container(BoxLayout.y());
        lcommandesContainer = new Container(BoxLayout.x());
                labelContainer.add(new Label("Vous trouveriez ci-dessous les produits :"));
                commandesContainer.add(labelContainer);
                commandesContainer.add(new Label("Nom          Prix  Qte"));
        //commandesContainer.add(new Label("Prix Total"));
        //for(Commande commande : CommandeService.getAll()) {
            for(LigneCommande commande : LigneCommandeService.getAll1()) {
            //id  = commande.getId();
//            System.err.println(commande);
//            Label date = new Label(commande.getDateValidation());
        
//                    subChild.add(date);
            
            
            commandesContainer.add(packItemm(commande));
                       
            
            
    
        }
            
                for(LigneService lss : LigneCommandeService.getAll2()) {
            //id  = commande.getId();
            System.err.println(lss);
//            Label date = new Label(commande.getDateValidation());
        
//                    subChild.add(date);
            
            
            lcommandesContainer.add(packItemmm(lss));
                       
            
            
    
        }
                slabelContainer = new Container(BoxLayout.y());
           slabelContainer.add(new Label("Vous trouveriez ci-dessous les Services :"));
           commandesContainer.add(slabelContainer);
           commandesContainer.add(lcommandesContainer); 
           commandesContainer.add(boutonContainer); 
           commandesContainer.add(reclamerContainer);
           

this.rootContainer.removeAll();
this.rootContainer.add(BorderLayout.NORTH,commandesContainer);

this.rootContainer.revalidate();
    }

    
        private Container packItemm(LigneCommande commande){
            
        Container parent = new Container(BoxLayout.x());
        //parent.add(getImageFromURL(commande.getImagePack()));
        Container child = new Container(BoxLayout.y());
        parent.add(child);
        Container subChild = new Container(BoxLayout.x());
        Label nomprod = new Label(commande.getIdProduit().getNomProd());
        //Label qte = new Label(String.valueOf(commande.getIdProduit().getQuantite()));
        Label prixprod = new Label (String.valueOf(commande.getIdProduit().getPrixProd())); 
        Label qte = new Label (String.valueOf(commande.getQte()));
        //Label prixprod = new Label(commande.getIdProduit().getPrixProd()); 


        
    
        subChild.add(nomprod);
        subChild.add(prixprod);
        subChild.add(qte);
        child.add(subChild);
        //Label description = new Label(commande.getDescriptionPack());
        //child.add(description);
        return parent ;
    }
    

        
        
        
                private Container packItemmm(LigneService commande){
                    LigneService ls = new LigneService();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Container parent = new Container(BoxLayout.x());
        //parent.add(getImageFromURL(commande.getImagePack()));
        Container child = new Container(BoxLayout.y());
        parent.add(child);
        Container subChild1 = new Container(BoxLayout.y());
        parent.setScrollableX(true);
        
        
        
        

        
        
        //commandesContainer.add(lcommandesContainer);
        Label datedebut = new Label (commande.getDateDebut().toString());
        Label datefin = new Label (commande.getDateFin().toString());
        //lservice.setDateDebut(df.parse(obj.get("datedebut").toString().substring(0,9)));
        //Label datedebut = new Label (commande.setDateDebut(df.parse(commande.get("datedebut").toString().substring(0,9))));
        //Label datedebut = new ls.setDateDebut(df.parse(commande.get("datedebut").toString().substring(0,9)));
        //Label datedebut = new Label (commande.setDateDebut(df.parse(commande.getDateDebut().toString().substring(0,9))));
        //Label datedebut = new Label (commande.setDateDebut(df.parse(dateDebut));
        //Label datedebut = new Label(commande.getDateDebut());
        //Label qte = new Label(String.valueOf(commande.getIdProduit().getQuantite()));
        //Label prixprod = new Label (String.valueOf(commande.getIdProduit().getPrixProd())); 
        //Label prixprod = new Label(commande.getIdProduit().getPrixProd()); 


        
    
        subChild1.add(datedebut);
        subChild1.add(datefin);
        
        //subChild.add(prixprod);
        child.add(subChild1);
        //Label description = new Label(commande.getDescriptionPack());
        //child.add(description);
        return parent ;
    }
}
