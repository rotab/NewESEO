/**
 * Created by WatchYourStep on 17/10/2016.
 */

package com.mobile.eseo.neweseoapp.model;

public class DeadLine {

    private int id;
    private String motif;
    private String limite;

    public DeadLine(){}

    public DeadLine(String motif, String limite){
        this.motif = motif;
        this.limite = limite;
    }

    public DeadLine(int id, String motif, String limite){
        this.id=id;
        this.motif = motif;
        this.limite = limite;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }
    public String getLimite() {
        return limite;
    }
    public void setLimite(String limite) {
        this.limite = limite;
    }
    public String toString(){
        return "ID : "+id+"\nMotif : "+motif+"\nLimite : "+limite;
    }
}
