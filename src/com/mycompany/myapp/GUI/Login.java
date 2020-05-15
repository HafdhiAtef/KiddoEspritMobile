/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Service.UserService;
import java.io.IOException;

/**
 *
 * @author TR3x
 */
public class Login {
    Form f;
    Form homepage;
    Form pageForum;
    SpanLabel lb;
    TextField unf;
    TextField pf;
    Button valider;
    Button inscription;
    Image logo;
    
    public Login(){
        
        f = new Form("login",new BoxLayout(BoxLayout.Y_AXIS));
        unf = new TextField("", "Username");
        pf = new TextField("", "Password");
        pf.setConstraint(TextArea.PASSWORD);
        valider = new Button("Se connecter");
        inscription = new Button("S'inscrire");
        
        inscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Inscription inscription = new Inscription();
            }
        });
        
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService us = new UserService();
                if(UserService.recupererUser(unf.getText(),pf.getText())== 0){
                    HomePage affForm = new HomePage();
                    homepage = affForm.getF();
                    homepage.show();
                    
                }
              else{
                    Dialog dlg = new Dialog("Erreurs!");
                    dlg.setLayout(new BorderLayout());
                    dlg.setDialogType(Dialog.TYPE_WARNING);
                    dlg.add(BorderLayout.CENTER,new SpanLabel("Veuillez vérifier votre username ou mot de passe.", "DialogBody"));
                    int h = Display.getInstance().getDisplayHeight();
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.show();
                }
            }
        });
        int mm = Display.getInstance().convertToPixels(3);
        logo = null;
        try {
            logo = Image.createImage("/gallery_06.jpg");
        } catch (IOException ex) {   
        }
        logo.scale(mm*20, mm*22);
        f.add(logo);
      f.add(unf);
        f.add(pf);
        f.add(valider);
        f.add(inscription);
        f.show();  
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
