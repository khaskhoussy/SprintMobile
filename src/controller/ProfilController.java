/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.User;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import service.UserService;

/**
 *
 * @author aisce
 */
public class ProfilController extends Controller {

    private User user ;
    private Container profilContainer ;
    private Image image ;
    private TextField nomT,prenomT,telephoneT,emailT,currentPassword,newPassword;
    private Button modifier,valider,validerPassword;
    
    @Override
    public void initialize() {
        initFields();
        initData();
        profilContainer = new Container(BoxLayout.y());
        profilContainer.setScrollableY(true);
        image = getRoundImage(getImageFromURL(user.getImage()));
        profilContainer.add(BorderLayout.centerAbsolute(new Label(image)));
        profilContainer.add(BorderLayout.centerAbsolute(new Label(user.getUsername())));
        profilContainer.add(new Label("Nom"));
        profilContainer.add(nomT);
        profilContainer.add(new Label("Prenom"));
        profilContainer.add(prenomT);
        profilContainer.add(new Label("Telephone"));
        profilContainer.add(telephoneT);
        profilContainer.add(new Label("Email"));
        profilContainer.add(emailT);
        
        profilContainer.add(BoxLayout.encloseX(modifier,valider));
       
        profilContainer.add(BoxLayout.encloseY(currentPassword,newPassword,validerPassword));
        
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,profilContainer);
        this.rootContainer.revalidate();
        
    }
    
    private void initFields(){
        nomT = new TextField();
        nomT.setEditable(false);
        prenomT = new TextField();
        prenomT.setEditable(false);
        telephoneT = new TextField();
        telephoneT.setConstraint(TextField.PHONENUMBER);
        telephoneT.setEditable(false);
        emailT = new TextField();
        emailT.setEditable(false);
        
        modifier = new Button("modifier");
        modifier.addActionListener((ActionEvent evt)->{
            modifier.setEnabled(false);
            valider.setEnabled(true);
            nomT.setEditable(true);
            prenomT.setEditable(true);
            telephoneT.setEditable(true);
            emailT.setEditable(true);
        });
        valider = new Button("valider");
        valider.setEnabled(false);
        valider.addActionListener((ActionEvent evt) -> {
            modifier.setEnabled(true);
            valider.setEnabled(false);
            nomT.setEditable(false);
            prenomT.setEditable(false);
            telephoneT.setEditable(false);
            emailT.setEditable(false);
            user.setNom(nomT.getText());
            user.setPrenom(prenomT.getText());
            user.setEmail(emailT.getText());
            user.setTelephone(telephoneT.getText());
            if(!UserService.modiferUserProfile(user)) {
                Dialog.show("Error!", "Modification non validée", "Ok", null);
                initData();
            }
        });
        
        currentPassword = new TextField("", "Current password");
        currentPassword.setConstraint(TextField.PASSWORD);
        newPassword = new TextField("", "New password");
        newPassword.setConstraint(TextField.PASSWORD);
        validerPassword = new Button("Change password");
        validerPassword.addActionListener((ActionEvent evt)->{
            boolean b = UserService.modiferUserPassword(currentPassword.getText(), newPassword.getText());
            if(!b) Dialog.show("Error!", "Modification non validée", "Ok", null);
            currentPassword.setText("");
            newPassword.setText("");
        });
    }
    
    private void initData(){
        user = UserService.getProfilUser() ;
        nomT.setText(user.getNom());
        prenomT.setText(user.getPrenom());
        telephoneT.setText(user.getTelephone());
        emailT.setText(user.getEmail());
    }
    
    public static Image getRoundImage(Image originalImage)
    {
        Label label1 = new Label(originalImage);
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();   
        Image maskImage = Image.createImage(w, h);
        Graphics g = maskImage.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0x000000);
        g.fillRect(0, 0, w, h);
        g.setColor(0xffffff);
        g.fillArc(0, 0, w, h, 0, 360);
        Object mask = maskImage.createMask();     
        return originalImage.applyMask(mask);
    }
    
    public static Image getImageFromURL(String url)
    {
        try {
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(Controller.ip+"/SprintMobileAPI/web/uploads/Users/"+url);
            NetworkManager.getInstance().addToQueueAndWait(request);
            Image image = Image.createImage(new ByteArrayInputStream(request.getResponseData()));
            return image.scaled(64,64);
        } catch (IOException ex) {
        }
        
        return null;
    }
}
