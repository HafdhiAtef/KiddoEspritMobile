/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entity;

/**
 *
 * @author TR3x
 */
public class Reclamation {
    int id;
    String titre;
    String contenu;
    int etat;
    int id_user;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Reclamation(int id, String titre, String contenu, int etat, int id_user) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.etat = etat;
        this.id_user = id_user;
    }
    public Reclamation(){
        
    }
}
