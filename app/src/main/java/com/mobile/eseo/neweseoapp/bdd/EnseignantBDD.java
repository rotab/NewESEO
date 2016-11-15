package com.mobile.eseo.neweseoapp.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mobile.eseo.neweseoapp.model.Enseignant;
import java.text.ParseException;
import java.util.ArrayList;



public class EnseignantBDD {


    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "enseignant.db";

    private static final String TABLE_ENSEIGNANT = "table_Enseignant";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_PRENOM = "Prenom";
    private static final int NUM_COL_PRENOM = 1;
    private static final String COL_NOM = "Nom";
    private static final int NUM_COL_NOM = 2;
    private static final String COL_FONCTION = "Fonction";
    private static final int NUM_COL_FONCTION = 3;
    private static final String COL_EMAIL = "Email";
    private static final int NUM_COL_EMAIL = 4;
    private static final String COL_NUMERO = "Numero";
    private static final int NUM_COL_NUMERO = 5;


    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public EnseignantBDD(Context context){
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

    public long insertEnseignant(Enseignant enseignant){

        ContentValues values = new ContentValues();

        values.put(COL_PRENOM, enseignant.getPrenom());
        values.put(COL_NOM, enseignant.getNom());
        values.put(COL_FONCTION, enseignant.getFonction());
        values.put(COL_EMAIL, enseignant.getEmail());
        values.put(COL_NUMERO, enseignant.getNumero());

        return bdd.insert(TABLE_ENSEIGNANT, null, values);
    }

    public int updateEnseignant(int id, Enseignant enseignant){
        //La mise Ã  jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement prÃ©ciser quel livre on doit mettre Ã  jour grÃ¢ce Ã  l'ID
        ContentValues values = new ContentValues();
        values.put(COL_PRENOM, enseignant.getPrenom());
        values.put(COL_NOM, enseignant.getNom());
        values.put(COL_FONCTION, enseignant.getFonction());
        values.put (COL_EMAIL, enseignant.getEmail());
        values.put (COL_NUMERO, enseignant.getNumero());
        return bdd.update(COL_NUMERO, values, COL_ID + " = " +id, null);
    }

    public int removeEnseignantWithID(int id){
        //Suppression d'un livre de la BDD grÃ¢ce Ã  l'ID
        return bdd.delete(TABLE_ENSEIGNANT, COL_ID + " = " +id, null);
    }


    public void deleteAll()
    {
        bdd.delete(TABLE_ENSEIGNANT, null, null);
    }


    public ArrayList<Enseignant> getAllEnseignants () throws ParseException {
        ArrayList<Enseignant> listeEnseignant = new ArrayList<>();
        int i;
        Cursor c = bdd.query(TABLE_ENSEIGNANT, new String[] {COL_ID, COL_NOM, COL_PRENOM, COL_FONCTION, COL_EMAIL, COL_NUMERO} , null , null, null, null, null);

        c.moveToFirst();
        Enseignant enseignant =  new Enseignant();
        enseignant.setId(c.getInt(NUM_COL_ID));
        enseignant.setNom(c.getString(NUM_COL_NOM));
        enseignant.setPrenom(c.getString(NUM_COL_PRENOM));
        enseignant.setFonction(c.getString(NUM_COL_FONCTION));
        enseignant.setEmail(c.getString(NUM_COL_EMAIL));
        enseignant.setNumero(c.getInt(NUM_COL_NUMERO));


        listeEnseignant.add(enseignant);

        for (i=1;i<c.getCount();i++) {
            Enseignant enseignant2=cursorToEnseignant(c);
            if (enseignant2!=null) {
                listeEnseignant.add(enseignant2);
            }
        }
        c.close();
        return listeEnseignant;
    }

    //Cette mÃ©thode permet de convertir un cursor en un livre
    public Enseignant cursorToEnseignant(Cursor c){

        //si aucun Ã©lÃ©ment n'a Ã©tÃ© retournÃ© dans la requÃªte, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier Ã©lÃ©ment
        c.moveToNext();
        //On crÃ©Ã© un livre
        Enseignant enseignant = new Enseignant();
        //on lui affecte toutes les infos grÃ¢ce aux infos contenues dans le Cursor
        enseignant.setId(c.getInt(NUM_COL_ID));
        enseignant.setNom(c.getString(NUM_COL_NOM));
        enseignant.setPrenom(c.getString(NUM_COL_PRENOM));
        enseignant.setFonction(c.getString(NUM_COL_FONCTION));
        enseignant.setEmail(c.getString(NUM_COL_EMAIL));
        enseignant.setNumero(c.getInt(NUM_COL_NUMERO));

        //On retourne le livre
        return enseignant;
    }


}