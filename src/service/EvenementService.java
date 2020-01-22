/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Evenement;
import Entity.EventComments;
import Entity.LignePack;
import Entity.PackDecoration;
import Entity.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.main.Controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cherif
 */
public class EvenementService {
    
    public static List<Evenement> findAll() {
        List<Evenement> events = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/event/",false);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                
                
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                
                for (Map<String, Object> obj : list) {
                    Evenement event = new Evenement();
                    Collection<EventComments> eventCommentsList = new ArrayList();
                    event.setEventCommentsCollection(eventCommentsList);
                    event.setId((int)Float.parseFloat(obj.get("id").toString()));
                    event.setNom(obj.get("nom").toString());
                    event.setDescription(obj.get("description").toString());
                    event.setEveprix((int)Float.parseFloat(obj.get("eveprix").toString()));
                    event.setImage(obj.get("image").toString());
                    event.setDatedebut(obj.get("datedebut").toString());
                    event.setDatefin(obj.get("datefin").toString());
                    
                    List<Map<String, Object>> lignePacksJson = (List<Map<String, Object>>) obj.get("commentairesEvents");
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    for(Map<String, Object> lignePackJson : lignePacksJson) {
                        EventComments eventComments  = new EventComments();
                        eventComments.setId((int)Float.parseFloat(lignePackJson.get("id").toString()));
                        eventComments.setContenu(lignePackJson.get("contenu").toString());
                        try {
                            eventComments.setDate(dateFormat.parse(lignePackJson.get("date").toString().substring(0, 9)));
                        } catch (ParseException ex) {
                        }
                        eventComments.setIdEvenement(event);
                        eventCommentsList.add(eventComments);
                    }
                    
                    events.add(event);
                }
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return events;
    }
    
        
    public static boolean checkParticipation(int idEvent) {
        
        ConnectionRequest con = 
                new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/event/checkParticipation",false);
        con.addArgument("idevenement", String.valueOf(idEvent));
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return Integer.parseInt(new String(con.getResponseData())) == 0;
    }
    
    public static boolean participate(int idEvent) {
        
        ConnectionRequest con = 
                new ConnectionRequest(Controller.ip+"/SprintMobileAPI/web/app_dev.php/front/event/participate",true);
        con.addArgument("idevenement", String.valueOf(idEvent));
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return new String(con.getResponseData()).equals("OK");
    }
}
