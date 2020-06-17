/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entities.User;
import Services.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author yousra
 */
public class enseignentMenu extends Form {

    Form current;
    Form previous = Display.getInstance().getCurrent();

    public enseignentMenu(Form previous) {

        current = this;
        setTitle("Enseignents");
        setLayout(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        Form prev = Display.getInstance().getCurrent();
        tb.addMaterialCommandToLeftBar("back",
                FontImage.MATERIAL_ARROW_BACK, ev -> {
                    previous.showBack();
                });
        tb.addCommandToOverflowMenu("Exit",
                null, ev -> {
                    Display.getInstance().exitApplication();
                });
        Style loginStyle = getAllStyles();

        //loginStyle.setBgImage(MyApplication.theme.getImage("b2.jpg"));
        /**
         * ***********************************************************************************************
         */
        //ImageViewer Logo = new ImageViewer(MyApplication.theme.getImage("log.png"));
        /**
         * ***********************************************************************************************
         */
        Button btnval = new Button("Nouveau Enseignent");
        Style butStyle = btnval.getAllStyles();
        butStyle.setBorder(RoundRectBorder.create().
                strokeColor(0x3399ff).
                strokeOpacity(120)
        );
        butStyle.setBgColor(0x3399ff);
        butStyle.setFgColor(0xffffff);
        butStyle.setBgTransparency(255);
        butStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle.setMargin(Component.BOTTOM, 3);

        butStyle.setMargin(Component.TOP, 10);

        butStyle.setMargin(Component.LEFT, 10);
        butStyle.setMargin(Component.RIGHT, 10);
        /**
         * ***********************************************************************************************
         */
        Button btnval1 = new Button("Liste De Vos Enseignants");
        Style butStyle1 = btnval1.getAllStyles();
        butStyle1.setBorder(RoundRectBorder.create().
                strokeColor(0x3399ff).
                strokeOpacity(120)
        );
        butStyle1.setBgColor(0x3399ff);
        butStyle1.setFgColor(0xffffff);
        butStyle1.setBgTransparency(255);
        butStyle1.setMarginUnit(Style.UNIT_TYPE_DIPS);
        butStyle1.setMargin(Component.BOTTOM, 3);

        butStyle1.setMargin(Component.TOP, 5);

        butStyle1.setMargin(Component.LEFT, 10);
        butStyle1.setMargin(Component.RIGHT, 10);

        /**
         * ***********************************************************************************************
         */
        addAll(/*Logo,*/btnval, btnval1);

        /**
         * ***********************************************************************************************
         */
        btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new gestionEnseignat(current).show();
            }
        });
        btnval1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService uss = new UserService();
                ArrayList<User> listeNew = uss.getListEnseignat();
                new listeEnseignat(listeNew).show();
            }
        });

        /**
         * ***********************************************
         */
                        //Container cnt1=new Container(new FlowLayout(Container.CENTER));
        // add(Logo3);
    }
}
