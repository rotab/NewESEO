package com.mobile.eseo.neweseoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.eseo.neweseoapp.adaptater.DeadlineArrayAdaptater;
import com.mobile.eseo.neweseoapp.bdd.DeadLineBDD;
import com.mobile.eseo.neweseoapp.model.DeadLine;

import java.text.ParseException;
import java.util.ArrayList;

public class TabActivity_Deadlines extends AppCompatActivity {

    ListView listView;

    private ArrayList<DeadLine> deadlines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_deadlines);


        DeadLineBDD deadlinebdd = new DeadLineBDD(this);

        deadlinebdd.open();


        try {
            deadlines = deadlinebdd.getDeadLineWithDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        deadlinebdd.close();

        listView = (ListView) findViewById(R.id.listDeadline);
        ArrayAdapter<DeadLine> adapter = new DeadlineArrayAdaptater(this, 0, deadlines);
        listView.setAdapter(adapter);




        /*listView = (ListView) findViewById(R.id.listDeadline);

        String[] villes = {"Paris", "Marseille", "Lille", "Bordeaux", "Lyon",
                "Toulouse"};

        // Définition de l'adapter
        // Premier Paramètre - Context
        // Second Paramètre - le Layout pour les Items de la Liste
        // Troisième Paramètre - l'ID du TextView du Layout des Items
        // Quatrième Paramètre - le Tableau de Données

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, villes);

        // on assigne l'adapter à notre list
        listView.setAdapter(adapter);

        // la gestion des clics sur les items de la liste
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // l'index de l'item dans notre ListView
                int itemPosition = position;

                // On récupère le texte de l'item cliqué
                String itemValue = (String) listView
                        .getItemAtPosition(position);

                // On affiche ce texte avec un Toast
                Toast.makeText(
                        getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : "
                                + itemValue, Toast.LENGTH_LONG).show();

            }

        });*/

    }

}
