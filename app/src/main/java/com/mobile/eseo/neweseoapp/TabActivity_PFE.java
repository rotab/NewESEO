package com.mobile.eseo.neweseoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobile.eseo.neweseoapp.adaptater.PFEArrayAdaptater;
import com.mobile.eseo.neweseoapp.bdd.PFEBDD;
import com.mobile.eseo.neweseoapp.model.PFE;

import java.text.ParseException;
import java.util.ArrayList;

public class
TabActivity_PFE extends AppCompatActivity {

    ListView listView;

        private ArrayList<PFE> pfe = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tab_pfe);


            PFEBDD pfeBDD = new PFEBDD(this);

            pfeBDD.open();


            try {
                pfe = pfeBDD.getAllPFE();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            pfeBDD.close();

            listView = (ListView) findViewById(R.id.listPFE);
            ArrayAdapter<PFE> adapter = new PFEArrayAdaptater(this, 0, pfe);
            listView.setAdapter(adapter);

        }
}
