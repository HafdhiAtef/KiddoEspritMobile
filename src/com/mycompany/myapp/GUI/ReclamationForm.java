/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.Entity.Reclamation;
import com.mycompany.myapp.Entity.User;
import com.mycompany.myapp.Service.ServiceReclamation;
import com.mycompany.myapp.Service.UserService;
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
    int count=0;
    int verif  = 0;
    int id;
    public ReclamationForm(int a){
        
        
        
        ServiceReclamation service= new ServiceReclamation();
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
        
        
        
        
        
        count = service.Recuperercount(a);
        System.out.println("Count reclamation for user : "+a+" = "+count);     
        
        if (count==3){
        //f = new Form("Nous réclamer", new BoxLayout(BoxLayout.Y_AXIS));    
            Dialog dlg = new Dialog("Erreurs!");
                    dlg.setLayout(new BorderLayout());
                    dlg.setDialogType(Dialog.TYPE_WARNING);
                    dlg.add(BorderLayout.CENTER,new SpanLabel("Veuillez avez dépasser la limite des réclamations possible", "DialogBody"));
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.show();
      
            System.out.println("count MAX before heading to homepage");
            HomePage x = new HomePage(a);
            x.getF().show();   
        
   HomePage home = new HomePage(a);
            System.out.println("when out recform id = "+a);
               home.getF().show();
        }
        else{  
            f = new Form("Nous réclamer", new BoxLayout(BoxLayout.Y_AXIS));
           f.add(img);
        f.add(titre);
        f.add(contenu);
        f.add(btnvalider);
            f.show();
        
        
        btnvalider.addActionListener((l) -> {
            Reclamation r = new Reclamation();
            
           
            
            r.setTitre(titre.getText());
            r.setContenu(contenu.getText());
            
            //r.setId(id);
            System.out.println("set user id reclamation = "+a);
            r.setId_user(a);
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
               HomePage home = new HomePage(a);
               home.getF().show();
               
           }
            
        });
        f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
            HomePage h = new HomePage(a);
            h.getF().show();
        });
        
        
        }
        }  
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
    
}
