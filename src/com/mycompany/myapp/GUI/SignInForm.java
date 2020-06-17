/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package Form;

import Entities.User;
import Services.UserService;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {
 public static User recupUser;
 Form curant;
         public SignInForm(Resources res) {
        super(new BorderLayout());
        curant = this;

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");

       // add(BorderLayout.NORTH, new Label(res.getImage("Imageback.jpg"), "LogoLabel"));

        TextField username = new TextField("", "Nom d'utilisateur", 20, TextField.ANY);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Se connecter");

        signIn.addActionListener(new ActionListener() {
            User user = new User();

            @Override
            public void actionPerformed(ActionEvent evt) {

                UserService userService = new UserService();
                System.out.println(username.getText() + "/n" + password.getText());
                //userService.check(username.getText(), password.getText());
                // = userService.check(usern,pass) ;

                signIn.addActionListener(e -> {
                    String passswordText = password.getText();
                    String usernameText = username.getText();
                    // signIn.requestFocus();
                    String mdp = BCrypt.hashpw(passswordText, BCrypt.gensalt(13));
                    //mdp = mdp.replaceFirst("2a", "2y");
                     //System.out.print("CHECK : "+test);
                    //String mdp = userService.cryptPassword(password.getText());

                    user = userService.getUser(username.getText());
                    recupUser = user; 

                    String motDePasseBD = user.getPassword();
                    char[] tab = motDePasseBD.toCharArray();
                    tab[2] = 'a';
                    motDePasseBD = String.valueOf(tab);
                    boolean c = BCrypt.checkpw(passswordText, motDePasseBD);
                    System.out.println("CH 1 /:" + c);

                    System.out.println(usernameText.equals(user.getUsername()));
                    System.out.println("Username : " + user.getUsername());
                    System.out.println("Username : " + usernameText);
                    System.out.println("Passsword: " + user.getPassword());
                    System.out.println("Passsword: " + mdp);

                    if (usernameText.equals(user.getUsername()) && BCrypt.checkpw(passswordText, motDePasseBD)) {
                        if (user.getEnabled() == 0) {
                            Dialog.show("Attention", "Ce compte a été désactiver", "OK", null);
                        } else {
                            if(user.getRole().equals("[ROLE_PARENT, ROLE_Client]")){
                                System.out.println("Vous ete connecter en tant que parent") ;
                                new NewsfeedForm(res).show();
                            }else{
                            System.out.println("Vous ete connecter en tant que responsable enseignant"+user.getRole()) ;
                            new acceuil(curant).show();
                            }
                        }
                       
                    } else {
                        Dialog.show("Attention", "Verifier les  coordonnées", "OK", null);
                    }

                }
                );

                //userService.sendEmail() ;
            }
        });

        Button signUp = new Button("Inscription");
        signUp.addActionListener(e -> new SignUpForm().show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Vous n'avez pas de compte?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);

    }

}
