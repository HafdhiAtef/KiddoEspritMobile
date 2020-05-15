/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;


public class Inscription {
     Form f;
    Form login;
    TextField Usernamef;
    TextField Emailf;
    TextField Passwordf;
    TextField ConfirmPassf;
    Button valider;
    Image logo;
    
    public Inscription(){
        
        f = new Form("Inscription",new BoxLayout(BoxLayout.Y_AXIS));
        valider = new Button("S'inscrire");
        Usernamef = new TextField("", "Username");
        Emailf = new TextField("", "Adresse E-Mail");
        
        Passwordf = new TextField("", "Mot de Passe");
        ConfirmPassf = new TextField("", "Confirmer votre Mot de passe");
        
        Emailf.setConstraint(TextArea.EMAILADDR);
        Passwordf.setConstraint(TextArea.PASSWORD);
        ConfirmPassf.setConstraint(TextArea.PASSWORD);
        
        Toolbar tb = f.getToolbar();
        tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Login logform = new Login();
                login = logform.getF();
                login.show();
            }
        });
        
        int mm = Display.getInstance().convertToPixels(3);
        logo = null;
        try {
            logo = Image.createImage("/signin.png");
        } catch (IOException ex) {   
        }
        logo.scale(mm*10, mm*10);
        Container c1 = new Container(new FlowLayout(CENTER,CENTER));
        c1.add(logo);
        f.add(c1);
        f.add(new Label("veuillez remplir le formaulre d'inscription"));
        f.add(Usernamef);
        f.add(Emailf);
        f.add(Passwordf);
        f.add(ConfirmPassf);
        f.add(valider);
        f.show();
    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
        
        
        
        
        
        
    }

