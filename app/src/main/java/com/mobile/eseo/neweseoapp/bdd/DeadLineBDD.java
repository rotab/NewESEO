/**
 * Created by WatchYourStep on 17/10/2016.
 */

package com.mobile.eseo.neweseoapp.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.eseo.neweseoapp.model.DeadLine;

public class DeadLineBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "eleves.db";

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
        //on insÃ¨re l'objet dans la BDD via le ContentValues
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

   /* public DeadLine getDeadLineWithId(int id){
        //RÃ©cupÃ¨re dans un Cursor les valeurs correspondant Ã  un livre contenu dans la BDD (ici on sÃ©lectionne la deadline grÃ¢ce Ã  son id)
        Cursor c = bdd.query(TABLE_DEADLINE, new String[] {COL_ID, COL_MOTIF, COL_LIMITE}, COL_ID + " = " + id, null, null, null, null);
        return cursorToDeadLine(c);
    }*/


    public DeadLine getDeadLineWithLimite(String l){
        //RÃ©cupÃ¨re dans un Cursor les valeurs correspondant Ã  un livre contenu dans la BDD (ici on sÃ©lectionne le livre grÃ¢ce Ã  son titre)
        Cursor c = bdd.query(TABLE_DEADLINE, new String[] {COL_ID, COL_MOTIF, COL_LIMITE}, COL_LIMITE + " LIKE \"" + l +"\"", null, null, null, null);
        return cursorToDeadLine(c);
        //return null;
    }


    //Cette mÃ©thode permet de convertir un cursor en un livre
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
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return deadline;
    }
}