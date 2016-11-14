package com.mobile.eseo.neweseoapp.model;

/**
 * Created by etudiant on 19/10/2016.
 */

public class Enseignant {
    private int id;
    private String nom;
    private String prenom;
    private String fonction;
    private String email;
    private long numero;

    public Enseignant(){}

    public Enseignant(String nom, String prenom, String fonction, String email, long numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
        this.email = email;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

