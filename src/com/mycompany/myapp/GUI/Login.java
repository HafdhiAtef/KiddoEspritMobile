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
//import java.util.logging.Level;
//import java.util.logging.Logger;

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
    Button valider,fb,log,signup;
    Button inscription;
    Image logo;
    
    public Login(){
        
        f = new Form("login",new BoxLayout(BoxLayout.Y_AXIS));
        unf = new TextField("", "Username");
        pf = new TextField("", "Password");
        try {
            fb = new Button("Connectez avec Facebook", Image.createImage("/fb_2.png"));
        } catch (IOException ex) {
            
        }
       
        pf.setConstraint(TextArea.PASSWORD);
        
        log = new Button("Se connecter");
       signup = new Button("S'inscrire");
        
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Inscription inscription = new Inscription();
            }
        });
        fb.addActionListener(new ActionListener<ActionEvent>() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("connected with fb button clicked");
                    UserForm l = new UserForm();
                    l.show();
                }
            });
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService us = new UserService();
                int existe=UserService.recupererUser(unf.getText(),pf.getText());
                if(UserService.recupererUser(unf.getText(),pf.getText()) > 0){
                    System.out.println(unf.getText());
                    HomePage affForm = new HomePage(existe);
                    homepage = affForm.getF();
                    homepage.show();
                    
                }
                else {
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
        
        f.add(log);
        f.add(signup);
        f.add(fb);
        f.show();  
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
