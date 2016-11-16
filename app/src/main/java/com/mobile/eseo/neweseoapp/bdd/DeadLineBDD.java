/**
 * Created by WatchYourStep on 17/10/2016.
 */

package com.mobile.eseo.neweseoapp.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.eseo.neweseoapp.model.DeadLine;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Boolean.FALSE;

public class DeadLineBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "deadline.db";

    private static final String TABLE_DEADLINE = "table_DeadLine";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_MOTIF = "Motif";
    private static final int NUM_COL_MOTIF = 1;
    private static final String COL_LIMITE = "Limite";
    private static final int NUM_COL_LIMITE = 2;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public DeadLineBDD(Context context){
        //On crÃ©e la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en Ã©criture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accÃ¨s Ã  la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertDeadLine(DeadLine deadline){
        //CrÃ©ation d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associÃ©e Ã  une clÃ© (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_MOTIF, deadline.getMotif());
        values.put(COL_LIMITE, deadline.getLimite());
        return bdd.insert(TABLE_DEADLINE, null, values);
    }

    public int updateDeadLine(int id, DeadLine deadline){
        //La mise Ã  jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement prÃ©ciser quel livre on doit mettre Ã  jour grÃ¢ce Ã  l'ID
        ContentValues values = new ContentValues();
        values.put(COL_MOTIF, deadline.getMotif());
        values.put(COL_LIMITE, deadline.getLimite());
        return bdd.update(TABLE_DEADLINE, values, COL_ID + " = " +id, null);
    }

    public int removeDeadLineWithID(int id){
        //Suppression d'un livre de la BDD grÃ¢ce Ã  l'ID
        return bdd.delete(TABLE_DEADLINE, COL_ID + " = " +id, null);
    }

    public void deleteAll()
    {
        bdd.delete(TABLE_DEADLINE, null, null);
    }


    //Renvoie les deadline pas dépassées
    public ArrayList<DeadLine> getDeadLineWithDate () throws ParseException {
        ArrayList<DeadLine> listeDeadLines = new ArrayList<>();
            int i;
            java.util.Date dateJour = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy hh:mm:ss");

            Cursor c = bdd.query(TABLE_DEADLINE, new String[] {COL_ID, COL_MOTIF, COL_LIMITE}, null, null, null, null, null);

            c.moveToFirst();
            DeadLine deadline = new DeadLine();
            deadline.setId(c.getInt(NUM_COL_ID));
            deadline.setMotif(c.getString(NUM_COL_MOTIF));
            deadline.setLimite(c.getString(NUM_COL_LIMITE));

            Date date = null;
            try {
                date = sdf.parse(deadline.getLimite());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (dateJour.before(date)) {
                listeDeadLines.add(deadline);
            }

            for (i=1;i<c.getCount();i++) {
                DeadLine deadlineTest=cursorToDeadLineTest(c);
                if (deadlineTest!=null) {
                    listeDeadLines.add(deadlineTest);
                }
            }

        //On ferme le cursor
        c.close();
        return listeDeadLines;
    }

    //Renvoie les deadline des prochaines 24h
    public ArrayList<DeadLine> getDeadLineOfDay () throws ParseException {
        ArrayList<DeadLine> listeDeadLines = new ArrayList<>();
        int i;
        java.util.Date dateJour = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy hh:mm:ss");

        Cursor c = bdd.query(TABLE_DEADLINE, new String[] {COL_ID, COL_MOTIF, COL_LIMITE}, null, null, null, null, null);

        c.moveToFirst();
        DeadLine deadline = new DeadLine();
        deadline.setId(c.getInt(NUM_COL_ID));
        deadline.setMotif(c.getString(NUM_COL_MOTIF));
        deadline.setLimite(c.getString(NUM_COL_LIMITE));

        Date date = null;
        try {
            date = sdf.parse(deadline.getLimite());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateJour.before(date)) {
            listeDeadLines.add(deadline);
        }

        for (i=1;i<c.getCount();i++) {
            DeadLine deadlineTest=cursorToDeadLineOfDayTest(c);
            if (deadlineTest!=null) {
                listeDeadLines.add(deadlineTest);
            }
        }

        //On ferme le cursor
        c.close();
        return listeDeadLines;
    }


    private DeadLine cursorToDeadLineTest(Cursor c) {

        java.util.Date dateJour = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy hh:mm:ss");

        c.moveToNext();
        DeadLine deadline2 = new DeadLine();
        deadline2.setId(c.getInt(NUM_COL_ID));
        deadline2.setMotif(c.getString(NUM_COL_MOTIF));
        deadline2.setLimite(c.getString(NUM_COL_LIMITE));


        Date date = null;
        try {
            date = sdf.parse(deadline2.getLimite());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateJour.before(date)) {
            return deadline2;
        } else {
            return null;
        }

    }

    private DeadLine cursorToDeadLineOfDayTest(Cursor c) {

        java.util.Date dateJour = new java.util.Date();
        java.util.Date dateLendemain = new java.util.Date();
        dateLendemain.setTime(dateJour.getTime()+86400000);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy hh:mm:ss");

        c.moveToNext();
        DeadLine deadline2 = new DeadLine();
        deadline2.setId(c.getInt(NUM_COL_ID));
        deadline2.setMotif(c.getString(NUM_COL_MOTIF));
        deadline2.setLimite(c.getString(NUM_COL_LIMITE));


        Date date = null;
        try {
            date = sdf.parse(deadline2.getLimite());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateJour.before(date) && dateLendemain.after(date)) {
            return deadline2;
        } else {
            return null;
        }

    }



    public DeadLine getDeadLineWithLimite(String l){
        //RÃ©cupÃ¨re dans un Cursor les valeurs correspondant Ã  un livre contenu dans la BDD (ici on sÃ©lectionne le livre grÃ¢ce Ã  son titre)
        Cursor c = bdd.query(TABLE_DEADLINE, new String[] {COL_ID, COL_MOTIF, COL_LIMITE}, COL_LIMITE + " LIKE \"" + l +"\"", null, null, null, null);
        return cursorToDeadLine(c);
        //return null;
    }


    //Cette mÃ©thode permet de convertir un cursor en une date limite
    private DeadLine cursorToDeadLine(Cursor c){
        //si aucun Ã©lÃ©ment n'a Ã©tÃ© retournÃ© dans la requÃªte, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier Ã©lÃ©ment
        c.moveToFirst();
        //On crÃ©Ã© un livre
        DeadLine deadline = new DeadLine();
        //on lui affecte toutes les infos grÃ¢ce aux infos contenues dans le Cursor
        deadline.setId(c.getInt(NUM_COL_ID));
        deadline.setMotif(c.getString(NUM_COL_MOTIF));
        deadline.setLimite(c.getString(NUM_COL_LIMITE));


        //On retourne le livre
        return deadline;
    }


}