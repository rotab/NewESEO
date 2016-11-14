package com.mobile.eseo.neweseoapp.model;

/**
 * Created by etudiant on 19/10/2016.
 */

public class PFE {
    private int Id;
    private String titre;
    private int idEncadrant;
    private String problematique;

    public PFE() {}

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }



    public PFE(String titre, int idEncadrant, String problematique) {
        this.titre = titre;
        this.idEncadrant = idEncadrant;
        this.problematique = problematique;
    }


    public int getEncadrant() {
        return idEncadrant;
    }

    public void setEncadrant(int idEncadrant) {
        this.idEncadrant = idEncadrant;
    }

    public String getProblematique() {
        return problematique;
    }

    public void setProblematique(String problematique) {
        this.problematique = problematique;
    }
}
