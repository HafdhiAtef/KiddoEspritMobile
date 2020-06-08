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
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
/**
 *
 * @author TR3x
 */
public class listeatelier {
    Form f;
    
    public listeatelier(int userid){
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
          int count;
          count =  serviceAtelier.verifierinscrit(userid,a.getId());
          //System.out.println("1 if user already subscribed 0 if not "+count);
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
            
            Button consulter = new Button("Consulter");
           
            
            
           // informer.setIcon(FontImage.create(FontImage.MATERIAL_SUBSCRIPTIONS, informer.getUnselectedStyle(), materialFont));
            Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
           
            //cnt.addComponent(informer);
            f.add(consulter);
            
      
            consulter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   
               Form f1;
                f1 = new Form(""+a.getNom()+" : ", new BoxLayout(BoxLayout.Y_AXIS));
               f1.getToolbar().addMaterialCommandToRightBar("",FontImage.MATERIAL_ARROW_BACK , (ev) -> {
        listeatelier h = new listeatelier(userid);
      
      h.getF().show();
      });
               TextField number;
               
               Image img1 = null;
            try {
                img1 = Image.createImage("/"+ a.getPath()); 
            } catch (IOException ex) {

            }
            img1.scale(mm * 20, mm * 20);
            f1.add(img1);
            Label nom = new Label(a.getNom());
            Label datedeb = new Label(a.getDate_debut());
            Label datefin = new Label(a.getDate_fin());
            
            
            nom.getAllStyles().setFgColor(0x8c1010);
            datedeb.getAllStyles().setFgColor(0x9411);
            datefin.getAllStyles().setFgColor(0x9411);
            
            SpanLabel desc = new SpanLabel(a.getDescription());
             f1.add(nom);
             f1.add(datedeb);
             f1.add(datefin);
             Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
                    nom.getAllStyles().setFont(mediumPlainSystemFont);
            f1.add(desc);
            
            desc.getAllStyles().setFont(mediumPlainSystemFont);
            
            
            Font materialFont = FontImage.getMaterialDesignFont();
            int size = Display.getInstance().convertToPixels(6, true);
            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            if(count!=1){
                
            
            
               number = new TextField("", "+216");
            Button informer = new Button("s'abonner");
            f1.add(number);
            f1.add(informer);
            f1.show();
               informer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    /*     int i=Integer.parseInt(number.getText());*/
                    serviceAtelier.mail(number.getText(),a.getNom(),a.getDate_debut());
                    ServiceAtelier.upmail(a.getId(),userid);
    Dialog dlg = new Dialog("Abonement");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
   // title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("Un   Message d'authentification a été envoyé , priére de vérifier votre tél");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    dlg.showDialog();
    listeatelier h = new listeatelier(userid);
    h.getF().show();
                }});
            
                }
             else if(count==1){
                Label already = new Label("Vous étes déja inscrit a cette atelier");
                already.getAllStyles().setFgColor(0x3699);
                f1.add(already);
                f1.show();
            }
           
                }
            });
            
          
          
                
                    
                  
                    
                    
                    
                    
             

                

               
            
      
            
      
            
                 
      
      
        
                                    }
      f.getToolbar().addMaterialCommandToRightBar("",FontImage.MATERIAL_ARROW_BACK , (ev) -> {
        
      HomePage h = new HomePage(userid);
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
