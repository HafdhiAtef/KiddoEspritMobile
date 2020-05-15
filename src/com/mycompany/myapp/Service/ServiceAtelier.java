/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entity.Atelier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author TR3x
 */
public class ServiceAtelier {
    public ArrayList<Atelier> tasks;
   public static ServiceAtelier instance=null;
    public boolean resultOK;
   double tmestampDate,tmestampDate1;
    private ConnectionRequest req;
     public ServiceAtelier() {
         req = new ConnectionRequest();
    }
        public static ServiceAtelier getInstance() {
        if (instance == null) {
            instance = new ServiceAtelier();
        }
        return instance;
    }
    public ArrayList<Atelier>  parseListTaskJson(String json){
        
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
        tasks = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)tasks.get("root");
        for(Map<String,Object> obj : list){
        Atelier a = new Atelier();
         float id = Float.parseFloat(obj.get("id").toString());
            a.setId((int)id);
            Map<String,Object> dateCreationObj = (Map<String,Object>) obj.get("dateDebut");
                double tmestampDate = Double.parseDouble(dateCreationObj.get("timestamp").toString());
                long timeStampDateD = (long)tmestampDate * 1000;
                Date datedeb = new Date(timeStampDateD);
                a.setDate_debut(datedeb.toString());
            Map<String,Object> dateCreationObj1 = (Map<String,Object>) obj.get("dateFin");
                double tmestampDate1 = Double.parseDouble(dateCreationObj1.get("timestamp").toString());
                long timeStampDateF = (long)tmestampDate1 * 1000;
                Date datefin = new Date(timeStampDateF);
                a.setDate_fin(datefin.toString());
                 a.setNom(obj.get("nom").toString());
                a.setDescription(obj.get("description").toString());
                 a.setPath(obj.get("path").toString());
                 System.out.println(a+"\n ***********************");
                 
                 listAtelier.add(a);
        }    
            
            
        } catch (IOException ex){
                 }
            System.out.println(listAtelier);        
        return listAtelier;
      
    }
    
    ArrayList<Atelier> listAtelier = new ArrayList<>();
    public ArrayList<Atelier> RecupererAteliers(){
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/KiddoEsprit/web/app_dev.php/affichermobile");
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                listAtelier = parseListTaskJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
                System.out.println(listAtelier);                      
            }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAtelier;
    }
    
    
    
}
