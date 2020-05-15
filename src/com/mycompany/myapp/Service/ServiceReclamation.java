/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.e;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.mycompany.myapp.Entity.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author TR3x
 */
public class ServiceReclamation {
   /* public ArrayList<Reclamation> parseListTaskJson(String json) throws IOException{
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            
            for (Map<String, Object> obj : list){
                Reclamation r = new Reclamation();
                
                float id = Float.parseFloat(obj.get("id").toString());
                float etat = Float.parseFloat(obj.get("etat").toString());
                float user= Float.parseFloat(obj.get("id_user").toString());
                
                r.setTitre(obj.get("titre").toString());
                r.setId((int) id);
                r.setContenu(obj.get("contenu").toString());
                r.setId_user((int) user);
                
                listReclamation.add(r);
                System.out.println(r);
            } 
                
            }
            catch (IOException ex){
                    
                    }
            System.out.println(listReclamation);
                    return listReclamation;
        }*/
        
        public void ajouterReclamation(Reclamation r){
            ConnectionRequest con = new ConnectionRequest();
            String Url = "http://localhost/KiddoEsprit/web/app_dev.php/ajoutermobile?titre=" + r.getTitre()+ "&contenu=" + r.getContenu()  ;
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((l) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            
        }
    }
    
    
    

