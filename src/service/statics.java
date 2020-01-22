/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import controller.MapsDemo;


import java.io.IOException;
//import com.mycompany.myapp.MyApplication1;
//import com.mycompany.myapp.MyApplication2;

/**
 *
 * @author Amal
 */
public class statics {
       
    public static void setLabelStyle(Label l){
        l.getUnselectedStyle().setFgColor(-16777216);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
    }
     
     public static Button createBackBtn(Resources res){
         Button b=new Button("Back");
         b.getUnselectedStyle().setFgColor(5542241);
         b.addActionListener((ActionListener) (ActionEvent evt) -> {
            
         });
         return b;
     }
     public static Button createBackBtn2(){
         Button b=new Button("Back");
         b.getUnselectedStyle().setFgColor(5542241);
         b.addActionListener((ActionListener) (ActionEvent evt) -> {
   MapsDemo maCarte = new MapsDemo();
                maCarte.start(); 
         });
         return b;
     }
    
}