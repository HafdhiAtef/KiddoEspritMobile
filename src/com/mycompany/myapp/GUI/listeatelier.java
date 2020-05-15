/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.Entity.Atelier;
import com.mycompany.myapp.Service.ServiceAtelier;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Style;
/**
 *
 * @author TR3x
 */
public class listeatelier {
    Form f;
    
    public listeatelier(){
     Toolbar.setGlobalToolbar(true);
        
        Style s = UIManager.getInstance().getComponentStyle("Title");
        
     f = new Form("Liste des ateliers", new BoxLayout(BoxLayout.Y_AXIS));
     
     TextField searchField = new TextField("", "ToolBar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        f.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        searchField.addDataChangeListener((i1, i2) -> { // <2>
            String t = searchField.getText();

            if(t.length() < 1) {
        for(Component cmp : f.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
    } else {
        t = t.toLowerCase();
        for(Component cmp : f.getContentPane()) {
            String val = null;
            if(cmp instanceof Label) {
                val = ((Label)cmp).getText();
            } else {
                if(cmp instanceof TextArea) {
                    val = ((TextArea)cmp).getText();
                } else {
                    val = (String)cmp.getPropertyValue("text");
                }
            }
            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
            cmp.setHidden(!show); // <3>
            cmp.setVisible(show);
        }
    }
    f.getContentPane().animateLayout(250);
});
     
    
     
     
     
     
        ServiceAtelier serviceAtelier = new ServiceAtelier();
        
     int mm = Display.getInstance().convertToPixels(3);
        
      for (Atelier a : serviceAtelier.RecupererAteliers()){

            Image img = null;
            try {
                img = Image.createImage("/"+ a.getPath()); 
            } catch (IOException ex) {

            }
            img.scale(mm * 20, mm * 20);
            f.add(img);
            Label nom = new Label(a.getNom());
            nom.getAllStyles().setFgColor(0x8c1010);
            SpanLabel desc = new SpanLabel(a.getDescription());
             f.add(nom);
             Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
                    nom.getAllStyles().setFont(mediumPlainSystemFont);
            f.add(desc);
            
            desc.getAllStyles().setFont(mediumPlainSystemFont);
            
            
            Font materialFont = FontImage.getMaterialDesignFont();
            int size = Display.getInstance().convertToPixels(6, true);
            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            
            Button informer = new Button("s'abonner");
           
           // informer.setIcon(FontImage.create(FontImage.MATERIAL_SUBSCRIPTIONS, informer.getUnselectedStyle(), materialFont));
            Container cnt = new Container(new FlowLayout(CENTER));
            
            cnt.addComponent(informer);
            f.addComponent(cnt);
            
            /*MultiButton multi = new MultiButton(a.getNom());
          multi.setTextLine1(a.getDescription());
          multi.setTextLine2(a.getDate_debut());
          multi.setIcon(img);
          
          

          Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
          c.add(informer);
          
          f.add(multi);

          f.add(c);
*/
      }  
    
      
      
      
      
      
      
      f.getToolbar().addMaterialCommandToRightBar("",FontImage.MATERIAL_ARROW_BACK , (ev) -> {
      HomePage h = new HomePage();
      h.getF().show();
      });
      
        
    } 
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
