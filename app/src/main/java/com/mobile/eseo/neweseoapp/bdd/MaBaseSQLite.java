/**
 * Created by WatchYourStep on 17/10/2016.
 */

package com.mobile.eseo.neweseoapp.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.mobile.eseo.neweseoapp.model.Enseignant;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_DEADLINE = "table_DeadLine";
    private static final String COL_ID = "Id";
    private static final String COL_MOTIF = "Motif";
    private static final String COL_LIMITE = "Limite";

    private static final String TABLE_PFE = "table_PFE";
    private static final String COL_TITRE = "Titre";
    private static final String COL_ENCADRANT = "Encadrant";
    private static final String COL_PROBLEMATIQUE = "Problematique";

    private static final String TABLE_ENSEIGNANT = "table_Enseignant";
    private static final String COL_NOM = "Nom";
    private static final String COL_PRENOM = "Prenom";
    private static final String COL_FONCTION = "Fonction";
    private static final String COL_EMAIL = "Email";
    private static final String COL_NUMERO = "Numero";


    private static final String CREATE_BDD1 =

            "CREATE TABLE " + TABLE_PFE + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITRE + " TEXT NOT NULL, "
                    + COL_ENCADRANT + " INTEGER,"
                    + COL_PROBLEMATIQUE + " TEXT NOT NULL);";

    private static final String CREATE_BDD2 =
            "CREATE TABLE " + TABLE_DEADLINE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MOTIF + " TEXT NOT NULL, "
            + COL_LIMITE + " TEXT NOT NULL);";

    private static final String CREATE_BDD3 =
            "CREATE TABLE " + TABLE_ENSEIGNANT + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NOM + " TEXT NOT NULL,"
            + COL_PRENOM + " TEXT NOT NULL," + COL_FONCTION + " TEXT NOT NULL,"
            + COL_EMAIL + " TEXT NOT NULL," + COL_NUMERO + " NUMERIC);";


    public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crÃ©e la table Ã  partir de la requÃªte Ã©crite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD1);
        db.execSQL(CREATE_BDD2);
        db.execSQL(CREATE_BDD3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai dÃ©cidÃ© de supprimer la table et de la recrÃ©er
        //comme Ã§a lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_DEADLINE + ";");
        onCreate(db);
    }

}