package com.mobile.eseo.neweseoapp.model;

/**
 * Created by etudiant on 19/10/2016.
 */

public class PFE {
    private int ID;
    private String titre;
    private Enseignant enseignant;
    private String encadrant;
    private String problematique;

    public PFE() {}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public String getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(String encadrant) {
        this.encadrant = encadrant;
    }

    public String getProblematique() {
        return problematique;
    }

    public void setProblematique(String problematique) {
        this.problematique = problematique;
    }
}
