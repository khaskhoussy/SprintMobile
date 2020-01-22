/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aisce
 */
public class UserService {
    
    public static User getProfilUser()
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Controller.ip+"/sprintMobileAPI/web/app_dev.php/front/ProfileClient");
        User user = new User();
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                Map<String, Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                user.setId((int)Float.parseFloat(mapUser.get("id").toString()));
                user.setUsername(mapUser.get("username").toString());
                user.setNom(mapUser.get("nom").toString());
                user.setPrenom(mapUser.get("prenom").toString());
                user.setTelephone(mapUser.get("telephone").toString());
                user.setImage(mapUser.get("image").toString());
                user.setEmail(mapUser.get("email").toString());
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user ;
    }
    
    public static boolean modiferUserProfile(User user) {
        ConnectionRequest connectionRequest = 
                new ConnectionRequest(Controller.ip+"/sprintMobileAPI/web/app_dev.php/front/ProfileClient", true);
        connectionRequest.addArgument("changeProfile", "changeProfile");
        connectionRequest.addArgument("nom", user.getNom());
        connectionRequest.addArgument("prenom", user.getPrenom());
        connectionRequest.addArgument("email", user.getEmail());
        connectionRequest.addArgument("telephone", user.getTelephone());
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return connectionRequest.getResponseCode() == 200 ;
    }
    
    public static boolean modiferUserPassword(String currentPassword,String newPassword) {
        ConnectionRequest connectionRequest = 
                new ConnectionRequest(Controller.ip+"/sprintMobileAPI/web/app_dev.php/front/ProfileClient", true);
        connectionRequest.addArgument("changePassword", "changePassword");
        connectionRequest.addArgument("currentPassword", currentPassword);
        connectionRequest.addArgument("newPassword", newPassword);
        connectionRequest.addArgument("verifPassword", newPassword);
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        System.out.println(new String(connectionRequest.getResponseData()));
        return (new String(connectionRequest.getResponseData())).equals("\"true\"");
    }
}
