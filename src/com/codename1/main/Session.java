/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.main;

import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.xml.XMLParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author hero
 */
public class Session {
    
    private String username;
    private String password;
    private static Session session;
    private User user;
    
    private Session()
    {
        
    }
    
    
    public static Session getInstance()
    {
        if(session == null) session = new Session();
        return session;
    }
    
    public void setParameters(String username,String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public boolean initSession()
    {
        //User u = getUserByUsername(username);
        //String hashed =u.getPassword().substring(0, 2)+"a"+u.getPassword().substring(3);
        //if (!BCrypt.checkpw(password,hashed)) return false;
        try {
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl(Controller.ip+"/SprintMobileAPI/web/app_dev.php/login");
            NetworkManager.getInstance().addToQueueAndWait(con);
            XMLParser xmlParsr = new XMLParser();
            xmlParsr.setIncludeWhitespacesBetweenTags(true);
            String token =  xmlParsr.parse(new InputStreamReader(new ByteArrayInputStream(con.getResponseData()), "UTF-8")).getElementById("tokenInput").getAttribute("value");
            con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/login_check", true);
            con.addArgument("_username", username);
            con.addArgument("_password", password);
            con.addArgument("_csrf_token", token);
            NetworkManager.getInstance().addToQueueAndWait(con);
            if(!con.getUrl().equals(Controller.ip+"/SprintMobileAPI/web/app_dev.php/login"))
                return true;
        } catch (UnsupportedEncodingException ex) {
        }
        return false;
    }
    

    
    
}
