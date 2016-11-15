package com.mobile.eseo.neweseoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobile.eseo.neweseoapp.adaptater.EnseignantArrayAdaptater;
import com.mobile.eseo.neweseoapp.bdd.EnseignantBDD;
import com.mobile.eseo.neweseoapp.model.Enseignant;

import java.text.ParseException;
import java.util.ArrayList;

public class TabActivity_Enseignants extends AppCompatActivity {

    ListView listView;

    private ArrayList<Enseignant> enseignant = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_enseignants);


        EnseignantBDD enseignantBDD = new EnseignantBDD(this);

        enseignantBDD.open();


        try {
            enseignant = enseignantBDD.getAllEnseignants();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        enseignantBDD.close();

        listView = (ListView) findViewById(R.id.listEnseignants);
        ArrayAdapter<Enseignant> adapter = new EnseignantArrayAdaptater(this, 0, enseignant);
        listView.setAdapter(adapter);

    }
}



