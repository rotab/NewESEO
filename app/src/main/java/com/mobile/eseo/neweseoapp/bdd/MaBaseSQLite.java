/**
 * Created by WatchYourStep on 17/10/2016.
 */

package com.mobile.eseo.neweseoapp.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_DEADLINE = "table_DeadLine";
    private static final String COL_ID = "ID";
    private static final String COL_MOTIF = "Motif";
    private static final String COL_LIMITE = "Limite";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_DEADLINE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MOTIF + " TEXT NOT NULL, "
            + COL_LIMITE + " TEXT NOT NULL);";

    public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crÃ©e la table Ã  partir de la requÃªte Ã©crite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai dÃ©cidÃ© de supprimer la table et de la recrÃ©er
        //comme Ã§a lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_DEADLINE + ";");
        onCreate(db);
    }

}