package com.mobile.eseo.neweseoapp.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.eseo.neweseoapp.model.Enseignant;
import com.mobile.eseo.neweseoapp.model.PFE;
import com.mobile.eseo.neweseoapp.bdd.EnseignantBDD;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class PFEBDD {


    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "pfe.db";

    private static final String TABLE_PFE = "table_PFE";
    private static final String COL_ID = "Id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TITRE = "Titre";
    private static final int NUM_COL_TITRE = 1;
    private static final String COL_ENCADRANT = "Encadrant";
    private static final int NUM_COL_ENCADRANT = 2;
    private static final String COL_PROBLEMATIQUE = "Problematique";
    private static final int NUM_COL_PROBLEMATIQUE = 3;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public PFEBDD(Context context){
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

    public long insertPFE(PFE pfe){

        ContentValues values = new ContentValues();

        values.put(COL_TITRE, pfe.getTitre());
        values.put(COL_ENCADRANT, pfe.getEncadrant());
        values.put(COL_PROBLEMATIQUE, pfe.getProblematique());
        //on insÃ¨re l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_PFE, null, values);
    }


    public int updatePFE(int id, PFE pfe){
        //La mise Ã  jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement prÃ©ciser quel livre on doit mettre Ã  jour grÃ¢ce Ã  l'ID
        ContentValues values = new ContentValues();
        values.put(COL_TITRE, pfe.getTitre());
        values.put(COL_ENCADRANT, pfe.getEncadrant());
        values.put (COL_PROBLEMATIQUE, pfe.getProblematique());
        return bdd.update(TABLE_PFE, values, COL_ID + " = " +id, null);
    }

    public int removePFEWithID(int id){
        //Suppression d'un livre de la BDD grÃ¢ce Ã  l'ID
        return bdd.delete(TABLE_PFE, COL_ID + " = " +id, null);
    }

    public void deleteAll()
    {
        bdd.delete(TABLE_PFE, null, null);
    }



    public ArrayList<PFE> getAllPFE () throws ParseException {
        ArrayList<PFE> listePFE = new ArrayList<>();
        int i;
        Cursor c = bdd.query(TABLE_PFE, new String[] {COL_ID, COL_TITRE, COL_ENCADRANT, COL_PROBLEMATIQUE} , null , null, null, null, null);

        c.moveToFirst();
        PFE pfe = new PFE();
        pfe.setId(c.getInt(NUM_COL_ID));
        pfe.setTitre(c.getString(NUM_COL_TITRE));
        pfe.setEncadrant(c.getInt(NUM_COL_ENCADRANT));
        pfe.setProblematique(c.getString(NUM_COL_PROBLEMATIQUE));

        listePFE.add(pfe);

        for (i=1;i<c.getCount();i++) {
            PFE pfe2=cursorToPFE(c);
            if (pfe2!=null) {
                listePFE.add(pfe2);
            }
        }
        c.close();
        return listePFE;
    }


    //Cette mÃ©thode permet de convertir un cursor en un livre
    public PFE cursorToPFE(Cursor c){

        //si aucun Ã©lÃ©ment n'a Ã©tÃ© retournÃ© dans la requÃªte, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier Ã©lÃ©ment
        c.moveToNext();
        //On crÃ©Ã© un livre
        PFE pfe = new PFE();
        //on lui affecte toutes les infos grÃ¢ce aux infos contenues dans le Cursor
        pfe.setId(c.getInt(NUM_COL_ID));
        pfe.setTitre(c.getString(NUM_COL_TITRE));
        pfe.setProblematique(c.getString(NUM_COL_PROBLEMATIQUE));
        pfe.setEncadrant(c.getInt(NUM_COL_ENCADRANT));

        //On retourne le livre
        return pfe;
    }

}