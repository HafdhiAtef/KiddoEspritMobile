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
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entity.Atelier;
import com.mycompany.myapp.Entity.Reclamation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author TR3x
 */
public class ServiceReclamation {
  public int  parseListTaskJson(String json){
         int i = 0;
        //ArrayList<Atelier> listAtelier = new ArrayList<>();
        
        try{
            /*JSONParser j = new JSONParser();
            Map<String,Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                      
            for (Map<String, Object> obj : list) {
                Atelier a = new Atelier();
                
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                a.setNom(obj.get("nom").toString());
                a.setDescription(obj.get("description").toString());
                a.setDate_debut(obj.get("date_debut").toString());
                a.setDate_fin(obj.get("date_fin").toString());
                a.setPath(obj.get("path").toString());
                
                listAtelier.add(a);
                System.out.println(a);
                System.out.println(a.getDate_debut());
                System.out.println(a.getNom());                       
                
            }
        */    
        //tasks = new ArrayList<>();
        JSONParser j = new JSONParser();
        
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)tasks.get("root");
        for(Map<String,Object> obj : list){
      
         float id = Float.parseFloat(obj.get("count").toString());
            
            i = (int)id;
         
                 
                 
        }    
            
            
        } catch (IOException ex){
                 }
                   
        return i;
      
    }
        
        public void ajouterReclamation(Reclamation r){
            ConnectionRequest con = new ConnectionRequest();
            String Url = "http://localhost/KiddoEsprit/web/app_dev.php/ajoutermobile?titre=" + r.getTitre()+ "&contenu=" + r.getContenu()+"&iduser="+r.getId_user()  ;
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((l) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
            
        }
        
        int a;
        public int Recuperercount(int id){
           
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/KiddoEsprit/web/app_dev.php/countrec?id="+id);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                a = parseListTaskJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
                                    
            }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return a;
    }
        
        
        
        
        
    }
    
    
    

