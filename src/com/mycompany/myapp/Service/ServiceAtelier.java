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
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
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

    public static void upmail(int id, int userid) {
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/KiddoEsprit/web/app_dev.php/inscription?iduser=" + userid + "&atelier=" + id  ;
        con.setUrl(Url);
        
        con.addResponseListener((l) -> {
        String str = new String(con.getResponseData());//Récupération de la réponse du serveur
        System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
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
    
    public int  parseListTaskJson1(String json){
         int i = 0;
        //ArrayList<Atelier> listAtelier = new ArrayList<>();
        
        try{
               
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
            System.out.println("membre inscrit"+i);        
        return i;
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
    
    public void mail(String i ,String nom , String date){
        
        
        System.out.println(i);
        System.out.println(nom);
        System.out.println(date);
        Response<Map> v = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + "AC44682edf70461132f1b1085710008078" + "/Messages.json").
        queryParam("To", "+216"+i).
        queryParam("From", "+12055578744").
        queryParam("Body", "Bonjour , Vous etes abonnéé a l'atlier "+nom+" prévu pour la date : "+date+"  merci d'étre présent le jour de l'atelier").
        basicAuth("AC44682edf70461132f1b1085710008078", "9fe77126fccc94b867fb5b5c309402bd").//header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
        getAsJsonMap();
        
       
    }
    int count;
    public int verifierinscrit(int userid, int id) {
      ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/KiddoEsprit/web/app_dev.php/verifmobile?userid="+userid+"&atelier="+id);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                count = parseListTaskJson1(new String(con.getResponseData()));
                con.removeResponseListener(this);
                                    
            }
         });
        NetworkManager.getInstance().addToQueueAndWait(con);
      
        return count; 
    }
        
    
    
    
    
}
