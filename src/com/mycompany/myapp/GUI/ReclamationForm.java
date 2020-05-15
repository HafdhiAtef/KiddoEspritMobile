/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.Entity.Reclamation;
import com.mycompany.myapp.Service.ServiceReclamation;
import java.io.IOException;

/**
 *
 * @author TR3x
 */
public class ReclamationForm {
    Form f;
    TextField titre;
    TextField contenu;
    Picker datepick;
    
    Button btnvalider;
    ComboBox<String> combotype;
    Form homepage;
    String msg = "";
    String msg1 = "";
    String msg2 = "";
    String msg3 = "";
    String msg4 = "";
    
    int verif  = 0;
    
    public ReclamationForm(){
        f = new Form("Nous réclamer", new BoxLayout(BoxLayout.Y_AXIS));
        titre = new TextField("", "Titre");
        contenu = new TextField("", "Contenu");
        btnvalider = new Button("Valider");
        int mm = Display.getInstance().convertToPixels(3);
        Image img = null;
        try {
            img = Image.createImage("/reclamation.png");
        } catch (IOException ex) {

        }
        img.scale(mm * 20, mm * 12);
        //img.scaledWidth(100);
        //img.scaledHeight(100);
        f.add(img);
        f.add(titre);
        f.add(contenu);
        f.add(btnvalider);
        
        btnvalider.addActionListener((l) -> {
            Reclamation r = new Reclamation();
            
            r.setTitre(titre.getText());
            r.setContenu(contenu.getText());
            
          // r.setEtat(0);
           
            ServiceReclamation s = new ServiceReclamation();
           if (titre.getText().length() < 5) {
                msg1 = "Vérifiez le Titre";
                verif = 1;
            }
           if (contenu.getText().length() < 5) {
                msg2 = "Vérifiez le Contenu";
                verif = 1;
            }
           if (verif == 1) {
                
                msg4 = msg1 + "\n" + msg2 ;
                Dialog.show("Erreur", msg4, "OK", "Quitter");
                msg4=" ";
                msg1=" ";
                msg2=" ";
                msg3=" ";
                verif=0;
            }else {
               s.ajouterReclamation(r);
               HomePage h = new HomePage();
               h.getF().show();
               
           }
            
        });
        f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
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
