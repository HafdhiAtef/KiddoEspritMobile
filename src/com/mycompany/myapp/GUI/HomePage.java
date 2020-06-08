/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
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
    public class HomePage {
    Form Home;
    Form pageForum;
    Form login;
    Form hi;
        
        public HomePage(int username){
         Home = new Form("Accueil", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tbh = Home.getToolbar();
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

        Label lab = new Label("\n    Bienvenue à Kiddo");
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
        //lab.getAllStyles().setFgColor(CENTER);
        Home.add(lab);

        int mm = Display.getInstance().convertToPixels(3);
        Image img = null;
        try {
            img = Image.createImage("/banner_06.jpg");
        } catch (IOException ex) {

        }
        img.scale(mm * 20, mm * 12);
        //img.scaledWidth(100);
        //img.scaledHeight(100);
        Home.add(img);

        /**
         * **************************************************************************************************************************************************
         */
        Label ffs = new Label(" à propos de nous ");
        ffs.getAllStyles().setFont(mediumPlainSystemFont);

        ffs.getAllStyles().setFgColor(0x3399FF);
        Home.add(ffs);
        Container ccc = new Container(new BoxLayout(BoxLayout.X_AXIS));
        int m = Display.getInstance().convertToPixels(3);
        Image img2 = null;
        try {
            img2 = Image.createImage("/blog_02.jpg");
        } catch (IOException ex) {

        }
        img2.scale(m * 10, m * 17);
        ccc.add(img2);
        SpanLabel t = new SpanLabel("kiddo est la premiére platforme tunisienne qui gére les jardins d'enfants "
                + " Vous pouvez tout faire ici , de consulter le bilan de votre enfant a l'inscription aux differentes ateliers"
                //+ " et la culture sont interdépendantes et se nourrissent ..."
        );

        t.getAllStyles().setFont(mediumPlainSystemFont);
        t.getAllStyles().setFgColor(0x33FFCC);
        ccc.add(t);

        Home.add(ccc);

        /**
         * **************************************************************************************************************************************************
         */
        Home.add(new Label(""));
        Home.add(new Label(""));
        Home.add(new Label(""));
        /**
         * **************************************************************************************************************************************************
         */
        Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label titre = new Label("       Programme du mois");
//        Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        titre.getAllStyles().setFont(mediumPlainSystemFont);
        titre.getAllStyles().setFgColor(0x3399FF);
        cc.add(titre);
        int mmm = Display.getInstance().convertToPixels(3);
        Image img1 = null;
        try {
            img1 = Image.createImage("/calendrier.JPG");
        } catch (IOException ex) {

        }
        img1.scale(mmm * 20, mmm * 10);
        cc.add(img1);

        Home.add(cc);

        /**
         * **************************************************************************************************************************************************
         */
        Label con = new Label("Contactez nous :");
        con.getAllStyles().setFont(mediumPlainSystemFont);
        con.getAllStyles().setFgColor(0x006699);
        Home.add(con);

        Home.add(new Label("Adresse : Rue Mohamed V, Tunis "));
        Home.add(new Label("E-mail : contact.Kiddo@gmail.com"));
        Home.add(new Label("Fax : (+200) 70028312"));

        Container sm = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label fb = new Label("Suivez nous : ");
        fb.getAllStyles().setFont(mediumPlainSystemFont);
        fb.getAllStyles().setFgColor(0x006699);
        sm.add(fb);
        int x = Display.getInstance().convertToPixels(3);
        Image img3 = null;
        try {
            img3 = Image.createImage("/fb.png");
        } catch (IOException ex) {

        }
        img3.scale(x * 6, x * 5);
        sm.add(img3);

        Home.add(sm);

        /**
         * **************************************************************************************************************************************************
         */
        tbh.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            }
        });
        
        tbh.addMaterialCommandToSideMenu("Réclamation", FontImage.MATERIAL_BUILD, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //HomeForm f = new HomeForm();
                System.out.println("username when accessing recform"+username);
                ReclamationForm recform = new ReclamationForm(username);
                recform.getF().show();
            }
        });

        tbh.addMaterialCommandToSideMenu("Nos Atelier", FontImage.MATERIAL_EXTENSION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               listeatelier afform = new listeatelier(username);
               afform.getF().show();
            }
        });

        

     

        tbh.addMaterialCommandToSideMenu("Visitez Notre Site", FontImage.MATERIAL_EXPLORE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form hi = new Form("Browser", new BorderLayout());
BrowserComponent browser = new BrowserComponent();
//browser.setURL("http://www.pornhub.com");
browser.setURL("http://127.0.0.1:8000/inscrireAtelier1");
hi.add(BorderLayout.CENTER, browser);
hi.show();
hi.getToolbar().addMaterialCommandToRightBar("",FontImage.MATERIAL_KEYBOARD_BACKSPACE , (ev) -> {
      HomePage h = new HomePage(username);
      h.getF().show();
      });
            }
        });
/*
       
          tbh.addMaterialCommandToSideMenu("Consulter Reservation: Salle",FontImage.MATERIAL_EVENT_NOTE,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                 MesSalles ser = new MesSalles();
       
                 ser.mesSalles();
              
            }
        }
          );
           
      
            
         
*/         Image i = null;
        try {
            i = Image.createImage("/exit.png");
        } catch (IOException ex) {

        }
        i.scale(m * 1, m * 1);
        tbh.addCommandToOverflowMenu("Déconnexion", i, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService.setLoggedInUser(null);
                Login logform = new Login();
                login = logform.getF();
                login.show();
            }
        });
 
        Home.show();
    }

    public Form getF() {
        return Home;
    }

    public void setF(Form f) {
        this.Home = f;
    }   
            
            
            
            
        }

