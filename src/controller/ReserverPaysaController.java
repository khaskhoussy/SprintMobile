/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.User;
import com.codename1.components.ImageViewer;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.main.Controller;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import static controller.ListePaysagisteController.getImageFromURL;
import java.util.Map;
import javafx.scene.control.DatePicker;
import service.MesServicesService;
import service.PanierService;
import service.UserService;

/**
 *
 * @author hp
 */
public class ReserverPaysaController extends Controller {


    public void initialize () 
    {

        Container l = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        l.setScrollableY(true);       
        MesServicesService ms=new MesServicesService();
        UserService m=new UserService();
        User s2=m.getProfilUser();
        User s1=ms.getDetailById(User.focusedId);
        ImageViewer imageViewer = new ImageViewer(getImageFromURL(s1.getImage()));
        Label Nom = new Label("Nom: "+s1.getNom());
        Label Mail = new Label("Mail: "+s1.getEmail());
        Label Num = new Label("Num: "+s1.getTelephone());
        Label Dd=new Label("Date debut: ");
        Picker DD = new Picker();
        Label Df=new Label("Date fin: ");
        Picker DF = new Picker();

        l.add(imageViewer);
        l.add(Nom);
        l.add(Mail);
        l.add(Num);
        l.add(Dd);
        l.add(DD);
        l.add(Df);
        l.add(DF);  
        Long DDf=DF.getDate().getTime();
        Long DDd=DD.getDate().getTime();


        Button confirmer = new Button("Confirmer");
        
        l.add(confirmer); 
                confirmer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                            if (DDd>DDf)
        {
                            Dialog.show("Alert", "Date fin incorrecte inferieur a Date debut", "ok", null);
        }
        else
        {
                    PanierService.reserver(s1.getId(), DD.getDate(), DF.getDate());
                    MesServicesService is = new MesServicesService();
                    User user = new User();
                    String accountSID = "AC69e052b9351b95fa15bddf1359972af1";
                    String authToken = "b5bdc61e4d43bc865a54d717a629f300";
                    String fromPhone = "+19312723559";
                    Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                    queryParam("To","+21626123530").
                    queryParam("From", fromPhone).
                    queryParam("Body", s1.getNom()+": Vous avez une reservation de "+s2.getNom()+"\n"+"num de tel: "
                    +s2.getTelephone()+"\n"+"Date Debut:"+DD.getValue().toString()+"\n"+"Date Fin:"
                    +DF.getValue().toString()).
                    header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).getAsJsonMap();
                }}
        });
    
        this.rootContainer.removeAll();
        this.rootContainer.add(BorderLayout.NORTH,l);
        this.rootContainer.revalidate();
        }

}